package com.github.adam6806.catamaranindex.boat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ScheduledTasks {

    private static final Logger log = Logger.getLogger(ScheduledTasks.class.getName());

    @Scheduled(fixedRate = 5000)
    public void report() {
        log.warning("hey it's me");

    }
}
