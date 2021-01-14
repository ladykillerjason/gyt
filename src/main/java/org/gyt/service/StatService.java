/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.service;

import org.apache.commons.lang.StringUtils;
import org.gyt.dao.StatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatService {

    @Autowired
    StatDao statDao;

    public List<Map> statByZhiLiaoShi(Map<String, String> param) {
        String sPage = param.get("page");
        String sLimit = param.get("limit");

        String docNo= param.get("docNo");
        String docName= param.get("docName");
        String startTime = param.get("startTime");
        String endTime = param.get("endTime");

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

        map.put("doc_no", docNo);
        map.put("doc_name", docName);
        map.put("start_time", startTime);
        map.put("end_time", endTime);


        map.put("start", start);
        map.put("size", limit);
        return statDao.statByZhiLiaoshi(map);
    }

    public List<Map> statByKaiDanRen(Map<String, String> param) {
        String sPage = param.get("page");
        String sLimit = param.get("limit");

        String docNo= param.get("docNo");
        String docName= param.get("docName");
        String startTime = param.get("startTime");
        String endTime = param.get("endTime");
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

        map.put("doc_no", docNo);
        map.put("doc_name", docName);
        map.put("start_time", startTime);
        map.put("end_time", endTime);

        map.put("start", start);
        map.put("size", limit);
        return statDao.statByKaiDanRen(map);
    }

    public List<Map> statByPatient(Map<String, String> param) {
        String sPage = param.get("page");
        String sLimit = param.get("limit");

        String patientNo = param.get("patientNo");
        String patientName = param.get("patientName");
        String startTime = param.get("startTime");
        String endTime = param.get("endTime");

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

        map.put("patient_no", patientNo);
        map.put("patient_name", patientName);
        map.put("start_time", startTime);
        map.put("end_time", endTime);

        map.put("start", start);
        map.put("size", limit);
        return statDao.statByPatient(map);
    }

    public List<Map> statByProject(Map<String, String> param) {
        String sPage = param.get("page");
        String sLimit = param.get("limit");

        String projectNo= param.get("projectNo");
        String projectName= param.get("projectName");
        String startTime = param.get("startTime");
        String endTime = param.get("endTime");

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

        map.put("project_no", projectNo);
        map.put("projectName", projectName);
        map.put("start_time", startTime);
        map.put("end_time", endTime);

        map.put("start", start);
        map.put("size", limit);
        return statDao.statByProject(map);
    }

    public List<Map> statMoneyOfKaidanRen(Map<String, String> param) {

        String startTime = param.get("startTime");
        String endTime = param.get("endTime");

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("start_time", startTime);
        map.put("end_time", endTime);

        return statDao.statMoneyOfKaidanRen(map);
    }

    public List<Map> statMoneyOfZhiliaoshi(Map<String, String> param) {

        String startTime = param.get("startTime");
        String endTime = param.get("endTime");

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("start_time", startTime);
        map.put("end_time", endTime);

        return statDao.statMoneyOfZhiliaoshi(map);
    }
}
