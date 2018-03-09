package be.schuermans.incatrailavailabilities;

import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.StringWriter;

import static be.schuermans.incatrailavailabilities.KnowUsFrom.GOOGLE;
import static be.schuermans.incatrailavailabilities.TestConstants.*;
import static be.schuermans.incatrailavailabilities.booking.BookingForm.Tour.INCA_TRAIL_CLASSIC;
import static be.schuermans.incatrailavailabilities.booking.BookingForm.TourType.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IncaTrailAvailabilitiesApplication.class})
public class TemplateIntegrationTest {

    @Autowired
    @Qualifier("contactFormEmailTemplate")
    Template contactFormEmailTemplate;

    @Autowired
    @Qualifier("bookingFormEmailTemplate")
    Template bookingFormEmailTemplate;

    @Test
    public void contactFormEmailTemplate() throws Exception {
        StringWriter stringWriter = new StringWriter();
        contactFormEmailTemplate.process(CONTACT_FORM, stringWriter);

        String actual = stringWriter.toString();

        assertThat(actual).contains(EMAIL);
        assertThat(actual).contains(MESSAGE);
    }

    @Test
    public void bookingFormEmailTemplate() throws Exception {
        StringWriter stringWriter = new StringWriter();
        bookingFormEmailTemplate.process(BOOKING_FORM, stringWriter);

        String actual = stringWriter.toString();

        assertThat(actual).contains(EMAIL);
        assertThat(actual).contains(INCA_TRAIL_CLASSIC.getDescription());
        assertThat(actual).containsIgnoringCase(PRIVATE.name());
        assertThat(actual).containsIgnoringCase(GOOGLE.name());
        assertThat(actual).contains(HOTEL);
        assertThat(actual).contains(FOOD_RESTRICTION);
        assertThat(actual).contains(ANY_INFORMATION);

        assertThat(actual).contains(FIRST_NAME);
        assertThat(actual).contains(LAST_NAME);
        assertThat(actual).contains(PASSPORT);
        assertThat(actual).contains(NATIONALITY);
        assertThat(actual).contains(BIRTH_DATE);
    }
}
