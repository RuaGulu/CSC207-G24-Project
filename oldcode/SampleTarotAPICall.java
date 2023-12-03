import okhttp3.*;

import java.io.IOException;
import java.util.function.ToIntFunction;

public class SampleTarotAPICall {
    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();

        // Draw one card from the deck. Construct an api link from tarot api.
        String apiURL = "https://tarot-api-3hv5.onrender.com/api/v1/cards/random?n=1";

            Request request = new Request.Builder()
                    .url(apiURL)
                    .build();
            try {
                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String responseBody = response.body().string();

                    System.out.println(responseBody);
                } else {
                    System.out.println("Error: unable to retrive data from tarot-api. Response Code: " + response.code());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }