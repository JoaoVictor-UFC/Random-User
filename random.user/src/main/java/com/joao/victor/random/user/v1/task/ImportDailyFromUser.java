package com.joao.victor.random.user.v1.task;

import com.joao.victor.random.user.v1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ImportDailyFromUser {

    @Autowired
    private UserService userService;

    // TODO: Every day, 1 in the morning
    @Scheduled(cron = "0 0 1 * * *")
    private void checkValidateOfCard() throws Exception {
        log.info("Importing new users...");
        userService.importForApiRandomUser(Long.parseLong("100"));
        log.info("Import completed successfully!");
    }
}
