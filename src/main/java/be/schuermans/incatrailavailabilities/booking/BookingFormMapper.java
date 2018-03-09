//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.Entity;
import org.springframework.stereotype.Component;

import static be.schuermans.incatrailavailabilities.appengine.Datastore.BOOKING_FORM_ENTITY;

@Component
public class BookingFormMapper {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Entity map(BookingForm bookingForm) {
        Entity entity = new Entity(BOOKING_FORM_ENTITY);
        entity.setProperty("email", bookingForm.getEmail());
        entity.setProperty("tourStartDate", bookingForm.getTourStartDate());
        entity.setProperty("tourType", bookingForm.getTourType().name());
        entity.setProperty("tour", bookingForm.getTour().name());
        entity.setProperty("hotel", bookingForm.getHotel());
        entity.setProperty("foodRestriction", bookingForm.getFoodRestriction());
        entity.setProperty("anyInformation", bookingForm.getAnyInformation());
        entity.setProperty("knowUsFrom", bookingForm.getKnowUsFrom().name());
        try {
            entity.setProperty("travellers", objectMapper.writeValueAsString(bookingForm.getTravellerForms()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }

}
