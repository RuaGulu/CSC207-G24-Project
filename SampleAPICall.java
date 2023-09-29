import okhttp3.*;

import java.io.IOException;
import java.net.HttpURLConnection;

public class SampleAPICall {
    public static void main(String[] args) {
        // Here is using Alice's API Key.
        String apiKey = "ec9198023c8a4fd9a8904131232909";
        // Postal Code only need first three characters.
        String city = "M5G";

        OkHttpClient client = new OkHttpClient();

        // Construct the URL for the WeatherAPI.com request
        String apiURL = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;

        Request request = new Request.Builder()
                .url(apiURL)
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                System.out.println(responseBody);
            } else {
                System.out.println("Error: unable to retrive data from weatherapi.com. Response Code: " + response.code());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
