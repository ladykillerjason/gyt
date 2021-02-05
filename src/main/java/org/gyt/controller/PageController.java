/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {


    @RequestMapping("/doctor/add-doctor")
    public String addDoctor() {
        return "/doctor/add-doctor";
    }

    @RequestMapping("/doctor/edit-doctor")
    public String editDoctor() {
        return "/doctor/edit-doctor";
    }

    @RequestMapping("/doctor/table-doctor")
    public String tableDoctor() {
        return "/doctor/table-doctor";
    }

    @RequestMapping("/login/login")
    public String login() {
        return "/login/login";
    }

    @RequestMapping("/patient/add-patient")
    public String addPatient() {
        return "/patient/add-patient";
    }
    @RequestMapping("/patient/edit-patient")
    public String editPatient() {
        return "/patient/edit-patient";
    }
    @RequestMapping("/patient/table-patient")
    public String tablePatient() {
        return "/patient/table-patient";
    }

    @RequestMapping("/patientQueue/add-patientQueue")
    public String addPatientQueue() {
        return "/patientQueue/add-patientQueue";
    }
    @RequestMapping("/patientQueue/finishTreat")
    public String finishTreat() {
        return "/patientQueue/finishTreat";
    }
    @RequestMapping("/patientQueue/query-treatBill")
    public String queryTreatBill() {
        return "/patientQueue/query-treatBill";
    }
    @RequestMapping("/patientQueue/show-queue")
    public String showQueue() {
        return "/patientQueue/show-queue";
    }

    @RequestMapping("/project/add-project")
    public String addProject() {
        return "/project/add-project";
    }
    @RequestMapping("/project/edit-project")
    public String editProject() {
        return "/project/edit-project";
    }
    @RequestMapping("/project/table-project")
    public String tableProject() {
        return "/project/table-project";
    }

    @RequestMapping("/stat/statKaiDanRen")
    public String statKaiDanRen() {
        return "/stat/statKaiDanRen";
    }
    @RequestMapping("/stat/statKaiDanRen-money")
    public String statKaiDanRenMoney() {
        return "/stat/statKaiDanRen-money";
    }
    @RequestMapping("/stat/statPatient")
    public String statPatient() {
        return "/stat/statPatient";
    }
    @RequestMapping("/stat/statProject")
    public String statProject() {
        return "/stat/statProject";
    }
    @RequestMapping("/stat/statZhiLiaoShi")
    public String statZhiLiaoShi() {
        return "/stat/statZhiLiaoShi";
    }
    @RequestMapping("/stat/statZhiLiaoShi-money")
    public String statZhiLiaoShiMoney() {
        return "/stat/statZhiLiaoShi-money";
    }

    @RequestMapping("/treatBill/add-treatBill")
    public String addTreatBill() {
        return "/treatBill/add-treatBill";
    }
    @RequestMapping("/treatBill/success")
    public String success() {
        return "/treatBill/success";
    }

    @RequestMapping("/404")
    public String Error404() {
        return "/404";
    }
    @RequestMapping("/error")
    public String errorPage() {
        return "/error";
    }
    @RequestMapping("/welcome")
    public String welcome() {
        return "/welcome";
    }
    @RequestMapping("/main")
    public String mainPage() {
        return "/main";
    }


















}
