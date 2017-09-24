package au.com.momenton.codechallenge.rest.api;

import au.momenton.codechallenge.common.utils.CoverageIgnore;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WPerera on 9/23/2017.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_DESCRIPTION = "Security Token for Access";

    @CoverageIgnore
    @Bean
    public Docket api() {

        List<Parameter> globalOperationParams = new ArrayList<Parameter>();
        globalOperationParams.add(new ParameterBuilder()
                .name(AUTHORIZATION_HEADER)
                .description(AUTHORIZATION_HEADER_DESCRIPTION)
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(globalOperationParams)
                .select().apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .paths(PathSelectors.any())
                .build();
    }
}


