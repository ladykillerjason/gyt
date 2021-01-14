/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.service;

import org.apache.commons.lang.StringUtils;
import org.gyt.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    ProjectDao dao;

    public List<Map> findProjects(Map<String, String> param) {
        String projectNo = param.get("projectNo");
        String projectName = param.get("projectName");
        String sPage = param.get("page");
        String sLimit = param.get("limit");
        Integer page = 0;
        Integer limit = 15;
        if (!StringUtils.isEmpty(sPage)) {
            page = Integer.valueOf(sPage) - 1;
            if (page < 0) {
                page = 0;
            }
        }
        if (!StringUtils.isEmpty(sLimit)) {
            limit = Integer.valueOf(sLimit);
        }
        Integer start = page * limit;
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(projectNo)) {
            map.put("project_no", projectNo);
        }
        if (!StringUtils.isEmpty(projectName)) {
            map.put("project_name", projectName);
        }
        map.put("start", start);
        map.put("size", limit);
        return dao.findProjects(map);
    }

    public boolean checkHasPatientNo(String patientNo) {
        Map<String, String> tm = new HashMap<>();
        tm.put("projectNo", patientNo);
        List<Map> ret = findProjects(tm);
        if (ret.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, String> addProject(Map<String, String> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "新增失败");
        String projectNo = param.get("projectNo");
        String projectName = param.get("projectName");
        String projectPrice = param.get("projectPrice");

        if (checkHasPatientNo(projectNo)) {
            ret.put("msg", "新增失败，已有项目编号");
            return ret;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project_no", "");
        map.put("project_name", "");
        map.put("project_price", "");
        if (!StringUtils.isEmpty(projectNo)) {
            map.put("project_no", projectNo);
        }
        if (!StringUtils.isEmpty(projectName)) {
            map.put("project_name", projectName);
        }
        if (!StringUtils.isEmpty(projectPrice)) {
            map.put("project_price", projectPrice);
        }
        int retV = dao.insertProject(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "插入成功");
            return ret;
        } else {
            return ret;
        }
    }

    public Map<String, String> editProject(Map<String, String> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "修改失败");
        String projectNo = param.get("projectNo");
        String projectName = param.get("projectName");
        String projectPrice = param.get("projectPrice");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project_no", projectNo);
        map.put("project_name", projectName);
        map.put("project_price", projectPrice);
        int retV = dao.updateProject(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "修改成功");
            return ret;
        } else {
            return ret;
        }
    }

    public Map<String, String> deleteProject(Map<String, String> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "删除失败");
        String projectNo = param.get("projectNo");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project_no", projectNo);
        int retV = dao.deleteProject(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "删除成功");
            return ret;
        } else {
            return ret;
        }
    }


}
