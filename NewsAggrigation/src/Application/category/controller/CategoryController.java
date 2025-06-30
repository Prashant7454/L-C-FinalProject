package Application.category.controller;

import Application.category.Category;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CategoryController {
    private static final String CATEGORY_API_URL = "http://localhost:8081/api/categories";
    private final Gson gson = new GsonBuilder().create();

    public Category addCategory(Category category) throws Exception {
        HttpURLConnection conn = createConnection(CATEGORY_API_URL, "POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = gson.toJson(category).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int status = conn.getResponseCode();
        InputStream inputStream = (status >= 200 && status < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        String responseJson = readStream(inputStream);
        return gson.fromJson(responseJson, Category.class);
    }

    // Get all categories
    public List<Category> getAllCategories() throws Exception {
        HttpURLConnection conn = createConnection(CATEGORY_API_URL, "GET");

        int status = conn.getResponseCode();
        InputStream inputStream = (status >= 200 && status < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        String responseJson = readStream(inputStream);

        Type listType = new TypeToken<List<Category>>() {}.getType();
        return gson.fromJson(responseJson, listType);
    }

    // Get category by ID
    public Category getCategoryById(int id) throws Exception {
        HttpURLConnection conn = createConnection(CATEGORY_API_URL + "/" + id, "GET");

        int status = conn.getResponseCode();
        InputStream inputStream = (status >= 200 && status < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        String responseJson = readStream(inputStream);
        return gson.fromJson(responseJson, Category.class);
    }

    private HttpURLConnection createConnection(String api, String requestMethod) throws IOException {
        URL url = new URL(api);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true); // GET doesn't need output
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
