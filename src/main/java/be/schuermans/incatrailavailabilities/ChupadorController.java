package be.schuermans.incatrailavailabilities;

import be.schuermans.incatrailavailabilities.appengine.Datastore;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.TaskOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.IntStream;

@RestController
@CrossOrigin
public class ChupadorController {

    @Autowired
    private Datastore datastore;
    @Autowired
    private CurrentDate currentDate;
    @Autowired
    private MonthAvailabilityParser monthAvailabilityParser;
    @Autowired
    private Queue queue;

    @GetMapping({"/"})
    @Cacheable({"availabilities"})
    public Availabilities get() {
        return this.datastore.get();
    }

    @DeleteMapping({"/"})
    public void delete() {
        this.datastore.delete();
    }

    @GetMapping({"/refresh"})
    public void refresh() {
        this.queue.add(TaskOptions.Builder.withUrl("/tasks/refresh").method(TaskOptions.Method.POST));
    }

    @PostMapping({"/tasks/refresh"})
    public void taskRefresh() {
        Arrays.stream(NumberOfDays.values()).forEach((numberOfDays) -> {
            IntStream.range(this.currentDate.year(), this.currentDate.year() + 3).forEach((year) -> {
                IntStream.range(1, 13).forEach((month) -> {
                    this.monthAvailabilityParser.parse(year, month, numberOfDays);
                });
            });
        });
        this.queue.add(TaskOptions.Builder.withUrl("/tasks/evictCache").method(TaskOptions.Method.POST));
    }

    @PostMapping({"/tasks/evictCache"})
    @CacheEvict({"availabilities"})
    public void evictCache() {
    }

}
