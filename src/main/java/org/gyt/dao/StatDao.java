/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface StatDao {

    @Select("<script>"
        + "select d.doc_no docNo,d.doc_name docName,p1.patient_no patientNo,p1.patient_name patientName,p2.project_no projectNo,p2.project_name projectName,bill.treat_total_count treatTotalCount,log.treat_count treatCount,log.treat_time treatTime "
        + "from t_treat_log log left join t_doctor d on d.doc_no = log.zhiliao_no "
        + "left join t_treat_bill bill on log.tb_id = bill.id "
        + "left join t_patient p1 on bill.patient_no = p1.patient_no "
        + "left join t_project p2 on bill.project_no = p2.project_no "
        + "where 1=1 "

        + "<if test=\"doc_no !=null and doc_no!='' \">"
        + "and d.doc_no = #{doc_no} "
        + "</if>"
        + "<if test=\"doc_name !=null and doc_name!='' \">"
        + "and d.doc_name = #{doc_name} "
        + "</if>"
        + "<if test=\"start_time !=null and start_time!='' \">"
        + "and log.treat_time &gt;= #{start_time} "
        + "</if>"
        + "<if test=\"end_time !=null and end_time!='' \">"
        + "and log.treat_time &lt;= #{end_time}"
        + "</if>"

        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> statByZhiLiaoshi(Map<String, Object> map);


    @Select("<script>"
        + "select d.doc_no docNo,d.doc_name docName,bill.tb_no treatBillNo,p1.patient_name patientName, "
        + "p2.project_name projectName,bill.treat_total_count treatTotalCount,bill.create_time createTime,bill.is_over isOver,bill.over_time overTime "
        + "from t_treat_bill bill left join t_doctor d on d.doc_no = bill.kaidan_no "
        + "left join t_patient p1 on bill.patient_no = p1.patient_no "
        + "left join t_project p2 on bill.project_no = p2.project_no "
        + "where 1=1 "

        + "<if test=\"doc_no !=null and doc_no!='' \">"
        + "and d.doc_no = #{doc_no} "
        + "</if>"
        + "<if test=\"doc_name !=null and doc_name!='' \">"
        + "and d.doc_name = #{doc_name} "
        + "</if>"

        + "<if test=\"start_time !=null and start_time!='' \">"
        + "and bill.create_time &gt;= #{start_time} "
        + "</if>"
        + "<if test=\"end_time !=null and end_time!='' \">"
        + "and bill.create_time &lt;= #{end_time} "
        + "</if>"

        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> statByKaiDanRen(Map<String, Object> map);


    @Select("<script>"
        + "select t1.patientNo,t1.patientName,t1.projectName,t1.treatTotalCount,t1.treatCount,t1.treatTime,t1.zhiliaoshiName,t2.doc_name kaidanName "
        + "from "
        + "(select p1.patient_no patientNo,p1.patient_name patientName,p2.project_name projectName,bill.kaidan_no kaidanNo, "
        + "bill.treat_total_count treatTotalCount,log.treat_time treatTime,d.doc_name zhiliaoshiName,log.treat_count treatCount "
        + "from t_treat_log log left join t_treat_bill bill on log.tb_id = bill.id "
        + "left join t_doctor d on d.doc_no = log.zhiliao_no "
        + "left join t_patient p1 on bill.patient_no = p1.patient_no "
        + "left join t_project p2 on bill.project_no = p2.project_no "
        + "where p1.patient_no is not null "
        + "<if test=\"patient_no !=null and patient_no!='' \">"
        + "and p1.patient_no = #{patient_no} "
        + "</if>"
        + "<if test=\"patient_name !=null and patient_name!='' \">"
        + "and p1.patient_name = #{patient_name} "
        + "</if>"

        + "<if test=\"start_time !=null and start_time!='' \">"
        + "and log.treat_time &gt;= #{start_time} "
        + "</if>"
        + "<if test=\"end_time !=null and end_time!='' \">"
        + "and log.treat_time &lt;= #{end_time} "
        + "</if>"

        + ") as t1 "
        + "left join t_doctor t2 on t1.kaidanNo = t2.doc_no"

        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> statByPatient(Map<String, Object> map);


    @Select("<script>"
        + "select p2.project_name projectName,p1.patient_name patientName,log.treat_time treatTime,d.doc_name docName "
        + "from t_treat_log log left join t_treat_bill bill on log.tb_id = bill.id "
        + "left join t_doctor d on d.doc_no = log.zhiliao_no "
        + "left join t_patient p1 on bill.patient_no = p1.patient_no "
        + "left join t_project p2 on bill.project_no = p2.project_no "
        + "where 1=1 "

        + "<if test=\"project_no !=null and project_no!='' \">"
        + "and p2.project_no = #{project_no} "
        + "</if>"
        + "<if test=\"project_name !=null and project_name!='' \">"
        + "and p2.project_name = #{project_name} "
        + "</if>"

        + "<if test=\"start_time !=null and start_time!='' \">"
        + "and bill.create_time &gt;= #{start_time} "
        + "</if>"
        + "<if test=\"end_time !=null and end_time!='' \">"
        + "and bill.create_time &lt;= #{end_time} "
        + "</if>"

        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> statByProject(Map<String, Object> map);



    @Select("<script>"
        + "SELECT d.doc_name kaidanName,sum(p2.project_price) totalSum "
        + "from t_treat_log log "
        + "left join t_treat_bill bill on log.tb_id = bill.id "
        + "left join t_project p2 on bill.project_no = p2.project_no "
        + "left join t_doctor d on bill.kaidan_no = d.doc_no "
        + "where 1=1 "
        + "<if test=\"start_time !=null and start_time!='' \">"
        + "and log.treat_time &gt;= #{start_time} "
        + "</if>"
        + "<if test=\"end_time !=null and end_time!='' \">"
        + "and log.treat_time &lt;= #{end_time} "
        + "</if>"

        + " GROUP BY d.doc_name "

        + "</script>")
    public List<Map> statMoneyOfKaidanRen(Map<String, Object> map);


    @Select("<script>"
        + "SELECT d.doc_name zhiliaoshiName,sum(p2.project_price) totalSum "
        + "from t_treat_log log "
        + "left join t_treat_bill bill on log.tb_id = bill.id "
        + "left join t_project p2 on bill.project_no = p2.project_no "
        + "left join t_doctor d on log.zhiliao_no = d.doc_no "
        + "where 1=1 "
        + "<if test=\"start_time !=null and start_time!='' \">"
        + "and log.treat_time &gt;= #{start_time} "
        + "</if>"
        + "<if test=\"end_time !=null and end_time!='' \">"
        + "and log.treat_time &lt;= #{end_time} "
        + "</if>"

        + " GROUP BY d.doc_name "

        + "</script>")
    public List<Map> statMoneyOfZhiliaoshi(Map<String, Object> map);
}
