package devmax.dto;

import java.time.Instant;

public class ErrorResponse {
    private final String message;
    private final int status;
    private final Instant timestamp = Instant.now();

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() { return message; }
    public int getStatus() { return status; }
    public Instant getTimestamp() { return timestamp; }
}
