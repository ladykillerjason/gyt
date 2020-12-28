/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.service;

import org.apache.commons.lang.StringUtils;
import org.gyt.dao.PatientQueueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatientQueueService {

    @Autowired
    PatientQueueDao patientQueueDao;


    public List<Map> findPatientQueues(Map<String, String> param) {
        String sPage = param.get("page");
        String sLimit = param.get("limit");
        String patientNo = (String) param.get("patientNo");
        String patientName = (String) param.get("patientName");
        String treatBillNo = (String) param.get("treatBillNo");
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
        map.put("tb_no", treatBillNo);
        map.put("start", start);
        map.put("size", limit);
        return patientQueueDao.findPatientQueue(map);
    }

    public boolean checkInQueue(String patientNo,String treatBillNo,String projectNo){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("patient_no", patientNo);
        map.put("tb_no", treatBillNo);
        map.put("project_no", projectNo);
        List ret= patientQueueDao.findPatientQueue(map);
        if(ret.size() > 0){
            return true;
        }else{
            return false;
        }

    }

    public Map<String, String> addPatientQueue(Map<String, Object> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "新增失败");
        String patientNo = (String) param.get("patientNo");
        String treatBillNo = (String) param.get("treatBillNo");
        String projectNo = (String) param.get("projectNo");

        if(checkInQueue(patientNo,treatBillNo,projectNo)){
            return ret;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("patient_no", patientNo);
        map.put("tb_no", treatBillNo);
        map.put("project_no", projectNo);
        int retV = patientQueueDao.insertPatientQueue(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "插入成功");
            return ret;
        } else {
            return ret;
        }
    }

    public Map<String, String> deletePatientQueue(Map<String, Object> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "删除失败");
        String patientNo = (String) param.get("patientNo");
        String treatBillNo = (String) param.get("treatBillNo");
        String projectNo = (String) param.get("projectNo");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("patient_no", patientNo);
        map.put("tb_no", treatBillNo);
        map.put("project_no", projectNo);
        int retV = patientQueueDao.deletePatientQueue(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "修改成功");
            return ret;
        } else {
            return ret;
        }
    }

    public Map<String, String> deleteAllPatientQueue(){
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "删除所有排队信息失败");
        int retV = patientQueueDao.deleteAllPatientQueue();
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "修改成功");
            return ret;
        } else {
            return ret;
        }
    }

}
