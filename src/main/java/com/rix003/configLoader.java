package cloud_cruiser.src.main.java.com.rix003;

import java.io.IOException;
import java.util.Properties;

public class configLoader {
    private Properties properties = new Properties();
    
    public configLoader() {
        try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(input);
        }
        catch (IOException e) {
            System.err.println("Error loading configuration file: " + e.getMessage());
        }
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
