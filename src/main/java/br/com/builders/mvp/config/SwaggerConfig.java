package br.com.builders.mvp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Value("${server.servlet.context-path}")
    private String contextPath;
	
	@Bean
	public OpenAPI springHearthStoneApi() {
		return new OpenAPI()
	               .addServersItem(new Server().url(contextPath))
	               .info(
	                       new Info()
	                               .title("Stores API")
	                               .description("Spring stores application")
	                               .version("v1.0.0")
	               );
	}
}
