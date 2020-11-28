package higor.mybooks.api.dto;

import higor.mybooks.api.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;

public class OAuthTokenResponse {
  public String access_token;
  public String token_type;
  public int    expires_in;
  public String scope;

  public OAuthTokenResponse(Authentication authenticate, JwtConfig jwtConfig, SecretKey secretKey) {
    access_token = getJwtToken(authenticate, jwtConfig.getTokenExpirationAfterDays(), secretKey);
    token_type = jwtConfig.getTokenPrefix();
    expires_in = jwtConfig.getTokenExpirationAfterDays();
    scope = authenticate.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining());
  }

  private String getJwtToken(Authentication authentication, Integer tokenExpirationAfterDays, SecretKey secretKey) {
    return Jwts.builder()
        .setSubject(authentication.getName())
        .claim("authorities", authentication.getAuthorities())
        .setIssuedAt(new Date())
        .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(tokenExpirationAfterDays)))
        .signWith(secretKey)
        .compact();
  }
}
