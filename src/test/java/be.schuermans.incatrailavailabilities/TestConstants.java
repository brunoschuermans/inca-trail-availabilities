package be.schuermans.incatrailavailabilities;

import be.schuermans.incatrailavailabilities.contact.ContactForm;

public interface TestConstants {

    String EMAIL = "john@gmail.com";
    String MESSAGE = "message";
    String NAME = "NAME";
    ContactForm CONTACT_FORM = ContactForm.builder()
            .name(NAME)
            .email(EMAIL)
            .message(MESSAGE)
            .build();
}
