package org.gyt.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PatientQueueDao {

    @Select("<script>"
        + "select p1.patient_no patientNo,p1.patient_name patientName, p1.patient_phone patientPhone,p2.project_name projectName,p2.project_no projectNo,q.tb_no treatBillNo "
        + "from t_queue q "
        + "left join t_patient p1 on q.patient_no = p1.patient_no "
        + "left join t_project p2 on q.project_no = p2.project_no "
        + "where 1=1 "
        + "<if test=\"patient_no !=null and patient_no!='' \">"
        + "and p1.patient_no = #{patient_no} "
        + "</if>"
        + "<if test=\"patient_name !=null and patient_name!='' \">"
        + "and p1.patient_name = #{patient_name} "
        + "</if>"
        + "<if test=\"tb_no !=null and tb_no!='' \">"
        + "and q.tb_no = #{tb_no} "
        + "</if>"
        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> findPatientQueue(Map<String, Object> map);

    @Insert("<script>"
        + "insert into "
        + "t_queue(patient_no,tb_no,project_no) "
        + "values(#{patient_no},#{tb_no},#{project_no})"
        + "</script>")
    public int insertPatientQueue(Map map);

    @Delete("<script>"
        + "delete from t_queue "
        + "where  "
        + "patient_no = #{patient_no} and tb_no=#{tb_no} and project_no=#{project_no}"
        + "</script>")
    public int deletePatientQueue(Map map);


    @Delete("<script>"
        + "delete from t_queue"
        + "</script>")
    public int deleteAllPatientQueue();

}

