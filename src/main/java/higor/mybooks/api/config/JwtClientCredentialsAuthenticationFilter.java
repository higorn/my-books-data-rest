package higor.mybooks.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import higor.mybooks.api.dto.OAuthTokenResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

import static java.util.Optional.ofNullable;

public class JwtClientCredentialsAuthenticationFilter extends OncePerRequestFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtConfig             jwtConfig;
  private final SecretKey             secretKey;

  public JwtClientCredentialsAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig,
      SecretKey secretKey) {
    this.authenticationManager = authenticationManager;
    this.jwtConfig = jwtConfig;
    this.secretKey = secretKey;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    if ("/oauth/token".equals(request.getRequestURI())) {
      if (!request.getMethod().equals("POST")) {
        throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
      }

      String grantType = request.getParameter("grant_type");
      if (grantType == null)
        throw new MissingGrantTypeException(); // TODO: response with AuthError
      if (!"client_credentials".equals(grantType))
        throw new UnsupportedGrantTypeException();

      String username = request.getParameter("client_id");
      String password = request.getParameter("client_secret");
      if (username == null || password == null) {
        String authorization = ofNullable(request.getHeader(jwtConfig.getAuthorizationHeader()))
            .map(authHeader -> new String(Base64.getDecoder().decode(authHeader.replace("Basic ", ""))))
            .orElse(":");
        String[] credentials = authorization.split(":");
        username = credentials[0];
        password = credentials[1];
      }
      Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

      OAuthTokenResponse tokenResponse = new OAuthTokenResponse(authenticationManager.authenticate(authentication),
          jwtConfig, secretKey);
      response.setContentType(MediaType.APPLICATION_JSON.toString());
      response.getWriter().write(new ObjectMapper().writeValueAsString(tokenResponse));
      return;
    }

    filterChain.doFilter(request, response);
  }

  public static class MissingGrantTypeException extends RuntimeException {
  }

  public static class UnsupportedGrantTypeException extends RuntimeException {
  }
}
