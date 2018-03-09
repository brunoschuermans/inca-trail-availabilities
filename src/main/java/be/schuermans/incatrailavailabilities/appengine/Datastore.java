//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities.appengine;

import be.schuermans.incatrailavailabilities.Availabilities;
import be.schuermans.incatrailavailabilities.CurrentDate;
import be.schuermans.incatrailavailabilities.NumberOfDays;
import be.schuermans.incatrailavailabilities.booking.BookingForm;
import be.schuermans.incatrailavailabilities.booking.BookingFormMapper;
import be.schuermans.incatrailavailabilities.contact.ContactForm;
import be.schuermans.incatrailavailabilities.contact.ContactFormMapper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions.Builder;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Datastore {

    public static String AVAILABILITY_ENTITY = "Availability";
    public static String CONTACT_FORM_ENTITY = "ContactForm";
    public static String BOOKING_FORM_ENTITY = "BookingForm";

    @Autowired
    private DatastoreService datastoreService;

    @Autowired
    private DayAvailabilityMapper dayAvailabilityMapper;

    @Autowired
    private CurrentDate currentDate;

    @Autowired
    private ContactFormMapper contactFormMapper;

    @Autowired
    private BookingFormMapper bookingFormMapper;

    public Availabilities get() {
        Availabilities availabilities = new Availabilities();
        this.datastoreService.prepare((new Query(AVAILABILITY_ENTITY)).setFilter(
                new FilterPredicate("year", FilterOperator.GREATER_THAN_OR_EQUAL, this.currentDate.year())))
                .asList(Builder.withDefaults())
                .forEach((entity) -> availabilities.add(NumberOfDays.valueOf((String)entity.getProperty("numberOfDays")), ((Long)entity.getProperty("year")).intValue(), ((Long)entity.getProperty("month")).intValue(), ((Long)entity.getProperty("day")).intValue(), ((Long)entity.getProperty("availability")).intValue()));

        return availabilities;
    }

    public void put(int year, int month, int day, NumberOfDays numberOfDays, int availability) {
        datastoreService.put(dayAvailabilityMapper.map(year, month, day, numberOfDays, availability));
    }

    public void delete() {
        List<Key> keys = datastoreService
                .prepare((new Query(AVAILABILITY_ENTITY)).setKeysOnly())
                .asList(Builder.withDefaults())
                .stream()
                .map(Entity::getKey)
                .collect(Collectors.toList());

        datastoreService.delete(keys);
    }

    public void put(ContactForm contactForm) {
        datastoreService.put(contactFormMapper.map(contactForm));
    }

    public void put(BookingForm bookingForm) {
        datastoreService.put(bookingFormMapper.map(bookingForm));
    }
}
