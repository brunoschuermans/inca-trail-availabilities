package be.schuermans.incatrailavailabilities.contact;

import be.schuermans.incatrailavailabilities.appengine.Datastore;
import com.google.appengine.api.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin
public class ContactController {

    private static final String TO = "bruno.schuermans@gmail.com";
    private static final String FROM = "info@southernperuexplorers.com";
    private static final String SUBJECT = "[Contact Form]";

    @Autowired
    private Datastore datastore;

    @Autowired
    private MailService mailService;

    @PostMapping("/contact")
    public void postContact(@RequestBody @Valid ContactForm contactForm) throws IOException {
        datastore.put(contactForm);
        mailService.send(new MailService.Message(FROM, TO, SUBJECT, contactForm.toString()));
    }
}
