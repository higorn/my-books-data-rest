package higor.mybooks.api.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Base64;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JwtClientCredentialsAuthenticationFilterTest {

  private JwtClientCredentialsAuthenticationFilter filter;
  private MyAuthenticationManager authenticationManager;

  @BeforeEach
  void setUp() {
    authenticationManager = new MyAuthenticationManager();
    JwtConfig jwtConfig = new JwtConfig();
    jwtConfig.setTokenPrefix("Bearer");
    jwtConfig.setTokenExpirationAfterDays(14);
    jwtConfig.setSecretKey("secretsecretsecretsecretsecretsecretsecretsecretsecret");
    JwtSecretKey jwtSecretKey = new JwtSecretKey(jwtConfig);
    filter = new JwtClientCredentialsAuthenticationFilter(authenticationManager, jwtConfig, jwtSecretKey.secretKey());
  }

  @ParameterizedTest
  @MethodSource("provideUsernameAndPassword")
  void givenClientCredentialsInTheRequestBody_thenExtractTheCredentials_andAuthenticate(String username,
      String password) throws ServletException, IOException {
    // Given
    MockHttpServletRequest request = getHttpServletRequest(username, password);
    // When
    filter.doFilterInternal(request, new MockHttpServletResponse(), new MockFilterChain());
    // Then
    Authentication authentication = authenticationManager.authentication;
    assertNotNull(authentication);
    assertEquals(username, authentication.getPrincipal());
    assertEquals(password, authentication.getCredentials());
  }

  @Test
  void givenClientCredentialsInTheHeader_thenExtractTheCredentials_andAuthenticate()
      throws ServletException, IOException {
    // Given
    String username = "nicanor";
    String password = "1234";
    MockHttpServletRequest request = getHttpServletRequest(username, password);
    request.removeParameter("client_id");
    request.removeParameter("client_secret");
    request.addHeader("Authorization", "Basic "
        + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
    // When
    filter.doFilterInternal(request, new MockHttpServletResponse(), new MockFilterChain());
    // Then
    Authentication authentication = authenticationManager.authentication;
    assertNotNull(authentication);
    assertEquals(username, authentication.getPrincipal());
    assertEquals(password, authentication.getCredentials());
  }

  @Test
  void givenARequestWithoutGrantType_thenThrowsMissingGrantTypeException() {
    // Given
    MockHttpServletRequest request = getHttpServletRequest("nicanor", "1234");
    request.removeParameter("grant_type");
    // Then
    assertThrows(JwtClientCredentialsAuthenticationFilter.MissingGrantTypeException.class,
        () -> filter.doFilterInternal(request, new MockHttpServletResponse(), new MockFilterChain()));
  }

  @Test
  void givenARequestWithUnsupportedGrantType_thenThrowsUnsupportedGrantTypeException() {
    // Given
    MockHttpServletRequest request = getHttpServletRequest("nicanor", "1234");
    request.setParameter("grant_type", "");
    // Then
    assertThrows(JwtClientCredentialsAuthenticationFilter.UnsupportedGrantTypeException.class,
        () -> filter.doFilterInternal(request, new MockHttpServletResponse(), new MockFilterChain()));
  }

  private MockHttpServletRequest getHttpServletRequest(String username, String password) {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setRequestURI("/oauth/token");
    request.setMethod("POST");
    request.setParameter("grant_type", "client_credentials");
    request.setParameter("client_id", username);
    request.setParameter("client_secret", password);
    return request;
  }

  private static Stream<Arguments> provideUsernameAndPassword() {
    return Stream.of(
        Arguments.of("nicanor", "1234"),
        Arguments.of("protasio", "4321")
    );
  }

  private static class MyAuthenticationManager implements AuthenticationManager {
    public Authentication authentication;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      this.authentication = authentication;
      return authentication;
    }
  }
}