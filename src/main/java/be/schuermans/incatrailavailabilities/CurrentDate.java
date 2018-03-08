//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities;

import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

@Component
public class CurrentDate {
    public CurrentDate() {
    }

    public int year() {
        return Integer.parseInt(DateFormatUtils.format(new Date(), "yyyy"));
    }

    public int month() {
        return Integer.parseInt(DateFormatUtils.format(new Date(), "MM"));
    }
}
