package com.server.NewsAggrigationServer.util;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import static org.junit.jupiter.api.Assertions.*;

class ApiUtilisTest {
    static HttpServer server;
    static int port = 18081;
    static String baseUrl;

    @BeforeAll
    static void setupServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/test", new TestHandler());
        server.start();
        baseUrl = "http://localhost:" + port + "/test";
    }

    @AfterAll
    static void stopServer() {
        server.stop(0);
    }

    static class TestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "{\"message\":\"ok\"}";
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        }
    }

    @Test
    void testBuildUrl_withParams() {
        Map<String, String> params = new HashMap<>();
        params.put("foo", "bar");
        params.put("baz", "qux");
        String url = ApiUtilis.buildUrl("http://localhost", params);
        assertTrue(url.contains("foo=bar"));
        assertTrue(url.contains("baz=qux"));
        assertTrue(url.startsWith("http://localhost?"));
    }

    @Test
    void testBuildUrl_noParams() {
        String url = ApiUtilis.buildUrl("http://localhost", null);
        assertEquals("http://localhost", url);
    }

    @Test
    void testGet_withParams() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("foo", "bar");
        HttpResponse<String> response = ApiUtilis.get(baseUrl, params);
        assertEquals(200, response.statusCode());
        assertEquals("{\"message\":\"ok\"}", response.body());
    }

    @Test
    void testGet_withParamsAndHeaders() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("foo", "bar");
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Test", "true");
        HttpResponse<String> response = ApiUtilis.get(baseUrl, params, headers);
        assertEquals(200, response.statusCode());
        assertEquals("{\"message\":\"ok\"}", response.body());
    }
} 