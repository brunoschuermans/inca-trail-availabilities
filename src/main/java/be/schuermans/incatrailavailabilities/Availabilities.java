//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities;

import com.google.common.collect.Lists;

import java.util.List;

public class Availabilities {
    private List<MonthAvailabilities> availabilities = Lists.newArrayList();

    public Availabilities() {
    }

    public Availabilities add(MonthAvailabilities monthAvailabilities) {
        this.availabilities.add(monthAvailabilities);
        return this;
    }

    public Availabilities add(NumberOfDays numberOfDays, int year, int month, int day, int availability) {
        if (!this.availabilities.stream().anyMatch((a) -> {
            return a.getYear() == year && a.getMonth() == month;
        })) {
            this.add(new MonthAvailabilities(year, month));
        }

        MonthAvailabilities monthAvailabilities = (MonthAvailabilities)this.availabilities.stream().filter((a) -> {
            return a.getYear() == year && a.getMonth() == month;
        }).findAny().get();
        if (numberOfDays == NumberOfDays.TWO) {
            monthAvailabilities.add(numberOfDays, new DayAvailability(day, availability, 0));
        } else {
            monthAvailabilities.add(numberOfDays, new DayAvailability(day, 0, availability));
        }

        return this;
    }

    public List<MonthAvailabilities> getAvailabilities() {
        return this.availabilities;
    }

    public void setAvailabilities(List<MonthAvailabilities> availabilities) {
        this.availabilities = availabilities;
    }
}
