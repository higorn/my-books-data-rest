package higor.mybooks.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwtUsernameAndPasswordAuthenticationFilterTest {

  private JwtUsernameAndPasswordAuthenticationFilter filter;

  @BeforeEach
  void setUp() {
    filter = new JwtUsernameAndPasswordAuthenticationFilter(new MyAuthenticationManager(), null, null);
  }

  @ParameterizedTest
  @MethodSource("provideUsernameAndPassword")
  void givenARequestWithClientCredentials_thenExtractTheCredentials_andAuthenticate(String username, String password) {
    // Given
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setParameter("client_id", username);
    request.setParameter("client_secret", password);
    // When
    Authentication authentication = filter.attemptAuthentication(request, new MockHttpServletResponse());
    // Then
    assertNotNull(authentication);
    assertEquals(username, authentication.getPrincipal());
    assertEquals(password, authentication.getCredentials());
  }

  @Test
  void givenARequestWithoutGrantType_thenExtractTheUserAndPassword_andAuthenticate(String username, String password) {
    // Given
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setParameter("client_id", username);
    request.setParameter("client_secret", password);
    // When
    Authentication authentication = filter.attemptAuthentication(request, new MockHttpServletResponse());
    // Then
    assertNotNull(authentication);
    assertEquals(username, authentication.getPrincipal());
    assertEquals(password, authentication.getCredentials());
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