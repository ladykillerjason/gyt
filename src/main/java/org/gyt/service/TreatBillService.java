/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.service;

import org.apache.commons.lang.StringUtils;
import org.gyt.dao.PatientDao;
import org.gyt.dao.PatientQueueDao;
import org.gyt.dao.TreatBillDao;
import org.gyt.util.TimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class TreatBillService {

    @Autowired
    TreatBillDao treatBillDao;

    @Autowired
    PatientDao patientDao;

    @Autowired
    PatientQueueDao patientQueueDao;

    public List<Map> findTreatBills(Map<String, String> param) {
        String treatBillNo = param.get("treatBillNo");
        String kaidanNo = param.get("kaidanNo");
        String docName = param.get("docName");
        String patientNo = param.get("patientNo");
        String patientName = param.get("patientName");
        String projectNo = param.get("projectNo");
        String projectName = param.get("projectName");
        String isOver = param.get("isOver");
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
        if (!StringUtils.isEmpty(treatBillNo)) {
            map.put("tb_no", treatBillNo);
        }
        if (!StringUtils.isEmpty(kaidanNo)) {
            map.put("kaidan_no", kaidanNo);
        }
        if (!StringUtils.isEmpty(docName)) {
            map.put("doc_name", docName);
        }
        if (!StringUtils.isEmpty(patientNo)) {
            map.put("patient_no", patientNo);
        }
        if (!StringUtils.isEmpty(patientName)) {
            map.put("patient_name", patientName);
        }
        if (!StringUtils.isEmpty(projectNo)) {
            map.put("project_no", projectNo);
        }
        if (!StringUtils.isEmpty(projectName)) {
            map.put("project_name", projectName);
        }
        if (!StringUtils.isEmpty(isOver)) {
            map.put("is_over", isOver);
        }
        map.put("start", start);
        map.put("size", limit);
        return treatBillDao.findTreatBills(map);
    }

    public List<Map> findTreatBillsNotInQueue(Map<String, String> param) {
        List<Map> ret = new ArrayList<>();
        List<Map> allTreatBills = findTreatBills(param);
        List<Map> queue = patientQueueDao.findPatientQueue(new HashMap<>());
        if (queue.size() == 0) {
            return allTreatBills;
        }
        Set set = new HashSet<String>();
        for (Map<String, String> m : queue) {
            set.add(m.get("treatBillNo") + m.get("projectNo"));
        }
        for (Map<String, String> tbMap : allTreatBills) {
            String tmp = tbMap.get("treatBillNo") + tbMap.get("projectNo");
            if (!set.contains(tmp)) {
                ret.add(tbMap);
            }
        }
        return ret;
    }
    public List<Map> findTreatBillsInQueue(Map<String, String> param) {
        List<Map> ret = new ArrayList<>();
        List<Map> allTreatBills = findTreatBills(param);
        List<Map> queue = patientQueueDao.findPatientQueue(new HashMap<>());
        Set set = new HashSet<String>();
        for (Map<String, String> m : queue) {
            set.add(m.get("treatBillNo") + m.get("projectNo"));
        }
        for (Map<String, String> tbMap : allTreatBills) {
            String tmp = tbMap.get("treatBillNo") + tbMap.get("projectNo");
            if (set.contains(tmp)) {
                ret.add(tbMap);
            }
        }
        return ret;
    }


    public void checkHasPatient(String patientNo, String patientName) {
        Map<String, Object> map = new HashMap<>();
        map.put("patient_no", patientNo);
        List<Map> ret = patientDao.findPatients(map);
        if (ret.size() == 0) {
            map.put("patient_name", patientName);
            patientDao.insertPatient(map);
        }
    }

    public Map<String, String> addTreatBill(Map<String, Object> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "新增失败");
        String kaidanNo = (String) param.get("kaidanNo");
        String patientNo = (String) param.get("patientNo");
        String patientName = (String) param.get("patientName");
        checkHasPatient(patientNo, patientName);

        List<Map> projects = (List<Map>) param.get("projects");
        String createTime = TimeFormat.timeFormat(new Date());

        List<Map> intsertList = new ArrayList<>();
        String treatBillNo = TimeFormat.timeFormat2(new Date()) + kaidanNo;

        for (Map<String, Object> t_map : projects) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tb_no", treatBillNo);
            map.put("kaidan_no", kaidanNo);
            map.put("patient_no", patientNo);

            map.put("project_no", t_map.get("projectNo"));
            map.put("treat_total_count", t_map.get("treatTotalCount"));
            map.put("treat_use_count", 0);
            map.put("create_time", createTime);
            map.put("is_over", "否");
            intsertList.add(map);
        }

        int retV = treatBillDao.insertTreatBill(intsertList);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "插入成功");
            return ret;
        } else {
            return ret;
        }
    }

    public Map<String, String> updateTreatBill(Map<String, Object> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "修改失败");
        Integer id = (Integer) param.get("id");
        String isOver = (String) param.get("isOver");
        String overTime = (String) param.get("overTime");
        Integer treatUseCount = (Integer) param.get("treatUseCount");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("is_over", isOver);
        map.put("over_time", overTime);
        map.put("treat_use_count", treatUseCount);
        int retV = treatBillDao.updateTreatBill(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "修改成功");
            return ret;
        } else {
            return ret;
        }
    }

}
