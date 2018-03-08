package be.schuermans.incatrailavailabilities.appengine;

import be.schuermans.incatrailavailabilities.contact.ContactFormMapper;
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

import static be.schuermans.incatrailavailabilities.TestConstants.*;
import static be.schuermans.incatrailavailabilities.appengine.Datastore.CONTACT_FORM_ENTITY;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DatastoreTest {

    @InjectMocks
    Datastore datastore = new Datastore();

    @Spy
    ContactFormMapper contactFormMapper = new ContactFormMapper();

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
        assertThat(actual.getProperty("message")).isEqualTo(MESSAGE);
    }
}