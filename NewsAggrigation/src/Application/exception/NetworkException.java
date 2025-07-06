package Application.exception;

public class NetworkException extends NewsAggrigationException {
    private int statusCode;
    private String url;

    public NetworkException(String message) {
        super(message, "NETWORK_ERROR");
    }

    public NetworkException(String message, String url) {
        super(message, "NETWORK_ERROR");
        this.url = url;
    }

    public NetworkException(String message, String url, int statusCode) {
        super(message, "NETWORK_ERROR");
        this.url = url;
        this.statusCode = statusCode;
    }

    public NetworkException(String message, String errorCode, String url, int statusCode) {
        super(message, errorCode);
        this.url = url;
        this.statusCode = statusCode;
    }

    public NetworkException(String message, String url, Throwable cause) {
        super(message, "NETWORK_ERROR", cause);
        this.url = url;
    }

    public NetworkException(String message, Throwable cause) {
        super(message, "NETWORK_ERROR", cause);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getUrl() {
        return url;
    }
} 