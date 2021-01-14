/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.controller;

import org.gyt.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stat")
public class StatController {

    @Autowired
    StatService statService;

    @RequestMapping(value = "/statByZhiLiaoShi", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> statByZhiLiaoShi(@RequestBody Map<String, String> param) {
        List<Map> ret = statService.statByZhiLiaoShi(param);
        return ret;
    }

    @RequestMapping(value = "/statByKaiDanRen", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> statByKaiDanRen(@RequestBody Map<String, String> param) {
        List<Map> ret = statService.statByKaiDanRen(param);
        return ret;
    }

    @RequestMapping(value = "/statByPatient", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> statByPatient(@RequestBody Map<String, String> param) {
        List<Map> ret = statService.statByPatient(param);
        return ret;
    }

    @RequestMapping(value = "/statByProject", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> statByProject(@RequestBody Map<String, String> param) {
        List<Map> ret = statService.statByProject(param);
        return ret;
    }

    @RequestMapping(value = "/statMoneyOfKaidanRen", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> statMoneyOfKaidanRen(@RequestBody Map<String, String> param) {
        List<Map> ret = statService.statMoneyOfKaidanRen(param);
        return ret;
    }

    @RequestMapping(value = "/statMoneyOfZhiliaoshi", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> statMoneyOfZhiliaoshi(@RequestBody Map<String, String> param) {
        List<Map> ret = statService.statMoneyOfZhiliaoshi(param);
        return ret;
    }

}
