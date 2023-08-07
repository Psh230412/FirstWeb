package gmap;

public class Movie {
	private String title;
	private String poster;
	private LocationAndImage locationAndImage;
	
	public Movie(String title, String poster, LocationAndImage locationAndImage) {
		this.title = title;
		this.poster = poster;
		this.locationAndImage = locationAndImage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public LocationAndImage getLocationAndImage() {
		return locationAndImage;
	}

	public void setLocationAndImage(LocationAndImage locationAndImage) {
		this.locationAndImage = locationAndImage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locationAndImage == null) ? 0 : locationAndImage.hashCode());
		result = prime * result + ((poster == null) ? 0 : poster.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (locationAndImage == null) {
			if (other.locationAndImage != null)
				return false;
		} else if (!locationAndImage.equals(other.locationAndImage))
			return false;
		if (poster == null) {
			if (other.poster != null)
				return false;
		} else if (!poster.equals(other.poster))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", poster=" + poster + ", locationAndImage=" + locationAndImage + "]";
	}
	
	
}
