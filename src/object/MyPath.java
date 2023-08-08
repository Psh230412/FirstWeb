package object;

import java.sql.Blob;

public class MyPath {
	int pathNo;
	String locationAddress1;
	String locationAddress2;
	String locationAddress3;
	String locationAddress4;
	Blob pathMapImage;

	public MyPath(int pathNo, String locationAddress1, String locationAddress2, String locationAddress3,
			String locationAddress4, Blob pathMapImage) {
		this.pathNo = pathNo;
		this.locationAddress1 = locationAddress1;
		this.locationAddress2 = locationAddress2;
		this.locationAddress3 = locationAddress3;
		this.locationAddress4 = locationAddress4;
		this.pathMapImage = pathMapImage;
	}

	public int getPathNo() {
		return pathNo;
	}

	public void setPathNo(int pathNo) {
		this.pathNo = pathNo;
	}

	public String getLocationAddress1() {
		return locationAddress1;
	}

	public void setLocationAddress1(String locationAddress1) {
		this.locationAddress1 = locationAddress1;
	}

	public String getLocationAddress2() {
		return locationAddress2;
	}

	public void setLocationAddress2(String locationAddress2) {
		this.locationAddress2 = locationAddress2;
	}

	public String getLocationAddress3() {
		return locationAddress3;
	}

	public void setLocationAddress3(String locationAddress3) {
		this.locationAddress3 = locationAddress3;
	}

	public String getLocationAddress4() {
		return locationAddress4;
	}

	public void setLocationAddress4(String locationAddress4) {
		this.locationAddress4 = locationAddress4;
	}

	public Blob getPathMapImage() {
		return pathMapImage;
	}

	public void setPathMapImage(Blob pathMapImage) {
		this.pathMapImage = pathMapImage;
	}
}
