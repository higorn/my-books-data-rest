package higor.mybooks.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtConfig jwtConfig;
  private final SecretKey secretKey;

  public WebSecurityConfig(JwtConfig jwtConfig, SecretKey secretKey) {
    this.jwtConfig = jwtConfig;
    this.secretKey = secretKey;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("nicanor").password(passwordEncoder().encode("1234")).roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .addFilterAfter(new JwtClientCredentialsAuthenticationFilter(authenticationManager(), jwtConfig, secretKey),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtClientCredentialsAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers("/oauth/token").permitAll()
        .antMatchers("/actuator/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}
