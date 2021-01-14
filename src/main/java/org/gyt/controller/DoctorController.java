/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.controller;

import org.gyt.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpServletRequest request) {
        if (doctorService.checkLogin(username, password)) {
            model.addAttribute("msg", "登陆成功");
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("currentUserName", username);
            return "main";
        } else {
            model.addAttribute("msg", "登陆失败，请检查用户名密码");
            return "login";
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> getAllDoctors(@RequestBody Map<String, String> param) {
        List<Map> ret = doctorService.findDoctors(param);
        return ret;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map addDoctor(@RequestBody Map<String, String> param) {
        return doctorService.addDoctor(param);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map editDoctor(@RequestBody Map<String, String> param) {
        return doctorService.editDoctor(param);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map deleteDoctor(@RequestBody Map<String, String> param) {
        return doctorService.deleteDoctor(param);
    }
}
