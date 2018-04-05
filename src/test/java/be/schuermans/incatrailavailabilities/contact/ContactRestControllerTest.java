package be.schuermans.incatrailavailabilities.contact;

import be.schuermans.incatrailavailabilities.appengine.Datastore;
import com.google.appengine.api.mail.MailService;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static be.schuermans.incatrailavailabilities.Constants.FROM;
import static be.schuermans.incatrailavailabilities.Constants.TO;
import static be.schuermans.incatrailavailabilities.TestConstants.CONTACT_FORM;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContactRestControllerTest {

    @InjectMocks
    ContactRestController contactRestController = new ContactRestController();

    @Mock
    Datastore datastore;

    @Mock
    MailService mailService;

    @Mock
    Template template;

    @Test
    public void postContact() throws Exception {
        contactRestController.postContact(CONTACT_FORM);

        MailService.Message message = new MailService.Message(FROM, TO, "Contact Form Request: John Doe", EMPTY);
        message.setHtmlBody("");
        message.setReplyTo("john@gmail.com");

        verify(datastore).put(refEq(CONTACT_FORM));
        verify(mailService).send(refEq(message));
    }
}