package entity;

import static org.junit.Assert.*;

import entity.Weather;
import org.junit.Before;
import org.junit.Test;


public class WeatherTest {

    private Weather weather;

    @Before
    public void setUp() {
        weather = new Weather("London", "2023-12-01 10:00", 15, 59, "Sunny");
    }

    @Test
    public void testConstructor() {
        assertEquals("London", weather.getLocation());
        assertEquals("2023-12-01 10:00", weather.getTime());
        assertEquals(15, weather.getTempC());
        assertEquals(59, weather.getTempF());
        assertEquals("Sunny", weather.getCondition());
    }

    // Assuming getter methods exist
    @Test
    public void testGetLocation() {
        assertEquals("London", weather.getLocation());
    }

    @Test
    public void testGetTime() {
        assertEquals("2023-12-01 10:00", weather.getTime());
    }

    @Test
    public void testGetTempC() {
        assertEquals(15, weather.getTempC());
    }

    @Test
    public void testGetTempF() {
        assertEquals(59, weather.getTempF());
    }

    @Test
    public void testGetCondition() {
        assertEquals("Sunny", weather.getCondition());
    }

}