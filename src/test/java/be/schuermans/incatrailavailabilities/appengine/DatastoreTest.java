package be.schuermans.incatrailavailabilities.appengine;

import be.schuermans.incatrailavailabilities.booking.BookingForm;
import be.schuermans.incatrailavailabilities.booking.BookingFormMapper;
import be.schuermans.incatrailavailabilities.booking.TravellerForm;
import be.schuermans.incatrailavailabilities.contact.ContactFormMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

import static be.schuermans.incatrailavailabilities.KnowUsFrom.GOOGLE;
import static be.schuermans.incatrailavailabilities.TestConstants.*;
import static be.schuermans.incatrailavailabilities.appengine.Datastore.BOOKING_FORM_ENTITY;
import static be.schuermans.incatrailavailabilities.appengine.Datastore.CONTACT_FORM_ENTITY;
import static be.schuermans.incatrailavailabilities.booking.BookingForm.Tour.INCA_TRAIL_CLASSIC;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DatastoreTest {

    @InjectMocks
    Datastore datastore = new Datastore();

    @Spy
    ContactFormMapper contactFormMapper = new ContactFormMapper();

    @Spy
    BookingFormMapper bookingFormMapper = new BookingFormMapper();

    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
        ReflectionTestUtils.setField(datastore, "datastoreService", datastoreService);
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }


    @Test
    public void get() {
    }

    @Test
    public void put_Availabilities() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void put_ContactForm() {
        datastore.put(CONTACT_FORM);

        Entity actual = datastoreService.prepare(new Query(CONTACT_FORM_ENTITY)).asSingleEntity();

        assertThat(actual.getProperty("name")).isEqualTo(NAME);
        assertThat(actual.getProperty("email")).isEqualTo(EMAIL);
        assertThat(actual.getProperty("knowUsFrom")).isEqualTo(GOOGLE.name());
        assertThat(actual.getProperty("message")).isEqualTo(MESSAGE);
    }

    @Test
    public void put_BookingForm() throws IOException {
        datastore.put(BOOKING_FORM);

        Entity actual = datastoreService.prepare(new Query(BOOKING_FORM_ENTITY)).asSingleEntity();

        assertThat(actual.getProperty("email")).isEqualTo(EMAIL);
        assertThat(actual.getProperty("tourStartDate")).isEqualTo(TOUR_START_DATE);
        assertThat(actual.getProperty("tourType")).isEqualTo(BookingForm.TourType.PRIVATE.name());
        assertThat(actual.getProperty("tour")).isEqualTo(INCA_TRAIL_CLASSIC.name());
        assertThat(actual.getProperty("hotel")).isEqualTo(HOTEL);
        assertThat(actual.getProperty("foodRestriction")).isEqualTo(FOOD_RESTRICTION);
        assertThat(actual.getProperty("anyInformation")).isEqualTo(ANY_INFORMATION);
        assertThat(actual.getProperty("knowUsFrom")).isEqualTo(GOOGLE.name());
        assertThat(deserializeTravellers((String) actual.getProperty("travellers")))
                .usingFieldByFieldElementComparator()
                .containsExactly(TRAVELLER_FORM);
    }

    @Test
    public void bookingFormToJson() throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(BOOKING_FORM));
    }

    private List<TravellerForm> deserializeTravellers(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<List<TravellerForm>>() {
        });
    }
}