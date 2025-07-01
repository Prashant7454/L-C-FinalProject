package Application.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpClientUtil {

    public static String sendRequest(String api, String method, String jsonBody) throws IOException {
        HttpURLConnection conn = createConnection(api, method, jsonBody != null);

        if (jsonBody != null) {
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        int status = conn.getResponseCode();
        InputStream inputStream = (status >= 200 && status < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        return readStream(inputStream);
    }

    private static HttpURLConnection createConnection(String api, String method, boolean doOutput) throws IOException {
        URL url = new URL(api);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(doOutput);
        return conn;
    }

    private static String readStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder responseStr = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseStr.append(line.trim());
        }
        return responseStr.toString();
    }
}
