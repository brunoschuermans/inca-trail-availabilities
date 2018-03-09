package be.schuermans.incatrailavailabilities;

import be.schuermans.incatrailavailabilities.booking.BookingForm;
import be.schuermans.incatrailavailabilities.booking.TravellerForm;
import be.schuermans.incatrailavailabilities.contact.ContactForm;

import static be.schuermans.incatrailavailabilities.KnowUsFrom.GOOGLE;
import static be.schuermans.incatrailavailabilities.booking.BookingForm.Tour.INCA_TRAIL_CLASSIC;
import static be.schuermans.incatrailavailabilities.booking.BookingForm.TourType.PRIVATE;
import static com.google.common.collect.Lists.newArrayList;

public interface TestConstants {

    String EMAIL = "john@gmail.com";
    String MESSAGE = "message";
    String NAME = "NAME";
    ContactForm CONTACT_FORM = ContactForm.builder()
            .name(NAME)
            .email(EMAIL)
            .knowUsFrom(GOOGLE)
            .message(MESSAGE)
            .build();

    String FIRST_NAME = "John";
    String LAST_NAME = "Doe";
    String PASSPORT = "EN12345";
    String NATIONALITY = "USA";
    String BIRTH_DATE = "20/01/1980";
    TravellerForm TRAVELLER_FORM = TravellerForm.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .passport(PASSPORT)
            .nationality(NATIONALITY)
            .birthDate(BIRTH_DATE)
            .build();

    String TOUR_START_DATE = "2018-01-02";
    String HOTEL = "Wild Rover";
    String FOOD_RESTRICTION = "vegan";
    String ANY_INFORMATION = "any information";
    BookingForm BOOKING_FORM = BookingForm.builder()
            .email(EMAIL)
            .tourStartDate(TOUR_START_DATE)
            .tourType(PRIVATE)
            .tour(INCA_TRAIL_CLASSIC)
            .hotel(HOTEL)
            .foodRestriction(FOOD_RESTRICTION)
            .anyInformation(ANY_INFORMATION)
            .knowUsFrom(GOOGLE)
            .travellerForms(newArrayList(TRAVELLER_FORM))
            .build();
}
