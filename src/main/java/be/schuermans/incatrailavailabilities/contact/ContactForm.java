package be.schuermans.incatrailavailabilities.contact;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String message;
}
