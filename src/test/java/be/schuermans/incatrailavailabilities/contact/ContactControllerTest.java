package be.schuermans.incatrailavailabilities.contact;

import be.schuermans.incatrailavailabilities.appengine.Datastore;
import com.google.appengine.api.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static be.schuermans.incatrailavailabilities.TestConstants.CONTACT_FORM;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @InjectMocks
    ContactController contactController = new ContactController();

    @Mock
    Datastore datastore;

    @Mock
    MailService mailService;

    @Test
    public void postContact() throws IOException {
        contactController.postContact(CONTACT_FORM);

        verify(datastore).put(refEq(CONTACT_FORM));
        verify(mailService).send(any(MailService.Message.class));
    }
}