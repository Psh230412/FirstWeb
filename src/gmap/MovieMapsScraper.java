package gmap;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dbutil.DBUtil;

public class MovieMapsScraper implements Runnable {

	private static final String BASE_URL = "https://moviemaps.org/movies/";
	private MovieTitleAndURL titleAndURL;

	Connection conn = null;

	public MovieMapsScraper(MovieTitleAndURL titleAndURL, Connection conn) {
		this.titleAndURL = titleAndURL;
		this.conn = conn;
	}

	public static int sleep(int primaryMillesecond, int millesecond) throws InterruptedException {
		Random rand = new Random();
		int randomDelay = primaryMillesecond + rand.nextInt(millesecond);
//		System.out.println(placeImageUrlrandomDelay + "	초 취침");

		Thread.sleep(randomDelay);

		return randomDelay / 1000;
	}

	public static List<MovieTitleAndURL> getMovieTitleAndURLList() {
		try {
			List<MovieTitleAndURL> MovieTitleAndURLList = new ArrayList<>();

			org.jsoup.Connection BASE_URLConnection = Jsoup.connect(BASE_URL);

			Response BASE_URLResponse = BASE_URLConnection.execute();
			int BASE_URLStatusCode = BASE_URLResponse.statusCode();

			if (BASE_URLStatusCode == 403) {
				System.out.println("BASE_URLStatusCode:	" + BASE_URLStatusCode);
			} else if (BASE_URLStatusCode == 200) {
				System.out.println("BASE_URLStatusCode:	" + BASE_URLStatusCode);
				System.out.println(BASE_URL + "으로 이동 완료");
				Document document = BASE_URLResponse.parse();

				Elements movieLinks = document.select("a[href^='/movies/']");

				System.out.println(movieLinks.size() + "이것은 개별 영화 페이지 링크들의 갯수 입니다.");

				for (Element link : movieLinks) {

					String title = link.text();

					String hrefValue = link.attr("href");
					String movieId = hrefValue.split("/")[2];

					String movieUrl = BASE_URL + movieId;

					if (!gmap_Movie_DAO.isDup(title)) {
						MovieTitleAndURLList.add(new MovieTitleAndURL(title, movieUrl));

					} else {
						System.out.println(title + " 는 movie 테이블에서 중복을 발견하여 제외되었습니다.");
					}

				}
				return MovieTitleAndURLList;
			} else {
				System.out.println("BASE_URLStatusCode:	" + BASE_URLStatusCode);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void run() {

		String title = titleAndURL.getTitle();
		String movieUrl = titleAndURL.getURL();

		try {

//			System.out.println(movieUrl + "	접속 전  취침");
//			sleep(5000, 10000);

//			System.out.println(title+"리스트 내의 마지막 영화입니다.");

			org.jsoup.Connection movieUrlConnection = Jsoup.connect(movieUrl);
			Response movieUrlResponse = movieUrlConnection.execute();
			int movieUrlStatusCode = movieUrlResponse.statusCode();

			if (movieUrlStatusCode == 403) {
				System.out.println("movieUrlStatusCode:	" + movieUrlStatusCode);
			} else if (movieUrlStatusCode == 200) {
				System.out.println("movieUrlStatusCode:	" + movieUrlStatusCode);
				Document document = movieUrlResponse.parse();

				if (hasMoreSix(document, title)) {

					activateInsertMovie(document, title, conn);


					int count = gmap_Movie_DAO.getCountByTitle(conn, title);
//
//					System.out.println(title + "이 가지고 있는 장소의 갯수:	" + count);
//
					if (count <= 30) {
						activateInsertLocation(document, title, conn);
						
					}
				}

			} else {
				System.out.println("movieUrlStatusCode: " + movieUrlStatusCode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);

		}

	}

	public static void main(String[] args) {

		Connection conn = null;

		try {

			ExecutorService executor = Executors.newFixedThreadPool(32);

			List<MovieTitleAndURL> MovieTitleAndURLList = getMovieTitleAndURLList();

			List<MovieMapsScraper> workers = new ArrayList<>();

			for (MovieTitleAndURL titleAndURL : MovieTitleAndURLList) {
				conn = DBUtil.getConnection();

				MovieMapsScraper worker = new MovieMapsScraper(titleAndURL, conn);
				workers.add(worker);
				executor.execute(worker);

			}

			executor.shutdown();

			while (!executor.isTerminated()) {
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	영화 한개에 대해서 검증하는 메서드 
	public static boolean hasMoreSix(Document document, String title) throws IOException {

		Elements articles = document.select("article");

		int count = 0;

		for (Element article : articles) {

			Element h4Link = article.select("h4 > a[href^='/locations/']").first();

			if (h4Link != null) {

				Elements tinyThumbnailFigureTags = article.select("figure.tiny.thumbnail");

				if (!tinyThumbnailFigureTags.isEmpty()) {
//                        이미지가 있다는 뜻!!
					count++;

				}
			}
		}

		if (count >= 6) {
			return true;
		}
		System.out.println(title + " 은 이미지를 가지고 있는 장소의 갯수가 6개 미만이라서 제외 됐습니다. ");
		return false;
	}

// 영화 하나를 삽입하는  메서드
	public static void activateInsertMovie(Document document, String title, Connection conn)
			throws IOException, InterruptedException {

		Element aElement = document.select("figure.small.poster.gallery a").first();
		if (aElement == null) {
			System.out.println("aElement이 null 입니다.");

			System.out.println("figure.small.poster.gallery a 를 찾지 못했습니다 다음 영화를 탐색하겠습니다.");
			throw new NoSuchElementException();
		}

		if (aElement != null) {

			String hrefposter = aElement.attr("href");

			String posterUrl = "https://moviemaps.org" + hrefposter;
			System.out.println("poster를 다운로드 받기 위한 url: " + posterUrl);

//			System.out.println(posterUrl + "	접속 전 취침");
//			sleep(5000, 10000);

			org.jsoup.Connection posterUrlConnection = Jsoup.connect(posterUrl);
			Response posterUrlResponse = posterUrlConnection.execute();
			int posterUrlStatusCode = posterUrlResponse.statusCode();

			if (posterUrlStatusCode == 403) {
				System.out.println("posterUrlStatusCode:	" + posterUrlStatusCode);
			} else if (posterUrlStatusCode == 200) {
				System.out.println("posterUrlStatusCode:	" + posterUrlStatusCode);
				System.out.println(posterUrl + "으로 이동 완료");

				Document posterdocument = posterUrlResponse.parse();

				Element posterElement = posterdocument.select("figure img").first();
				if (posterElement == null) {
					System.out.println("posterElement이 null 입니다.");

				}
				if (posterElement != null) {

					String posterimgUrl = posterElement.attr("abs:src");

					URL posterimgURL = new URL(posterimgUrl);

//					System.out.println(posterimgURL + "	접속 전	취침");
//					sleep(5000, 10000);
					URLConnection posterimgURLConnection = posterimgURL.openConnection();

					if (posterimgURLConnection instanceof HttpURLConnection) {
						HttpURLConnection posterimgURLHttpConnection = (HttpURLConnection) posterimgURLConnection;
						int posterimgURLStatusCode = posterimgURLHttpConnection.getResponseCode();
						System.out.println("posterimgURLStatusCode:	" + posterimgURLStatusCode);
						System.out.println(posterimgUrl + "연결 성공");

						if (posterimgURLStatusCode != 200) {
							System.out.println("posterimgURLStatusCode:	" + posterimgURLStatusCode);
							System.out.println("Error message:	" + posterimgURLHttpConnection.getResponseMessage());
						}
					}
					InputStream posterinputStream = posterimgURLConnection.getInputStream();

					gmap_Movie_DAO.insertIntoMovie(title, posterinputStream, conn);

					posterinputStream.close();

					System.out.println("insertIntoMovie 메서드로 movie 테이블에 " + title + " 을 삽입 했습니다.");

				}

			} else {
				System.out.println("posterUrlStatusCode:	" + posterUrlStatusCode);
			}
		}
	}

//  한개의 영화에 딸린 장소들을 삽입하는 메서드 

	public static void activateInsertLocation(Document document, String title, Connection conn
			) throws IOException, InterruptedException {

		Elements articles = document.select("article");

		for (Element article : articles) {
			System.out.println(title + "을 location 테이블에 넣는 작업을 tinyThumbnailFigureTags != null 에서 시작하겠습니다.");
			Element h4Link = article.select("h4 > a[href^='/locations/']").first();

			if (h4Link != null) {
				Element tinyThumbnailInFigureTag = article.selectFirst("figure.tiny.thumbnail");

				if (tinyThumbnailInFigureTag != null) {
					Element a_tagInTinythumbnail = tinyThumbnailInFigureTag.selectFirst("a");

					if (a_tagInTinythumbnail != null) {
						String imagehrefInA_tag = a_tagInTinythumbnail.attr("href");
						String placeImageUrl = "https://moviemaps.org" + imagehrefInA_tag;

//						System.out.println(placeImageUrl + "	접속전 취침");
//						sleep(5000, 10000);

						org.jsoup.Connection placeImageUrlConnection = Jsoup.connect(placeImageUrl);
						Response placeImageresponse = placeImageUrlConnection.execute();
						int placeImageStatusCode = placeImageresponse.statusCode();

						if (placeImageStatusCode == 403) {
							System.out.println("placeImageStatusCode:	" + placeImageStatusCode);
						} else if (placeImageStatusCode == 200) {
							System.out.println("placeImageStatusCode:	" + placeImageStatusCode);
							Document placeImagedocument = placeImageresponse.parse();
							Element movieLink = placeImagedocument
									.selectFirst("section#description a[href^=/locations/]");

							if (movieLink != null) {
								String placeDetailhref = movieLink.attr("href");
								String placeDetailUrl = "https://moviemaps.org" + placeDetailhref;

//								System.out.println(placeDetailUrl + "	접속전 취침");
//								sleep(5000, 10000);

								org.jsoup.Connection placeDetailUrlConnection = Jsoup.connect(placeDetailUrl);
								Response placeDetailUrlresponse = placeDetailUrlConnection.execute();

								int placeDetailUrlStatusCode = placeDetailUrlresponse.statusCode();
								if (placeDetailUrlStatusCode == 403) {
									System.out.println("placeDetailUrlStatusCode:	" + placeDetailUrlStatusCode);
								} else if (placeDetailUrlStatusCode == 200) {
									System.out.println("placeDetailUrlStatusCode:	" + placeDetailUrlStatusCode);
									Document placeDetaildocument = placeDetailUrlresponse.parse();
									Element addressElement = placeDetaildocument.select("address").first();

									if (addressElement != null) {
										String address = addressElement.text();
										Element a_tagHaveLatLng = placeDetaildocument.select("a[href~=(\\?|&)lat=]")
												.first();

										if (a_tagHaveLatLng != null) {
											String latLnghref = a_tagHaveLatLng.attr("href");
											String lat = latLnghref.substring(latLnghref.indexOf("lat=") + 4,
													latLnghref.indexOf("&"));
											int startIndexOfLng = latLnghref.indexOf("lng=") + 4;
											int endIndexOfLng = latLnghref.indexOf("&", startIndexOfLng);
											if (endIndexOfLng == -1) {
												endIndexOfLng = latLnghref.length();
											}
											String lng = latLnghref.substring(startIndexOfLng, endIndexOfLng);
											Element imgElement = placeImagedocument.select("figure img").first();

											if (imgElement != null) {
												String imgUrl = imgElement.attr("abs:src");
												URL imgURL = new URL(imgUrl);

//												System.out.println(imgUrl + "	접속전 취침");
//												sleep(5000, 10000);
												URLConnection imgUrlConnection = imgURL.openConnection();

												if (imgUrlConnection instanceof HttpURLConnection) {
													HttpURLConnection imgUrlHttpConnection = (HttpURLConnection) imgUrlConnection;
													int imgUrlStatusCode = imgUrlHttpConnection.getResponseCode();
													System.out.println("imgUrlStatusCode:	" + imgUrlStatusCode);
													System.out.println(imgUrl + "	연결 성공");

													if (imgUrlStatusCode != 200) {
														System.out.println("imgUrlStatusCode:	" + imgUrlStatusCode);
														System.out.println("Error message:	"
																+ imgUrlHttpConnection.getResponseMessage());
													}
												}

												InputStream inputStream = imgUrlConnection.getInputStream();

//												gmap_Movie_DAO.insertIntoMovie(title, posterinputStream, conn);

												int movie_no = gmap_Movie_DAO.getMovie_noWithTitle(title, conn);

												int result = gmap_Movie_DAO.insertIntoLocation(movie_no, address,
														Double.valueOf(lat), Double.valueOf(lng), inputStream, conn);

												System.out.println(title + "의 장소 location 테이블에 성공적으로 추가 하였습니다.");

												inputStream.close();
//												posterinputStream.close();
											}
										}
									}
								} else {
									System.out.println("placeDetailUrlStatusCode:	" + placeDetailUrlStatusCode);
								}
							}
						} else {
							System.out.println("placeImageStatusCode:	" + placeImageStatusCode);
						}
					}
				}
			}
		}
	}
}
