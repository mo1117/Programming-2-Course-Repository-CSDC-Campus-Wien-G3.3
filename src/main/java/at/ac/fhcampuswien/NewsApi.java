package at.ac.fhcampuswien;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;

import java.io.IOException;

public class NewsApi {
    //key: 2f62c3bf2ba84f97adb6eaf246dccced
    //https://newsapi.org/v2/
    //...everything?q=bitcoin&api...
    //Key=2f62c3bf2ba84f97adb6eaf246dccced

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();

    public NewsResponse request() throws IOException {
        String url = new String();
        //url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=2f62c3bf2ba84f97adb6eaf246dccced";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()){
            NewsResponse newsResponse = gson.fromJson(response.body().string(), NewsResponse.class);
            return newsResponse;
        }
    }
}
