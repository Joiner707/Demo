package com.nit.task.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobDemo {

    @Scheduled(cron = "0/5 * * * * ?")
    public void printData(){
        log.info("now is "+ System.currentTimeMillis());
    }
}
