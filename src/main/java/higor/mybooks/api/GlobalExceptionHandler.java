package higor.mybooks.api;

import higor.mybooks.api.config.JwtClientCredentialsAuthenticationFilter;
import higor.mybooks.api.dto.ErrorDto;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    return ResponseEntity.status(status).body(new ErrorDto(status, e.getMessage()));
  }

  @ExceptionHandler({ ConversionFailedException.class, IllegalArgumentException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorDto> handleBadRequest(Exception e) {
    return handleExceptionWithContent(e, HttpStatus.BAD_REQUEST);
  }

/*
  @ExceptionHandler({ DuplicatedEntryException.class })
  public ResponseEntity<ErrorDto> handleConflict(Exception e) {
    return handleExceptionWithContent(e, HttpStatus.CONFLICT);
  }
*/

  @ExceptionHandler({ JwtClientCredentialsAuthenticationFilter.MissingGrantTypeException.class })
  public ResponseEntity<Object> handleMissingGrantType(Exception e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthError("invalid_request", "Missing grant type"));
  }

  @ExceptionHandler({ JwtClientCredentialsAuthenticationFilter.UnsupportedGrantTypeException.class })
  public ResponseEntity<Object> handleUnsupportedGrantType(Exception e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthError("unsupported_grant_type",
        "Unsupported grant type: client_credential"));
  }

  private ResponseEntity<ErrorDto> handleExceptionWithContent(Exception e, HttpStatus status) {
    return ResponseEntity.status(status).body(new ErrorDto(status, e.getMessage()));
  }

  public static class AuthError {
    public String error;
    public String error_description;

    public AuthError(String error, String error_description) {
      this.error = error;
      this.error_description = error_description;
    }
  }
}
