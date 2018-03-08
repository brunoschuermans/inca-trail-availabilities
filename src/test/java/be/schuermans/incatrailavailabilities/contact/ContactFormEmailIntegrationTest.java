package be.schuermans.incatrailavailabilities.contact;

import be.schuermans.incatrailavailabilities.IncaTrailAvailabilitiesApplication;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.StringWriter;

import static be.schuermans.incatrailavailabilities.TestConstants.CONTACT_FORM;
import static be.schuermans.incatrailavailabilities.TestConstants.EMAIL;
import static be.schuermans.incatrailavailabilities.TestConstants.MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IncaTrailAvailabilitiesApplication.class})
public class ContactFormEmailIntegrationTest {

    @Autowired
    Template template;

    @Test
    public void template() throws Exception {
        StringWriter stringWriter = new StringWriter();
        template.process(CONTACT_FORM, stringWriter);

        assertThat(stringWriter.toString()).contains(EMAIL);
        assertThat(stringWriter.toString()).contains(MESSAGE);
    }
}
