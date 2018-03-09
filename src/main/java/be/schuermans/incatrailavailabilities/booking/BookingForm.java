package be.schuermans.incatrailavailabilities.booking;

import be.schuermans.incatrailavailabilities.KnowUsFrom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingForm {

    public enum TourType {
        PRIVATE,
        GROUP
    }

    public enum Tour {

        INCA_TRAIL_ULTIMATE("Ultimate Inca Trail 8D/7N"),
        INCA_TRAIL_TRAILBLAZING("Trailblazing Inca Trail 5D/4N"),
        INCA_TRAIL_CLASSIC("Classic Inca Trail 4D/3N"),
        INCA_TRAIL_TASTE("A Taste of Inca Trail 2D/1N"),
        BEST_OF_PERU("The Best of Peru 12D/11N"),
        TAMBOPATA_JUNGLE("Tambopata Jungle 4D/3N"),
        SALKANTAY("Salkantay Trek 5D/4N"),
        LARES("Lares Trek 4D/3N"),
        EXTREME_ADVENTURE("Extreme Adventure to Machu Picchu 4D/3N"),
        CHOQUEQUIRAO("Choquequirao Trek 5D/4N"),
        AUSANGATE("Ausangate Trek 5D/4N");

        private final String description;

        Tour(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    @NotNull
    private String email;
    @NotNull
    private String tourStartDate;
    @NotNull
    private TourType tourType;
    @NotNull
    private Tour tour;
    @NotNull
    private String hotel;
    private String foodRestriction;
    private String anyInformation;
    @NotNull
    private KnowUsFrom knowUsFrom;
    private List<TravellerForm> travellerForms;

}
