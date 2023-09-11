package secret;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadAPIKey {
	public static String loadGoogleMapsAPIKey() {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream("src/secret.properties")) {
			prop.load(input);
			return prop.getProperty("google.maps.api.key");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
