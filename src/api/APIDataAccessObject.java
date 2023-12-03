package api;

import entity.AirQuality;
import entity.Weather;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class APIDataAccessObject implements WeatherDB {

    private static final String API_URL = "https://api.weatherapi.com/v1/current.json?key=";
    //
    private static final String API_TOKEN = "ec9198023c8a4fd9a8904131232909";

    public static String getApiToken() {
        return API_TOKEN;
    }

    @Override
    public Weather getWeather(String location) {
        // Here is using Alice's API Key.
        // Postal Code only need first three characters.

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Construct the URL for the WeatherAPI.com request
        String apiURL = API_URL + API_TOKEN + "&q=" + location;

        Request request = new Request.Builder()
                .url(apiURL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                JSONObject loc = responseBody.getJSONObject("location");
                JSONObject current = responseBody.getJSONObject("current");
                return Weather.builder()
                        .location(loc.getString("name"))
                        .tempC(current.getInt("temp_c"))
                        .tempF(current.getInt("temp_f"))
                        .condition(current.getJSONObject("condition").getString("text"))
                        .build();

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AirQuality getAirQuality(String location) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Construct the URL for the WeatherAPI.com request
        String apiURL = API_URL + API_TOKEN + "&q=" + location + "&aqi=yes";

        Request request = new Request.Builder()
                .url(apiURL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                JSONObject loc = responseBody.getJSONObject("location");
                JSONObject air = responseBody.getJSONObject("air_quality");
                return AirQuality.builder()
                        .location(loc.getString("name"))
                        .co(air.getInt("co"))
                        .no2(air.getInt("no2"))
                        .o3(air.getInt("o3"))
                        .so2(air.getInt("so2"))
                        .pm2_5(air.getInt("pm2_5"))
                        .pm10(air.getInt("pm10"))
                        .build();

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}