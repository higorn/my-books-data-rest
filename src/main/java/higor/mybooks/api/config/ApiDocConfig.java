package higor.mybooks.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class ApiDocConfig {

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("v1")
          .select()
//        .apis(not(withClassAnnotation(CustomIgnore.class)))
//          .paths(paths())
          .build()
        .apiInfo(apiInfo())
        .tags(new Tag("Books", "Repository for book entities."));
  }

  private Predicate<String> paths() {
    return regex("/v1/books.*")
        .or(regex("/v1/users.*"))
        .and(regex("/v1/users/.*/books/.*")).negate()
        .and(regex("/v1/books/.*/users/.*")).negate();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("My books API")
        .description("API to provide information to the My books app")
        .version("1.0")
        .termsOfServiceUrl("Terms of service URL")
        .contact(new Contact("Higor Wuttke Nunes", "", "higorn@gmail.com"))
        .license("License")
        .licenseUrl("License URL")
        .build();
  }
}
