/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.controller;

import org.gyt.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> getAllPatients(@RequestBody Map<String, String> param) {
        List<Map> ret = patientService.findPatients(param);
        return ret;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map addPatient(@RequestBody Map<String, String> param) {
        return patientService.addPatient(param);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map editPatient(@RequestBody Map<String, String> param) {
        return patientService.editPatient(param);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map deletePatient(@RequestBody Map<String, String> param) {
        return patientService.deletePatient(param);
    }
}
