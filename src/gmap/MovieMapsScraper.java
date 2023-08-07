package gmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
<<<<<<< HEAD
//	영화 제목,포스터이미지,장소 이미지 
	public String getPosterAndPlaceImage() {

		try {
			List<String> keysToRemove = new ArrayList<>();

			Map<String, String> MovieTitleAndURLMap = getMovieTitleAndURL();

			for (Map.Entry<String, String> entry : MovieTitleAndURLMap.entrySet()) {
				String title = entry.getKey();
				String movieUrl = entry.getValue();

				Document document = Jsoup.connect(movieUrl).get();

				Elements anchors = document.select("figure.location-map.gallery a");
				

				if (anchors != null) {
					if (anchors.size() >= 6) {
						for(Element anchor : anchors) {
							
						}
						
					} else {
						keysToRemove.add(title);
					}
				}
				
				
				
				Elements gallerySectionsHasFigures = document.select("section.gallery:has(figure.tiny.thumbnail)");

		        if (gallerySectionsHasFigures.size() >= 6) {
		        	 Elements gallerySections = document.select("section.gallery");
		        	 
		        	 for (Element gallerySection : gallerySections) {
		                 Element firstFigure = gallerySection.selectFirst("figure.tiny.thumbnail");
		                 
		                 Element anchorTag = firstFigure.selectFirst("a");
		                 
		                 if (anchorTag != null) {
		                     String hrefValue = anchorTag.attr("href");
		                     String fullUrl = "https://moviemaps.org" + hrefValue;
		                     
		                     
		                     
		                 } else {
		                     System.out.println("No matching element found.");
		                 }
		             }
		        }
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
=======

//	public String getLocationURL() {
//
//		try {
//			Map<String, String> MovieTitleAndURLMap = new HashMap();
//
//			for (Map.Entry<String, String> entry : MovieTitleAndURLMap.entrySet()) {
//				String title = entry.getKey();
//				String movieUrl = entry.getValue();
//
//				Document document = Jsoup.connect(movieUrl).get();
//				
//				Elements gallerylements = document.select("section.gallery");
//				
//				for (Element galleryElement : gallerylements) {
//		            Elements thumbnailElements = galleryElement.select("figure.tiny.thumbnail");
//		            if (thumbnailElements.size() >= 6) {
//		            	
//		            } else {
//		            	
//		            	continue;
//		            }
//				
//				}
//
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
>>>>>>> branch 'master' of https://github.com/Psh230412/FirstWeb.git

	public static void main(String[] args) {
	}
}
