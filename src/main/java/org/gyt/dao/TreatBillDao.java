/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface TreatBillDao {

    @Select("<script>"
        + "select b.id treatBillId,b.tb_no treatBillNo,d.doc_no docNo,d.doc_name docName,p1.patient_no patientNo,p1.patient_name patientName,p2.project_no projectNo,p2.project_name projectName,"
        + "b.treat_total_count treatTotalCount,b.treat_use_count treatUseCount,"
        + "b.create_time createTime,b.is_over isOver,b.over_time overTime "
        + "from t_treat_bill b left join t_doctor d on b.kaidan_no = d.doc_no "
        + "left join t_patient p1 on b.patient_no = p1.patient_no "
        + "left join t_project p2 on b.project_no = p2.project_no "
        + "where 1=1 "
        + "<if test=\"tb_no !=null and tb_no!='' \">"
        + "and b.tb_no = #{tb_no} "
        + "</if>"
        + "<if test=\"kaidan_no !=null and kaidan_no!='' \">"
        + "and b.kaidan_no = #{kaidan_no} "
        + "</if>"
        + "<if test=\"doc_name !=null and doc_name!='' \">"
        + "and d.doc_name = #{doc_name} "
        + "</if>"

        + "<if test=\"patient_no !=null and patient_no!='' \">"
        + "and p1.patient_no = #{patient_no} "
        + "</if>"
        + "<if test=\"patient_name !=null and patient_name!='' \">"
        + "and p1.patient_name = #{patient_name} "
        + "</if>"

        + "<if test=\"project_no !=null and project_no!='' \">"
        + "and p2.project_no = #{project_no} "
        + "</if>"
        + "<if test=\"project_name !=null and project_name!='' \">"
        + "and p2.project_name = #{project_name} "
        + "</if>"
        + "<if test=\"is_over !=null and is_over!='' \">"
        + "and b.is_over = #{is_over} "
        + "</if>"

        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> findTreatBills(Map<String, Object> map);


    @Insert("<script>"
        + "insert into "
        + "t_treat_bill(tb_no,kaidan_no,patient_no,project_no,treat_total_count,treat_use_count,create_time,is_over,over_time) values "
        + "<foreach collection='list' item='item1' index='index'  separator=','> "
        + "(#{item1.tb_no},#{item1.kaidan_no},#{item1.patient_no},#{item1.project_no},#{item1.treat_total_count},"
        + "#{item1.treat_use_count},#{item1.create_time},#{item1.is_over},#{item1.over_time}) "
        + "</foreach>"
        + "</script>")
    public int insertTreatBill(List<Map> list);


    @Update("<script>"
        + "update t_treat_bill "
        + "set id=#{id}"
        + "<if test=\"is_over !=null and is_over!='' \">"
        + ",is_over = #{is_over} "
        + "</if>"
        + "<if test=\"over_time !=null and over_time!='' \">"
        + ", over_time = #{over_time} "
        + "</if>"
        + "<if test=\"treat_use_count !=null and treat_use_count!='' \">"
        + ", treat_use_count = #{treat_use_count} "
        + "</if>"
        + "where id = #{id}"
        + "</script>")
    public int updateTreatBill(Map map);
}
