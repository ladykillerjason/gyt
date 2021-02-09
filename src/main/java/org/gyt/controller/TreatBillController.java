/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.controller;

import org.gyt.service.TreatBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/treatBill")
public class TreatBillController {

    @Autowired
    TreatBillService treatBillService;

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> getAllTreatBills(@RequestBody Map<String, String> param) {
        List<Map> ret = treatBillService.findTreatBills(param);
        return ret;
    }

    @RequestMapping(value = "/listNotInQueue", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> getAllTreatBillsNotInQueue(@RequestBody Map<String, String> param) {
        List<Map> ret = treatBillService.findTreatBillsNotInQueue(param);
        return ret;
    }
    @RequestMapping(value = "/listInQueue", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> getAllTreatBillsInQueue(@RequestBody Map<String, String> param) {
        List<Map> ret = treatBillService.findTreatBillsInQueue(param);
        return ret;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map addTreatBill(@RequestBody Map<String, Object> param) {
        return treatBillService.addTreatBill(param);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map editTreatBill(@RequestBody Map<String, Object> param) {
        return treatBillService.updateTreatBill(param);
    }
}
