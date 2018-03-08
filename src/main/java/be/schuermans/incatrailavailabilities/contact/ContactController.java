package be.schuermans.incatrailavailabilities.contact;

import be.schuermans.incatrailavailabilities.appengine.Datastore;
import com.google.appengine.api.mail.MailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.io.StringWriter;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@RestController
@CrossOrigin
public class ContactController {

    private static final String TO = "bruno.schuermans@gmail.com, joel@southernperuexplorers.com";
    private static final String FROM = "Southern Peru Explorers <info@steady-course-191300.appspotmail.com>";
    private static final String SUBJECT = "Contact Form Request";

    @Autowired
    private Datastore datastore;

    @Autowired
    private MailService mailService;

    @Autowired
    private Template contactFormEmailTemplate;

    @PostMapping("/contact")
    public void postContact(@RequestBody @Valid ContactForm contactForm) throws Exception {
        datastore.put(contactForm);

        MailService.Message message = new MailService.Message(FROM, TO, SUBJECT, EMPTY);
        message.setHtmlBody(contactFormEmailTemplate(contactForm));
        message.setReplyTo(contactForm.getEmail());
        mailService.send(message);
    }

    private String contactFormEmailTemplate(ContactForm contactForm) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        contactFormEmailTemplate.process(contactForm, stringWriter);
        return stringWriter.toString();
    }
}
