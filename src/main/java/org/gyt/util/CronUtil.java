/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.util;

import org.gyt.service.PatientQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CronUtil {

    @Autowired
    PatientQueueService patientQueueService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void deleteAllPatientQueueJob(){
        System.out.println("********deleteAllPatientQueueJob is running******");
        patientQueueService.deleteAllPatientQueue();
    }
}
