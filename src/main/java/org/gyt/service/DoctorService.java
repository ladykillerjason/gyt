/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.service;

import org.apache.commons.lang.StringUtils;
import org.gyt.dao.DoctorDao;
import org.gyt.util.ShaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService {

    @Autowired
    DoctorDao dao;

    public boolean checkLogin(String name, String password) {
        Map<String, Object> map = new HashMap();
        map.put("name", name);
        map.put("password", ShaUtil.shaEncode(password));
        List<Map> retList = dao.findDoctors(map);
        if (retList.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public List<Map> findDoctors(Map<String, String> param) {
        String docNo = param.get("docNo");
        String docName = param.get("docName");
        String docPhone = param.get("docPhone");
        String docTitle = param.get("docTitle");
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
        map.put("doc_no", docNo);
        map.put("doc_name", docName);
        map.put("doc_phone", docPhone);
        map.put("doc_title", docTitle);
        map.put("start", start);
        map.put("size", limit);
        return dao.findDoctors(map);
    }

    public boolean checkHasDocNo(String docNo) {
        Map<String, String> tm = new HashMap<>();
        tm.put("docNo", docNo);
        List<Map> ret = findDoctors(tm);
        if (ret.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, String> addDoctor(Map<String, String> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "新增失败");
        String docNo = param.get("docNo");
        String docPass = param.get("docPass");
        String docName = param.get("docName");
        String docPhone = param.get("docPhone");
        String docTitle = param.get("docTitle");

        if (checkHasDocNo(docNo)) {
            ret.put("msg", "新增失败，已有医生编号");
            return ret;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("doc_no", "");
        map.put("doc_pass", "");
        map.put("doc_name", "");
        map.put("doc_phone", "");
        map.put("doc_title", "");
        if (!StringUtils.isEmpty(docNo)) {
            map.put("doc_no", docNo);
        }
        if (!StringUtils.isEmpty(docPass)) {
            map.put("doc_pass", docPass);
        }
        if (!StringUtils.isEmpty(docName)) {
            map.put("doc_name", docName);
        }
        if (!StringUtils.isEmpty(docPhone)) {
            map.put("doc_phone", docPhone);
        }
        if (!StringUtils.isEmpty(docTitle)) {
            map.put("doc_title", docTitle);
        }
        map.put("doc_role", "user");
        int retV = dao.insertDoctor(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "插入成功");
            return ret;
        } else {
            return ret;
        }
    }

    public Map<String, String> editDoctor(Map<String, String> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "修改失败");
        String docNo = param.get("docNo");
        String docPass = param.get("docPass");
        String docName = param.get("docName");
        String docPhone = param.get("docPhone");
        String docTitle = param.get("docTitle");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("doc_no", docNo);
        map.put("doc_pass", docPass);
        map.put("doc_name", docName);
        map.put("doc_phone", docPhone);
        map.put("doc_title", docTitle);
        int retV = dao.updateDoctor(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "修改成功");
            return ret;
        } else {
            return ret;
        }
    }
}
