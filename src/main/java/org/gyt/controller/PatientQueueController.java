/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.controller;

import org.gyt.service.PatientQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/patientQueue")
public class PatientQueueController {

    @Autowired
    PatientQueueService patientQueueService;

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> getAllPatientQueues(@RequestBody Map<String, String> param) {
        List<Map> ret = patientQueueService.findPatientQueues(param);
        return ret;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map addPatientQueue(@RequestBody Map<String, Object> param) {
        return patientQueueService.addPatientQueue(param);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map deletePatientQueue(@RequestBody Map<String, Object> param) {
        return patientQueueService.deletePatientQueue(param);
    }
}
