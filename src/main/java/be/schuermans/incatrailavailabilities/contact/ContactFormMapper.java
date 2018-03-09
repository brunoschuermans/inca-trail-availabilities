//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities.contact;

import com.google.appengine.api.datastore.Entity;
import org.springframework.stereotype.Component;

import static be.schuermans.incatrailavailabilities.appengine.Datastore.CONTACT_FORM_ENTITY;

@Component
public class ContactFormMapper {

    public Entity map(ContactForm contactForm) {
        Entity entity = new Entity(CONTACT_FORM_ENTITY);
        entity.setProperty("name", contactForm.getName());
        entity.setProperty("email", contactForm.getEmail());
        entity.setProperty("knowUsFrom", contactForm.getKnowUsFrom().name());
        entity.setProperty("message", contactForm.getMessage());

        return entity;
    }

}
