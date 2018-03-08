//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities;

public enum NumberOfDays {
    TWO(1),
    FOUR(2);

    private int value;

    private NumberOfDays(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
