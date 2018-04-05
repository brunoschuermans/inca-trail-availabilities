package be.schuermans.incatrailavailabilities.contact;

import be.schuermans.incatrailavailabilities.appengine.Datastore;
import be.schuermans.incatrailavailabilities.booking.BookingForm;
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
public class ContactRestController {

    private static final String SUBJECT = "Contact Form Request: ${name}";

    @Autowired
    private Datastore datastore;

    @Autowired
    private MailService mailService;

    @Autowired
    @Qualifier("contactFormEmailTemplate")
    private Template contactFormEmailTemplate;

    @PostMapping("/contact")
    public void postContact(@RequestBody @Valid ContactForm contactForm) throws Exception {
        datastore.put(contactForm);

        MailService.Message message = new MailService.Message(FROM, TO, subject(contactForm), EMPTY);
        message.setHtmlBody(contactFormEmailTemplate(contactForm));
        message.setReplyTo(contactForm.getEmail());
        mailService.send(message);
    }

    private String contactFormEmailTemplate(ContactForm contactForm) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        contactFormEmailTemplate.process(contactForm, stringWriter);
        return stringWriter.toString();
    }

    private String subject(ContactForm contactForm) {
        return new StrSubstitutor(variables(contactForm)).replace(SUBJECT);
    }

    private ImmutableMap<String, String> variables(ContactForm contactForm) {
        return ImmutableMap.<String, String>builder()
                .put("name", contactForm.getName())
                .build();
    }

}
