//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities.appengine;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppEngineConfiguration {
    public AppEngineConfiguration() {
    }

    @Bean
    public DatastoreService datastoreService() {
        return DatastoreServiceFactory.getDatastoreService();
    }

    @Bean
    public Queue queue() {
        return QueueFactory.getDefaultQueue();
    }

    @Bean
    public URLFetchService urlFetchService() {
        return URLFetchServiceFactory.getURLFetchService();
    }

    @Bean
    public MailService mailService() {
        return MailServiceFactory.getMailService();
    }
}
