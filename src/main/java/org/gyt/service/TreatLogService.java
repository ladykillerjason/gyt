/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.service;

import org.apache.commons.lang.StringUtils;
import org.gyt.dao.PatientQueueDao;
import org.gyt.dao.TreatBillDao;
import org.gyt.dao.TreatLogDao;
import org.gyt.util.TimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Service
public class TreatLogService {

    @Autowired
    TreatLogDao treatLogDao;

    @Autowired
    TreatBillDao treatBillDao;

    @Autowired
    PatientQueueDao patientQueueDao;

    public List<Map> findTreatLogs(Map<String, String> param) {
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
        map.put("start", start);
        map.put("size", limit);
        return treatLogDao.findTreatLogs(map);
    }

    private int getTreatTotalCount(Map map) {
        List<Map> list = treatLogDao.findTreatLogs(map);
        return (int) list.get(0).get("treatTotalCount");
    }

    private int updateTreatBillOver(Map map) {
        Map<String, Object> tMap = new HashMap<>();
        tMap.put("id", map.get("tb_id"));
        tMap.put("is_over", "是");
        tMap.put("over_time", TimeFormat.timeFormat(new Date()));
        return treatBillDao.updateTreatBill(tMap);
    }

    private int updateTreatBillUseCount(Map map) {
        Map<String, Object> tMap = new HashMap<>();
        tMap.put("id", map.get("tb_id"));
        tMap.put("treat_use_count", map.get("treat_count"));
        return treatBillDao.updateTreatBill(tMap);
    }

    public int deletePatientQueue(String patientNo, String treatBillNo, String projectNo) {
        Map<String, String> map = new HashMap<>();
        map.put("patient_no", patientNo);
        map.put("tb_no", treatBillNo);
        map.put("project_no", projectNo);
        int retV = patientQueueDao.deletePatientQueue(map);
        return retV;
    }

    public Map<String, String> addTreatLog(Map<String, Object> param) {
        Map<String, String> ret = new HashMap<>();
        ret.put("status", "fail");
        ret.put("msg", "新增失败");
        Integer treatBillId = (Integer) param.get("treatBillId");
        String treatBillNo = (String) param.get("treatBillNo");
        String patientNo = (String) param.get("patientNo");
        String projectNo = (String) param.get("projectNo");
        deletePatientQueue(patientNo, treatBillNo, projectNo);

        String zhiliaoNo = (String) param.get("zhiliaoNo");
        Integer treatCount = (Integer) param.get("treatCount");
        String treatTime = TimeFormat.timeFormat(new Date());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tb_id", treatBillId);
        map.put("zhiliao_no", zhiliaoNo);
        map.put("treat_count", treatCount);
        map.put("treat_time", treatTime);
        int retV = treatLogDao.insertTreatLog(map);
        if (retV > 0) {
            ret.put("status", "success");
            ret.put("msg", "插入成功");

            updateTreatBillUseCount(map);

            int treatTotalCount = getTreatTotalCount(map);
            if (treatTotalCount == treatCount) {
                updateTreatBillOver(map);
            }
            return ret;
        } else {
            return ret;
        }
    }

    public Integer getTreatLogId(String treatBillNo, String zhiliaoNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("tb_id", treatBillNo);
        map.put("zhiliao_no", zhiliaoNo);
        List<Map> ret = treatLogDao.findTreatLogsPure(map);
        return (Integer) ret.get(0).get("id");

    }

    public Map<String, String> uploadPic(HttpServletRequest request) {
        String treatBillNo = request.getParameter("treatBillNo");
        String zhiliaoNo = request.getParameter("zhiliaoNo");
        Map<String, String> ret = new HashMap<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
            request.getSession().getServletContext());
        String rootPath = "/gyt/uploadPic/";
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                String myFileName = file.getOriginalFilename();
                String fullFileName = rootPath + myFileName;
                File fullPathFile = new File(fullFileName);
                System.out.println(myFileName);
                try {
                    // 保存文件到本地
                    file.transferTo(fullPathFile);
                    // 保存文件路径到数据库
                    Integer treatLogId = getTreatLogId(treatBillNo, zhiliaoNo);
                    Map<String, Object> tm = new HashMap<>();
                    tm.put("tl_id", treatLogId);
                    tm.put("pic_path", fullFileName);
                    ret.put("msg", "上传成功");
                    treatLogDao.insertUploadPic(tm);
                } catch (IOException e) {
                    ret.put("msg", "上传失败");
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
}
