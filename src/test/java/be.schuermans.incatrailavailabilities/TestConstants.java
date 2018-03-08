package be.schuermans.incatrailavailabilities;

import be.schuermans.incatrailavailabilities.contact.ContactForm;

public interface TestConstants {

    String EMAIL = "john@gmail.com";
    String MESSAGE = "message";
    ContactForm CONTACT_FORM = ContactForm.builder()
            .email(EMAIL)
            .message(MESSAGE)
            .build();
}
