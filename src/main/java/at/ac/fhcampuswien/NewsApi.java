package at.ac.fhcampuswien;

import at.ac.fhcampuswien.enums.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

public class NewsApi {
    //key: 2f62c3bf2ba84f97adb6eaf246dccced
    //https://newsapi.org/v2/
    //...everything?q=bitcoin&api...
    //Key=2f62c3bf2ba84f97adb6eaf246dccced

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private static final String API_key = "2f62c3bf2ba84f97adb6eaf246dccced";
    public static final String URL = "https://newsapi.org/v2/";
    public static CountryEnum countryEnum = CountryEnum.at;
    public static CategoryEnum categoryEnum;
    public static LanguageEnum languageEnum;
    public static EndpointEnum endpointEnum = EndpointEnum.everything;
    public static SortByEnum sortByEnum;
    public static String query = "bitcoin";


    public NewsResponse request() throws IOException {
        HttpUrl.Builder newURL = Objects.requireNonNull(HttpUrl.parse(URL)).newBuilder();
        newURL.addPathSegment(endpointEnum.getEndpoint());
        newURL.addQueryParameter("q", query);

        if (query != null) {
            newURL.addQueryParameter("q", query);
        }

        if (sortByEnum != null) {
            newURL.addQueryParameter("sortBy", sortByEnum.toString());
        }

        if (countryEnum != null) {
            newURL.addQueryParameter("country", countryEnum.toString());
        }

        if (categoryEnum != null) {
            newURL.addQueryParameter("category", categoryEnum.toString());
        }

        if (languageEnum != null) {
            newURL.addQueryParameter("language", languageEnum.toString());
        }

        newURL.addQueryParameter("apiKey", API_key);
        System.out.println(newURL.build());

        //url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=2f62c3bf2ba84f97adb6eaf246dccced";
        Request request = new Request.Builder()
                .url(newURL.build().toString())
                .build();

        try (Response response = client.newCall(request).execute()){
            NewsResponse newsResponse = gson.fromJson(response.body().string(), NewsResponse.class);
            return newsResponse;
        }
    }
}
