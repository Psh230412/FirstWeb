package gmap;

import java.io.InputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ImageDownloader {
	public static void main(String[] args) throws Exception {

		String html = "<figure class=\"location-map gallery\"><a href=\"/locations/gz\"><img src=\"//storage.googleapis.com/moviemaps/img/302.4ppevu.160.jpg\" width=\"160\" height=\"160\" srcset=\"//storage.googleapis.com/moviemaps/img/302.4ppevu.160.jpg,\n"
				+ "                     //storage.googleapis.com/moviemaps/img/302.4ppevu.320.jpg 2x\" title=\"Click to see more detail on Katz's Deli.\" alt=\"Photograph of Katz's Deli.\"></a></figure>";

		Document doc = Jsoup.parse(html);
		Element imgElement = doc.selectFirst("figure.location-map.gallery img");

		if (imgElement != null) {
			String imgUrl = "https:" + imgElement.attr("src"); // URL에 스키마 (https:) 추가
			downloadImage(imgUrl, "downloaded_image.jpg"); // 이미지를 "downloaded_image.jpg" 파일로 저장
		} else {
			System.out.println("No image found.");
		}
	}

	private static void downloadImage(String imageUrl, String targetFilePath) throws Exception {
		try (InputStream in = new URL(imageUrl).openStream()) {
//            Path outputPath = Path.of(targetFilePath);
//            Files.copy(in, outputPath, StandardCopyOption.REPLACE_EXISTING);
		}
	}
}
