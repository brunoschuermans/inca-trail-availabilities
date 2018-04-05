package be.schuermans.incatrailavailabilities.booking;

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
import static be.schuermans.incatrailavailabilities.TestConstants.BOOKING_FORM;
import static be.schuermans.incatrailavailabilities.TestConstants.EMAIL;
import static be.schuermans.incatrailavailabilities.booking.BookingForm.Tour.INCA_TRAIL_CLASSIC;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BookingRestControllerTest {

    @InjectMocks
    BookingRestController bookingRestController = new BookingRestController();

    @Mock
    Datastore datastore;

    @Mock
    MailService mailService;

    @Mock
    Template template;

    @Test
    public void postContact() throws Exception {
        bookingRestController.postContact(BOOKING_FORM);

        MailService.Message message = new MailService.Message(FROM, TO, "Booking Form Request: [john@gmail.com] Classic Inca Trail 4D/3N (1)", EMPTY);
        message.setHtmlBody("");
        message.setReplyTo("john@gmail.com");

        verify(datastore).put(refEq(BOOKING_FORM));
        verify(mailService).send(refEq(message));
    }
}