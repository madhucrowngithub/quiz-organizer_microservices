package com.quiz.question_services.configuration.apidocs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import static com.quiz.question_services.commons.utils.Utils.isDefaultProfile;

@Slf4j
@Configuration
@Profile({"!test"})
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openAPI(Environment environment, @Value("${server.port}") String port) {
        //TODO adapt to new profiles later
        String url = "";
        if (isDefaultProfile(environment)) {
            url = String.format("http://%s:%s/", environment.getProperty("custom.host.ip", "localhost"), port);
        } else {
            url = environment.getProperty("custom.host.ip", "https://localhost:8080");
        }
        log.info("URL for APIDOCS {}", url);
        return new OpenAPI()
                .addServersItem(new Server().url(url))
                .info(new Info()
                        .title("Pulse Ingestion API")
                        .description("Documentation for Pulse Ingestion API")
                        .version("v1.0"));
    }
}



