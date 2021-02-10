/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TreatLogDao {

    @Select("<script>"
        + "select p.patient_no patientNo,p.patient_name patientName,p.patient_phone patientPhone,log.treat_count treatCount, log.treat_time treatTime,bill.treat_total_count treatTotalCount  "
        + "from t_treat_bill bill left join t_patient p on bill.patient_no = p.patient_no "
        + "left join t_treat_log log on bill.id  = log.tb_id "
        + "where 1=1 "

        + "<if test=\"patient_no !=null and patient_no!='' \">"
        + "and p.patient_no = #{patient_no} "
        + "</if>"
        + "<if test=\"patient_name !=null and patient_name!='' \">"
        + "and p.patient_name = #{patient_name} "
        + "</if>"
        + "<if test=\"patient_phone !=null and patient_phone!='' \">"
        + "and p.patient_phone = #{patient_phone} "
        + "</if>"

        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> findTreatLogs(Map<String, Object> map);


    @Select("<script>"
        + "select * from t_treat_log "
        + "where 1=1 "

        + "<if test=\"tb_id !=null and tb_id!='' \">"
        + "and tb_id = #{tb_id} "
        + "</if>"
        + "<if test=\"zhiliao_no !=null and zhiliao_no!='' \">"
        + "and zhiliao_no = #{zhiliao_no} "
        + "</if>"
        + "<if test=\"treat_count !=null and treat_count!='' \">"
        + "and treat_count = #{treat_count} "
        + "</if>"
        + "</script>")
    public List<Map> findTreatLogsPure(Map<String, Object> map);

    @Insert("<script>"
        + "insert into "
        + "t_treat_log(tb_id,zhiliao_no,treat_count,treat_time) values "
        + "(#{tb_id},#{zhiliao_no},#{treat_count},#{treat_time})"

        + "</script>")
    public int insertTreatLog(Map<String, Object> map);


    @Insert("<script>"
        + "insert into "
        + "t_upload_pic(tl_id,pic_path) "
        + "values (#{tl_id},#{pic_path})"
        + "</script>")
    public int insertUploadPic(Map<String, Object> map);

    @Select("<script>"
        + "select tl_id treatBillId, pic_path picPath"
        + "from t_upload_pic "
        + "where 1=1 "
        + "<if test=\"id !=null and id!='' \">"
        + "and id = #{id} "
        + "</if>"
        + "</script>")
    public List<Map> findUploadPics(Map<String, Object> map);
}
