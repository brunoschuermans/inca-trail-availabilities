package be.schuermans.incatrailavailabilities.booking;

import be.schuermans.incatrailavailabilities.appengine.Datastore;
import com.google.appengine.api.mail.MailService;
import com.google.common.collect.ImmutableMap;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.io.StringWriter;

import static be.schuermans.incatrailavailabilities.Constants.FROM;
import static be.schuermans.incatrailavailabilities.Constants.TO;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@RestController
@CrossOrigin
public class BookingRestController {

    private static final String SUBJECT = "Booking Form Request: [${email}] ${tour} (${persons})";

    @Autowired
    private Datastore datastore;

    @Autowired
    private MailService mailService;

    @Autowired
    @Qualifier("bookingFormEmailTemplate")
    private Template bookingFormEmailTemplate;

    @PostMapping("/booking")
    public void postContact(@RequestBody @Valid BookingForm bookingForm) throws Exception {
        datastore.put(bookingForm);

        MailService.Message message = new MailService.Message(FROM, TO, subject(bookingForm), EMPTY);
        message.setHtmlBody(bookingFormEmailTemplate(bookingForm));
        message.setReplyTo(bookingForm.getEmail());
        mailService.send(message);
    }

    private String bookingFormEmailTemplate(BookingForm bookingForm) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        bookingFormEmailTemplate.process(bookingForm, stringWriter);
        return stringWriter.toString();
    }

    private String subject(BookingForm bookingForm) {
        return new StrSubstitutor(variables(bookingForm)).replace(SUBJECT);
    }

    private ImmutableMap<String, String> variables(BookingForm bookingForm) {
        return ImmutableMap.<String, String>builder()
                .put("email", bookingForm.getEmail())
                .put("tour", bookingForm.getTour().getDescription())
                .put("persons", String.valueOf(bookingForm.getTravellerForms().size()))
                .build();
    }
}
