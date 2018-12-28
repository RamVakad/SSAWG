package sswag.configuration;

import java.util.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)//
        .select()//
        .apis(RequestHandlerSelectors.any())//
        .paths(Predicates.not(PathSelectors.regex("/error")))//
        .build()//
        .apiInfo(metadata())//
        .useDefaultResponseMessages(false)//
        .securitySchemes(new ArrayList<>(Collections.singletonList(new ApiKey("Bearer %token", "Authorization", "Header"))))//
            .tags(new Tag("users", "Operations about users"))//
            .tags(new Tag("patterns", "Operations about patterns"))//
        .tags(new Tag("ping", "Just a ping"))//
        .genericModelSubstitutes(Optional.class);

  }

  private ApiInfo metadata() {
    return new ApiInfoBuilder()//
        .title("SSWAG API")//
        .description(
            "This is a the back-end service for the SSAWG application. It uses JWT for authentication & role management. Default Credentials: `client`, `approver` or `admin`  (passwords are same as username) for testing purposes. Once you have successfully logged in and obtained the JWT, you should click on the right top button `Authorize` and introduce it with the prefix \"Bearer \".")//
        .version("1.0.0")//
        .contact(new Contact(null, null, "REMOVED@gmail.com"))//
        .build();
  }

}
