/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.controller;

import org.gyt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> getAllProjects(@RequestBody Map<String, String> param) {
        List<Map> ret = projectService.findProjects(param);
        return ret;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map addProject(@RequestBody Map<String, String> param) {
        return projectService.addProject(param);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map editProject(@RequestBody Map<String, String> param) {
        return projectService.editProject(param);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map deleteProject(@RequestBody Map<String, String> param) {
        return projectService.deleteProject(param);
    }

}
