package higor.mybooks.api.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDto {
  public HttpStatus    status;
  public LocalDateTime timestamp;
  public String        message;

  public ErrorDto(HttpStatus status) {
    this.status = status;
    this.timestamp = LocalDateTime.now();
  }

  public ErrorDto(HttpStatus status, String message) {
    this(status);
    this.message = message;
  }
}
