package cloud_cruiser.src.main.java.com.rix003;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class weatherController {

    public TextField cityField;
    public Label weatherLabel;
    private final String apiKey;
    private final String apiUrl;

    public weatherController() {
        configLoader config = new configLoader();
        this.apiKey = System.getenv("Weather_API_Key") != null ? System.getenv("Weather_API_Key") : System.getProperty("weather.api.key");
        this.apiUrl = System.getenv("Weather_API_Url") != null ? System.getenv("Weather_API_Url") : System.getProperty("weather.api.url");
    }

    public void fetchWeather() {
        String cityName = cityField.getText();

        if (cityName.isEmpty()) {
            weatherLabel.setText("Please enter a city name!");
        }

        String requestUrl = String.format("%s?q=%s&appid=%s", apiUrl, cityName, apiKey);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestUrl)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
                String weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
                weatherLabel.setText("Weather: " + weather);

            }
            else {
                weatherLabel.setText("Error: HTTP" + get.statusCode());
            }
        }
        catch (Exception e) {
            weatherLabel.setText("Error fetching the weather data: " + e.getMessage());
        }
    }
}