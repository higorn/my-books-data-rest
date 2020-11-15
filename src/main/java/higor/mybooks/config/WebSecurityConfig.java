package higor.mybooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Value("${spring.security.oauth2.resourceserver.opaque-token.introspection-uri}")
  String introspectionUri;
  @Value("${spring.security.oauth2.resourceserver.opaque-token.client-id}")
  String clientId;
  @Value("${spring.security.oauth2.resourceserver.opaque-token.client-secret}")
  String clientSecret;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("nicanor").password(passwordEncoder.encode("1234")).roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
//        .requestMatchers()
//          .antMatchers("/**", "/login", "/oauth/authorize")
//          .and()
        .authorizeRequests()
          .antMatchers("/oauth/token").permitAll()
          .antMatchers("/login").permitAll()
        .anyRequest().authenticated()
        .and().formLogin().permitAll()
        .and().csrf().disable()
        .oauth2ResourceServer().opaqueToken(token -> token.introspectionUri(this.introspectionUri)
            .introspectionClientCredentials(this.clientId, this.clientSecret));
//        .oauth2ResourceServer().jwt();
//        .and()
//        .oauth2Login();

//    http.cors().disable().authorizeRequests()
//        .antMatchers("/v1", "/v1/explorer/**").permitAll()
//        .antMatchers(HttpMethod.GET, "/v1/books").permitAll()
/*
    http.antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/v1").permitAll()
        .anyRequest().authenticated()
        .and()
        .oauth2Login();
*/
//        .and()
//        .oauth2ResourceServer().jwt();
//        .jwtAuthenticationConverter(new JwtGroupsAuthenticationConverter());
  }

}
