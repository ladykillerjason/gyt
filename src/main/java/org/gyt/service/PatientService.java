/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.service;

import org.apache.commons.lang.StringUtils;
import org.gyt.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatientService {

    @Autowired
    PatientDao dao;

    public List<Map> findPatients(Map<String, String> param) {
        String patientNo = param.get("patientNo");
        String patientName = param.get("patientName");
        String patientPhone = param.get("patientPhone");
        String patientSex = param.get("patientSex");
        String sPage = (String) param.get("page");
        String sLimit = (String) param.get("limit");
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
        if (!StringUtils.isEmpty(patientNo)) {
            map.put("patient_no", patientNo);
        }
        if (!StringUtils.isEmpty(patientName)) {
            map.put("patient_name", patientName);
        }
        if (!StringUtils.isEmpty(patientPhone)) {
            map.put("patient_phone", patientPhone);
        }
        if (!StringUtils.isEmpty(patientSex)) {
            map.put("patient_sex", patientSex);
        }
        map.put("start", start);
        map.put("size", limit);
        return dao.findPatients(map);
    }

    public boolean checkHasPatientNo(String patientNo) {
        Map<String, String> tm = new HashMap<>();
        tm.put("patientNo", patientNo);
        List<Map> ret = findPatients(tm);
        if (ret.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, String> addPatient(Map<String, String> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "新增失败");
        String patientNo = param.get("patientNo");
        String patientName = param.get("patientName");
        String patientPhone = param.get("patientPhone");
        String patientSex = param.get("patientSex");
        String patientAge = param.get("patientAge");
        String patientMemo = param.get("patientMemo");

        if (checkHasPatientNo(patientNo)) {
            ret.put("msg", "新增失败，已有改病人编号");
            return ret;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("patient_no", "");
        map.put("patient_name", "");
        map.put("patient_phone", "");
        map.put("patient_sex", "");
        map.put("patient_age", 0);
        map.put("patient_memo", "");
        if (!StringUtils.isEmpty(patientNo)) {
            map.put("patient_no", patientNo);
        }
        if (!StringUtils.isEmpty(patientName)) {
            map.put("patient_name", patientName);
        }
        if (!StringUtils.isEmpty(patientPhone)) {
            map.put("patient_phone", patientPhone);
        }
        if (!StringUtils.isEmpty(patientSex)) {
            map.put("patient_sex", patientSex);
        }
        if (!StringUtils.isEmpty(patientAge)) {
            map.put("patient_age", Integer.valueOf(patientAge));
        }
        if (!StringUtils.isEmpty(patientMemo)) {
            map.put("patient_memo", patientMemo);
        }
        int retV = dao.insertPatient(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "插入成功");
            return ret;
        } else {
            return ret;
        }
    }

    public Map<String, String> editPatient(Map<String, String> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "修改失败");
        String patientNo = param.get("patientNo");
        String patientName = param.get("patientName");
        String patientPhone = param.get("patientPhone");
        String patientSex = param.get("patientSex");
        String patientAge = param.get("patientAge");
        String patientMemo = param.get("patientMemo");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("patient_no", patientNo);
        map.put("patient_name", patientName);
        map.put("patient_phone", Integer.valueOf(patientPhone));
        map.put("patient_sex", patientSex);
        map.put("patient_age", patientAge);
        map.put("patient_memo", patientMemo);
        int retV = dao.updatePatient(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "修改成功");
            return ret;
        } else {
            return ret;
        }
    }

    public Map<String, String> deletePatient(Map<String, String> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "删除失败");
        String patientNo = param.get("patientNo");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("patient_no", patientNo);
        int retV = dao.deletePatient(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "删除成功");
            return ret;
        } else {
            return ret;
        }
    }

}
