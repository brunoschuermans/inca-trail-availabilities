package be.schuermans.incatrailavailabilities.freemarker;

import freemarker.template.Template;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FreeMarkerConfiguration {

    @Bean
    freemarker.template.Configuration configuration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration();
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        return configuration;
    }

    @Bean(name = "contactFormEmailTemplate")
    Template contactFormEmailTemplate(freemarker.template.Configuration configuration) throws IOException {
        return configuration.getTemplate("ContactFormEmail.ftl");
    }

    @Bean(name = "bookingFormEmailTemplate")
    Template bookingFormEmailTemplate(freemarker.template.Configuration configuration) throws IOException {
        return configuration.getTemplate("BookingFormEmail.ftl");
    }
}
