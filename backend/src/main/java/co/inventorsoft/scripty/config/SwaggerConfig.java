package co.inventorsoft.scripty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Autowired
    Environment environment;

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                environment.getProperty("api.title"),
                environment.getProperty("api.desc"),
                environment.getProperty("api.version"),
                environment.getProperty("api.terms"),
                new Contact(environment.getProperty("api.contact.name"),
                        environment.getProperty("api.contact.url"),
                        environment.getProperty("api.contact.email")),
                "",
                "",
                Collections.emptyList()
        );
    }

}
