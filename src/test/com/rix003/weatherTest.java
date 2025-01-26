package com.rix003;

import javafx.scene.control.Label;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class weatherTest {
    
    @Test
    public void testFetchedWeather() {
        weatherController controller = new weatherController();
        controller.cityField = new TextField("London");
        controller. weatherLabel = new Label();
        controller.fetchWeather();

        assertTrue(controller.weatherLabel.getText().contains("Weather"));
    }
}
