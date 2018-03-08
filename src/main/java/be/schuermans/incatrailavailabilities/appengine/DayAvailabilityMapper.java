//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities.appengine;

import be.schuermans.incatrailavailabilities.NumberOfDays;
import com.google.appengine.api.datastore.Entity;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DayAvailabilityMapper {
    public DayAvailabilityMapper() {
    }

    public Entity map(int year, int month, int day, NumberOfDays numberOfDays, int availability) {
        Entity entity = new Entity(Datastore.AVAILABILITY_ENTITY, Joiner.on("").join(year, this.pad(month), new Object[]{this.pad(day), numberOfDays}));
        entity.setProperty("year", year);
        entity.setProperty("month", month);
        entity.setProperty("numberOfDays", numberOfDays.name());
        entity.setProperty("day", day);
        entity.setProperty("availability", availability);
        return entity;
    }

    private String pad(int number) {
        return StringUtils.leftPad(String.valueOf(number), 2, "0");
    }
}
