package object;

import java.util.ArrayList;
import java.util.List;

public class imgLocationObject {
	private List<String> imageData = new ArrayList<>();
	private List<String> addressData = new ArrayList<>();

	public imgLocationObject(List<String> imageData, List<String> addressData) {
		super();
		this.imageData = imageData;
		this.addressData = addressData;
	}

	public List<String> getImageData() {
		return imageData;
	}

	public void setImageData(List<String> imageData) {
		this.imageData = imageData;
	}

	public List<String> getAddressData() {
		return addressData;
	}

	public void setAddressData(List<String> addressData) {
		this.addressData = addressData;
	}

	@Override
	public String toString() {
		return "imgLocationObject [imageData=" + imageData + ", addressData=" + addressData + "]";
	}

}
