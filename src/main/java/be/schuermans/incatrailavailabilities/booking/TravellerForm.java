package be.schuermans.incatrailavailabilities.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravellerForm {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String passport;
    @NotNull
    private String nationality;
    @NotNull
    private String birthDate;

}
