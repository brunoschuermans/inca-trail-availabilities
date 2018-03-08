package be.schuermans.incatrailavailabilities.freemarker;

import freemarker.template.Template;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FreeMarkerConfiguration {

    @Bean
    freemarker.template.Configuration configuration() {
        return new freemarker.template.Configuration();
    }

    @Bean
    Template contactFormEmailTemplate(freemarker.template.Configuration configuration) throws IOException {
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        return configuration.getTemplate("ContactFormEmail.ftl");
    }
}
