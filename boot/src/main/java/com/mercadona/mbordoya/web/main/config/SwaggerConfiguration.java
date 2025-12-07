package com.mercadona.mbordoya.web.main.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI swaggerOpenApiConfig() {
    return new OpenAPI()
        .components(getComponents())
        .info(getInfo());
  }

  private static Components getComponents() {
    return new Components()
        .addSecuritySchemes("adfs", getOAuth2SecuritySchema());
  }

  private static Info getInfo() {
    return new Info()
        .title("PRUEBA T")
        .description("PRUEBA T")
        .version("v1.0.0");
  }

  private static SecurityScheme getOAuth2SecuritySchema() {
    return new SecurityScheme()
        .type(SecurityScheme.Type.OAUTH2)
        .flows(new OAuthFlows()
            .implicit(new OAuthFlow().authorizationUrl("").scopes(new Scopes())));
  }
}
