package be.schuermans.incatrailavailabilities.contact;

import be.schuermans.incatrailavailabilities.KnowUsFrom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private KnowUsFrom knowUsFrom;
    @NotNull
    private String message;
}
