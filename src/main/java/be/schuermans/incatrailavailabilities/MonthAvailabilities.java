//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities;

import com.google.common.collect.Lists;
import java.util.List;

public class MonthAvailabilities {
    private int year;
    private int month;
    private List<DayAvailability> dayAvailabilities = Lists.newArrayList();

    public MonthAvailabilities() {
    }

    public MonthAvailabilities(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public MonthAvailabilities add(DayAvailability dayAvailability) {
        this.dayAvailabilities.add(dayAvailability);
        return this;
    }

    public MonthAvailabilities add(NumberOfDays numberOfDays, DayAvailability anotherDayAvailability) {
        if (!this.dayAvailabilities.stream().anyMatch((d) -> {
            return d.getDay() == anotherDayAvailability.getDay();
        })) {
            this.add(anotherDayAvailability);
        } else {
            DayAvailability dayAvailability = (DayAvailability)this.dayAvailabilities.stream().filter((d) -> {
                return d.getDay() == anotherDayAvailability.getDay();
            }).findAny().get();
            if (numberOfDays == NumberOfDays.TWO) {
                dayAvailability.setTwo(anotherDayAvailability.getTwo());
            } else {
                dayAvailability.setFour(anotherDayAvailability.getFour());
            }
        }

        return this;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<DayAvailability> getDayAvailabilities() {
        return this.dayAvailabilities;
    }

    public void setDayAvailabilities(List<DayAvailability> dayAvailabilities) {
        this.dayAvailabilities = dayAvailabilities;
    }
}
