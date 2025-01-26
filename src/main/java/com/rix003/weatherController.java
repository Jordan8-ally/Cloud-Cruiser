package com.rix003;

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

    public void fetchWeather() {
        String cityName = cityField.getText();

        if (cityName.isEmpty()) {
            weatherLabel.setText("Please enter a city name!");
        }

        String apiKey = "YOUR_API_KEY";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
                String weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
                weatherLabel.setText("Weather: " + weather);

            }
            else {
                weatherLabel.setText("Error: Unable to fetch weather data.");
            }
        }
        catch (Exception e) {
            weatherLabel.setText("Error: " + e.getMessage());
        }
    }
}