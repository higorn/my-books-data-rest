package higor.mybooks.config;

import higor.mybooks.api.config.JwtClientCredentialsAuthenticationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.stream.Stream;

class JwtClientCredentialsAuthenticationFilterTest {

  private JwtClientCredentialsAuthenticationFilter filter;

  @BeforeEach
  void setUp() {
    filter = new JwtClientCredentialsAuthenticationFilter(new MyAuthenticationManager(), null, null);
  }

  @ParameterizedTest
  @MethodSource("provideUsernameAndPassword")
  void givenARequestWithClientCredentials_thenExtractTheCredentials_andAuthenticate(String username, String password) {
    // Given
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setParameter("grant_type", "client_credentials");
    request.setParameter("client_id", username);
    request.setParameter("client_secret", password);
    // When
//    Authentication authentication = filter.attemptAuthentication(request, new MockHttpServletResponse());
    // Then
//    assertNotNull(authentication);
//    assertEquals(username, authentication.getPrincipal());
//    assertEquals(password, authentication.getCredentials());
  }

  @Test
  void givenARequestWithoutGrantType_thenThrowsMissingGrantTypeException() {
    // Given
    String username = "nicanor";
    String password = "1234";
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setParameter("client_id", username);
    request.setParameter("client_secret", password);
    // Then
//    assertThrows(JwtClientCredentialsAuthenticationFilter.MissingGrantTypeException.class,
//        () -> filter.attemptAuthentication(request, new MockHttpServletResponse()));
  }

  @Test
  void givenARequestWithUnsupportedGrantType_thenThrowsUnsupportedGrantTypeException() {
    // Given
    String username = "nicanor";
    String password = "1234";
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setParameter("grant_type", "");
    request.setParameter("client_id", username);
    request.setParameter("client_secret", password);
    // Then
//    assertThrows(JwtClientCredentialsAuthenticationFilter.UnsupportedGrantTypeException.class,
//        () -> filter.attemptAuthentication(request, new MockHttpServletResponse()));
  }

  private static Stream<Arguments> provideUsernameAndPassword() {
    return Stream.of(
        Arguments.of("nicanor", "1234"),
        Arguments.of("protasio", "4321")
    );
  }

  private static class MyAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      return authentication;
    }
  }
}