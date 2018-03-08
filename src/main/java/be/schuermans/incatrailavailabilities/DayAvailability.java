//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities;

public class DayAvailability {
    private int day;
    private int four;
    private int two;

    public DayAvailability() {
    }

    public DayAvailability(int day, int two, int four) {
        this.day = day;
        this.four = four;
        this.two = two;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getFour() {
        return this.four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getTwo() {
        return this.two;
    }

    public void setTwo(int two) {
        this.two = two;
    }
}
