//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities;

import com.google.api.client.util.Throwables;
import com.google.appengine.api.urlfetch.FetchOptions.Builder;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.URLFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class MonthAvailabilityGateway {
    public static String URL = "http://www.tourinperu.com/wp-content/plugins/wp-inca-trail/it-form.php";
    public static String LANG = "en-US";
    @Autowired
    private URLFetchService urlFetchService;

    public MonthAvailabilityGateway() {
    }

    public String post(int year, int month, int numberOfDays) {
        try {
            HTTPRequest httpRequest = new HTTPRequest(new URL(URL), HTTPMethod.POST, Builder.withDeadline(20.0D));
            httpRequest.addHeader(new HTTPHeader("Cache-Control", "no-cache"));
            httpRequest.addHeader(new HTTPHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"));
            httpRequest.setPayload(("incatrail_lang=" + LANG + "&incatrail_place=" + numberOfDays + "&incatrail_month=" + month + "&incatrail_year=" + year).getBytes());
            return new String(this.urlFetchService.fetch(httpRequest).getContent());
        } catch (Exception var5) {
            throw Throwables.propagate(var5);
        }
    }
}
