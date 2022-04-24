package at.ac.fhcampuswien;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;

import java.io.IOException;

public class NewsApi {
    //key: 2f62c3bf2ba84f97adb6eaf246dccced
    //https://newsapi.org/v2/everything?q=bitcoin&apiKey=2f62c3bf2ba84f97adb6eaf246dccced

    OkHttpClient client = new OkHttpClient();

    public String request(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()){
            return response.body().string();
        }
    }

}
