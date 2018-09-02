package com.hotel.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket hotelAPIDocket()
    {
        Parameter authorizationParam =
                new ParameterBuilder()
                        .name("Authorization")
                        .description("This is the api identification key between the client and the server, " +
                                "To obtain a valid token you have to login with your credentials via POST /auth/login")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .build();
        List<Parameter> parametersList = new ArrayList<>();
        parametersList.add(authorizationParam);

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parametersList)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hotel"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfo(
                "Hotel Reservation Service Demo",
                "This is to demonstrate how to implement a secure stateless service using spring boot and jwt, \n" +
                        "using an example of a hotel and users who can reserve. ",
                "0.0.1-SNAPSHOT",
                "urn:tos",
                new Contact("Mostafa Omar", "www.linkedin.com/in/mgomar", "mostafa.ghany.omar@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
