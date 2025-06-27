package Application.news.controller;

import Application.news.News;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class NewsController {

    private static final String NEWS_API_URL = "http://localhost:8081/api/news";
    private final Gson gson = new GsonBuilder().create();

    public List<News> getAllNews() throws Exception {
        HttpURLConnection conn = createConnection(NEWS_API_URL);

        int status = conn.getResponseCode();
        InputStream inputStream = (status >= 200 && status < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        String responseJson = readStream(inputStream);

        Type listType = new TypeToken<List<News>>() {}.getType();
        return gson.fromJson(responseJson, listType);
    }

    public List<News> searchNews(String keyword) throws Exception{
        String api = NEWS_API_URL + "/search?searchString="+keyword;
        HttpURLConnection conn = createConnection(api);

        int status = conn.getResponseCode();
        InputStream inputStream = (status >= 200 && status < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        String responseJson = readStream(inputStream);

        Type listType = new TypeToken<List<News>>() {}.getType();
        return gson.fromJson(responseJson, listType);
    }

    public List<News> savedNews(int userId) throws Exception{
        String api = NEWS_API_URL + "/save/"+userId;
        HttpURLConnection conn = createConnection(api);

        int status = conn.getResponseCode();
        InputStream inputStream = (status >= 200 && status < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        String responseJson = readStream(inputStream);

        Type listType = new TypeToken<List<News>>() {}.getType();
        return gson.fromJson(responseJson, listType);
    }

    private HttpURLConnection createConnection(String api) throws IOException {
        URL url = new URL(api);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(false); // GET doesn't need output
        return conn;
    }

    private String readStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder responseStr = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseStr.append(line.trim());
        }
        return responseStr.toString();
    }
}
