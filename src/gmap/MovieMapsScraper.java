package gmap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dbutil.DBUtil;

/*

https://moviemaps.org/movies/
<body class = “movie list”>
	<main>
		<section>
			<article>
				<a name=“A-Z,Misc”>
				<ul>
					<li>
						<a href=“/movies/x1”>제목</a>
					</li>
				</ul>
				</a>
			</article>
		</section>
	</main>
</body>



https://moviemaps.org/movies/x1

포스터
<section id="description">
	<figure class="small poster gallery">
		<a href="/images/15m0" title="Click to view a larger version of the poster.">
			<img src="//storage.googleapis.com/moviemaps/img/15m0.545r6n.100.jpg" width="100" srcset="//storage.googleapis.com/moviemaps/img/15m0.545r6n.100.jpg,
                     //storage.googleapis.com/moviemaps/img/15m0.545r6n.200.jpg 2x" alt="Poster for The A-Team."></a></figure><ul class="links"><li><a href="https://www.imdb.com/title/tt0429493/">View on IMDb</a></li></ul><p class="cities description">
        The A-Team was filmed in <a href="/cities/2">Vancouver</a> &amp; <a href="/cities/91">Richmond</a> in Canada.
      </p></section>

장소이름,장소사진
갯수가 6개 이상인 것만 
<section>
	<article>
		<a name="6q6"></a>
		<figure class="location-map gallery">
			<a href="/locations/z2">
				<img src="//storage.googleapis.com/moviemaps/img/2ux.1mv45o.160.jpg" width="160" height="160" srcset="//storage.googleapis.com/moviemaps/img/2ux.1mv45o.160.jpg,
                     //storage.googleapis.com/moviemaps/img/2ux.1mv45o.320.jpg 2x" title="Click to see more detail on Vancouver Convention Centre." alt="Photograph of Vancouver Convention Centre."></a></figure><div><h4><a href="/locations/z2">Vancouver Convention Centre</a></h4><div class="markdown"><p></p></div><section class="gallery"></section><section class="source"><div class="markdown"><p>Source: <a href="https://www.youtube.com/watch?v=ojm74VGsZBU">Every Frame a Painting — YouTube</a></p></div></section></div></article><article><a name="ir4"></a><figure class="location-map gallery"><a href="/locations/zc"><img src="//storage.googleapis.com/moviemaps/img/137.fyawqa.160.jpg" width="160" height="160" srcset="//storage.googleapis.com/moviemaps/img/137.fyawqa.160.jpg,
                     //storage.googleapis.com/moviemaps/img/137.fyawqa.320.jpg 2x" title="Click to see more detail on Aerospace Technology Campus." alt="Photograph of Aerospace Technology Campus."></a></figure><div><h4><a href="/locations/zc">Aerospace Technology Campus</a>

  (<a href="/locations/1fd">BCIT</a>)


          
        </h4><div class="markdown"><p></p></div>
        <section class="gallery"></section>
        <section class="source"><div class="markdown"><p>Source: Roman Latta</p></div></section></div></article></section>



		 <section class="gallery">
		 <figure class="tiny thumbnail" img-key="1jn9.d9yk9m"><a href="/images/1jn9"><img title="Click to view a larger version of this image." class="tiny thumbnail" src="//storage.googleapis.com/moviemaps/img/1jn9.d9yk9m.78.jpg" srcset="//storage.googleapis.com/moviemaps/img/1jn9.d9yk9m.78.jpg, //storage.googleapis.com/moviemaps/img/1jn9.d9yk9m.156.jpg 2x"></a></figure><figure class="tiny thumbnail" img-key="1jnb.3cg53c"><a href="/images/1jnb"><img title="Click to view a larger version of this image." class="tiny thumbnail" src="//storage.googleapis.com/moviemaps/img/1jnb.3cg53c.78.jpg" srcset="//storage.googleapis.com/moviemaps/img/1jnb.3cg53c.78.jpg, //storage.googleapis.com/moviemaps/img/1jnb.3cg53c.156.jpg 2x"></a></figure><figure class="tiny thumbnail" img-key="1jna.1kitdy"><a href="/images/1jna"><img title="Click to view a larger version of this image." class="tiny thumbnail" src="//storage.googleapis.com/moviemaps/img/1jna.1kitdy.78.jpg" srcset="//storage.googleapis.com/moviemaps/img/1jna.1kitdy.78.jpg, //storage.googleapis.com/moviemaps/img/1jna.1kitdy.156.jpg 2x"></a></figure><figure class="tiny thumbnail" img-key="1jnc.3ntni8"><a href="/images/1jnc"><img title="Click to view a larger version of this image." class="tiny thumbnail" src="//storage.googleapis.com/moviemaps/img/1jnc.3ntni8.78.jpg" srcset="//storage.googleapis.com/moviemaps/img/1jnc.3ntni8.78.jpg, //storage.googleapis.com/moviemaps/img/1jnc.3ntni8.156.jpg 2x"></a></figure><figure class="tiny thumbnail" img-key="1jnd.d3w5at"><a href="/images/1jnd"><img title="Click to view a larger version of this image." class="tiny thumbnail" src="//storage.googleapis.com/moviemaps/img/1jnd.d3w5at.78.jpg" srcset="//storage.googleapis.com/moviemaps/img/1jnd.d3w5at.78.jpg, //storage.googleapis.com/moviemaps/img/1jnd.d3w5at.156.jpg 2x"></a></figure></section>

https://moviemaps.org/locations/z2

주소
<section id="description">
	<h1>Movies Filmed at Vancouver Convention Centre</h1>
	<address>1055 Canada Place, Vancouver, BC V6C 3T4, Canada</address>

<div class="markdown"><p>The recently opened West Building of the Vancouver Convention Centre has been a popular building with filmmakers in the past five years. The building features a multi-story lobby connecting the underground portion of West Waterfront Road to the convention centre and Canada Place.</p></div><section class="gallery"><figure class="tiny thumbnail" img-key="2ux.1mv45o" img-alt="Photograph of Vancouver Convention Centre."><a href="/images/2ux"><img title="Click to view a larger version of this image." alt="Photograph of Vancouver Convention Centre." class="tiny thumbnail" src="//storage.googleapis.com/moviemaps/img/2ux.1mv45o.78.jpg" srcset="//storage.googleapis.com/moviemaps/img/2ux.1mv45o.78.jpg, //storage.googleapis.com/moviemaps/img/2ux.1mv45o.156.jpg 2x"></a></figure><figure class="tiny thumbnail" img-key="2uz.10k8m9" img-alt="Photograph of Vancouver Convention Centre."><a href="/images/2uz"><img title="Click to view a larger version of this image." alt="Photograph of Vancouver Convention Centre." class="tiny thumbnail" src="//storage.googleapis.com/moviemaps/img/2uz.10k8m9.78.jpg" srcset="//storage.googleapis.com/moviemaps/img/2uz.10k8m9.78.jpg, //storage.googleapis.com/moviemaps/img/2uz.10k8m9.156.jpg 2x"></a></figure><figure class="tiny thumbnail" img-key="yfs.cgg6mc" img-alt="Photograph of Vancouver Convention Centre."><a href="/images/yfs"><img title="Click to view a larger version of this image." alt="Photograph of Vancouver Convention Centre." class="tiny thumbnail" src="//storage.googleapis.com/moviemaps/img/yfs.cgg6mc.78.jpg" srcset="//storage.googleapis.com/moviemaps/img/yfs.cgg6mc.78.jpg, //storage.googleapis.com/moviemaps/img/yfs.cgg6mc.156.jpg 2x"></a></figure><figure class="tiny thumbnail" img-key="yft.e8zp4z" img-alt="Photograph of Vancouver Convention Centre."><a href="/images/yft"><img title="Click to view a larger version of this image." alt="Photograph of Vancouver Convention Centre." class="tiny thumbnail" src="//storage.googleapis.com/moviemaps/img/yft.e8zp4z.78.jpg" srcset="//storage.googleapis.com/moviemaps/img/yft.e8zp4z.78.jpg, //storage.googleapis.com/moviemaps/img/yft.e8zp4z.156.jpg 2x"></a></figure><figure class="tiny thumbnail" img-key="yfu.4fc3td" img-alt="Photograph of Vancouver Convention Centre."><a href="/images/yfu"><img title="Click to view a larger version of this image." alt="Photograph of Vancouver Convention Centre." class="tiny thumbnail" src="//storage.googleapis.com/moviemaps/img/yfu.4fc3td.78.jpg" srcset="//storage.googleapis.com/moviemaps/img/yfu.4fc3td.78.jpg, //storage.googleapis.com/moviemaps/img/yfu.4fc3td.156.jpg 2x"></a></figure></section><noscript><section class="gallery"><a href="/images/2ux">Browse Gallery</a></section></noscript><h5>Nearby Locations</h5><div class="nearby"><a href="/locations/2qt">
        West Waterfront Road (between Thurlow &amp; …
      </a>
      
        from Arrow
          and 1 other movie.
        
      
      <div>
        
          82 m
        
      </div><br><a href="/locations/acr">
        Jack Poole Plaza
      </a>
      
        from Supergirl
      
      <div>
        
          93 m
        
      </div><br><a href="/locations/3fl">
        Fairmont Pacific Rim
      </a>
      
        from Arrow
      
      <div>
        
          112 m
        
      </div><br>
    

      Browse more
      <a href="/locations/?lat=49.2891411186&amp;lng=-123.116458805">nearby locations</a>.
    </div></section>


*/
public class MovieMapsScraper {

	private static final String BASE_URL = "https://moviemaps.org/movies/";

	public Map<String, String> getMovieTitleAndURL() {
		try {
			Map<String, String> MovieTitleAndURLMap = new HashMap();
			Document document = Jsoup.connect(BASE_URL).get();

			Elements movieLinks = document.select("a[href^='/movies/']");

			for (Element link : movieLinks) {
				String title = link.text();
				String movieUrl = BASE_URL + link.attr("href").substring(1);

				MovieTitleAndURLMap.put(title, movieUrl);

//    			System.out.println("Title: " + title);
//    			System.out.println("URL: " + movieUrl);

			}
			return MovieTitleAndURLMap;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

//	영화 제목,포스터이미지,장소 이미지 
	public String getPosterAndPlaceImage() {

		try {
			List<String> keysToRemove = new ArrayList<>();

			Map<String, String> MovieTitleAndURLMap = getMovieTitleAndURL();

			for (Map.Entry<String, String> entry : MovieTitleAndURLMap.entrySet()) {
				String title = entry.getKey();
				String movieUrl = entry.getValue();

				Document document = Jsoup.connect(movieUrl).get();

				Elements articles = document.select("article");

				int count = 0;
				
				if (articles.size() >= 6) {

					for (Element article : articles) {

						Element locationmapgalleryFigureTag = article.selectFirst("figure.location-map.gallery");
						Elements tinyThumbnailFigureTags = article.select("figure.tiny.thumbnail");

						if (locationmapgalleryFigureTag != null) {
							Element aTag = locationmapgalleryFigureTag.selectFirst("a");

							if (aTag != null || !tinyThumbnailFigureTags.isEmpty()) {
								
								
//				                이미지가 있다는 뜻!!
								count++;

							}
						}
						
	
					}

				}
				
				if (count >=6) {
					for (Element article : articles) {
						Element locationmapgalleryFigureTag = article.selectFirst("figure.location-map.gallery");
						Element tinyThumbnailFigureTags = article.selectFirst("figure.tiny.thumbnail");
						
						if(locationmapgalleryFigureTag == null && tinyThumbnailFigureTags == null) {
							continue;
						}
						if(locationmapgalleryFigureTag != null && tinyThumbnailFigureTags ==null) {
							
						}
						if(locationmapgalleryFigureTag != null && tinyThumbnailFigureTags !=null) {
							
						}
						if(locationmapgalleryFigureTag == null && tinyThumbnailFigureTags !=null) {
							
						}
						
					}
					
				}
				
				

				Elements anchors = document.select("figure.location-map.gallery a");
				Elements gallerySectionsHasFigures = document.select("section.gallery:has(figure.tiny.thumbnail)");

				if (anchors != null && gallerySectionsHasFigures != null) {
					if (anchors.size() >= 6) {
						for (Element anchor : anchors) {
							String hrefValue = anchor.attr("href");

//							장소 디테일 주소
							String PlaceDetailUrl = "https://moviemaps.org" + hrefValue;
//							장소 이미지와 주소를 구할 수 있음 
							Document PlaceImagedocument = Jsoup.connect(PlaceDetailUrl).get();

//					       	 장소 구하기 
							Element addressElement = PlaceImagedocument.select("address").first();
							String address = addressElement.text();

//							위도와 경도 추출
							Element nearbyLocationsLink = PlaceImagedocument.select("a[href~=(\\?|&)lat=]").first();
							String hrefplace = nearbyLocationsLink.attr("href");

							String lat = hrefplace.substring(hrefplace.indexOf("lat=") + 4, hrefplace.indexOf("&"));
							int startIndexOfLng = hrefplace.indexOf("lng=") + 4;
							int endIndexOfLng = hrefplace.indexOf("&", startIndexOfLng);
							if (endIndexOfLng == -1) { // "&"가 발견되지 않는 경우 문자열의 끝을 사용
								endIndexOfLng = hrefplace.length();
							}

							String lng = hrefplace.substring(startIndexOfLng, endIndexOfLng);

//							이미지 구하기 
							Element firstGallerySection = PlaceImagedocument.select("section.gallery").first();
							if (firstGallerySection != null) {
								Element aTag = firstGallerySection.select("a").first();
								if (aTag != null) {
									String imagehref = aTag.attr("href");

//					       	장소 큰 이미지를 찾을 수있는 url
									String imageUrl = "https://moviemaps.org" + imagehref;

									Document imagedocument = Jsoup.connect(imageUrl).get();

									Element imgElement = imagedocument.select("figure img").first();
//					                 장소 큰 이미지 url
									String imgUrl = imgElement.attr("abs:src");

									URL imgURL = new URL(imgUrl);
									URLConnection connection = imgURL.openConnection();
									InputStream inputStream = connection.getInputStream();

									int movie_no = getMovie_noWithTitle(title);
									insertIntoLocation(movie_no, address, Double.valueOf(lat), Double.valueOf(lng),
											inputStream);

								}
							}

						}

					}

				} else if (gallerySectionsHasFigures.size() >= 6) {

					for (Element gallerySection : gallerySectionsHasFigures) {
						Element firstFigure = gallerySection.selectFirst("figure.tiny.thumbnail");

						Element anchorTag = firstFigure.selectFirst("a");

						if (anchorTag != null) {
							String hrefValue = anchorTag.attr("href");

//								장소 큰 사진
							String imageUrl = "https://moviemaps.org" + hrefValue;

							Document imagedocument = Jsoup.connect(imageUrl).get();

							Element imgElement = imagedocument.select("figure img").first();
//			                 장소 큰 이미지 url
							String imgUrl = imgElement.attr("abs:src");

							URL imgURL = new URL(imgUrl);
							URLConnection connection = imgURL.openConnection();
							InputStream inputStream = connection.getInputStream();

						} else {
							System.out.println("No matching element found.");
						}
					}
				} else {
					keysToRemove.add(title);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public int insertIntoLocation(int movie_no, String address, Double latitude, Double longitude,
			InputStream inputStream) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();

			String sql = "INSERT INTO location (movie_no,address,latitude,longitude,image) VALUES (?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, movie_no);
			pstmt.setString(2, address);
			pstmt.setDouble(3, latitude);
			pstmt.setDouble(4, longitude);
			pstmt.setBlob(5, inputStream);
			int result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return 0;

	}

	public int getMovie_noWithTitle(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT movie_no FROM movie WHERE title = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int movie_no = rs.getInt("movie_no");
				return movie_no;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
