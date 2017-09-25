package au.com.momenton.codechallenge.main;

import au.momenton.codechallenge.common.utils.CoverageIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "au.com.momenton.codechallenge.rest.api",
        "au.com.momenton.codechallenge.service",
        "au.momenton.codechallenge.dao"
})
@Configuration
public class CodeChallengeBootApplication extends SpringBootServletInitializer {

    @Override
    @CoverageIgnore
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CodeChallengeBootApplication.class);
    }

    @CoverageIgnore
    public static void main(String[] args) {
        SpringApplication.run(CodeChallengeBootApplication.class, args);
    }

    @Bean
    @CoverageIgnore
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    @CoverageIgnore
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    @CoverageIgnore
    public Jackson2ObjectMapperBuilderCustomizer addCustomBigDecimalDeserialization() {
        return new Jackson2ObjectMapperBuilderCustomizer(){
            @Override
            @CoverageIgnore
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder.deserializerByType(String.class, new JsonDeserializer<String>() {
                    @Override
                    @CoverageIgnore
                    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                            throws IOException, JsonProcessingException {
                        JsonNode node = jsonParser.readValueAsTree();
                        if (node.asText().isEmpty()) {
                            return null;
                        }
                        return node.asText();
                    }
                });
            }
        };
    }

    @Bean
    @CoverageIgnore
    public PropertiesFactoryBean applicationProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("application.properties"));
        return bean;
    }

    @Bean
    @CoverageIgnore
    public BasicDataSource applicationDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        try {
            Properties properties = this.applicationProperties().getObject();
            dataSource.setDriverClassName(properties.getProperty("database.driver"));
            dataSource.setUrl(properties.getProperty("database.url"));
            dataSource.setUsername(properties.getProperty("database.username"));
            dataSource.setPassword(properties.getProperty("database.password"));
            dataSource.setValidationQuery(properties.getProperty("database.validation"));
            dataSource.setMaxWait((Long.valueOf(properties.getProperty("database.wait"))));
            dataSource.setMaxActive((Integer.parseInt(properties.getProperty("database.maxActive"))));
            dataSource.setMaxIdle((Integer.parseInt(properties.getProperty("database.maxIdle"))));
            dataSource.setInitialSize((Integer.parseInt(properties.getProperty("database.initialConnectionPoolSize"))));
            dataSource.setAccessToUnderlyingConnectionAllowed(true);
            dataSource.setRemoveAbandoned(true);
            dataSource.setRemoveAbandonedTimeout(60);
            dataSource.setLogAbandoned(true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
