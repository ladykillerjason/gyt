package org.gyt.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface PatientDao {

    @Select("<script>"
        + "select patient_no patientNo,patient_name patientName,patient_phone patientPhone,patient_sex patientSex,patient_age patientAge,patient_memo patientMemo  "
        + "from t_patient "
        + "where 1=1 "
        + "<if test=\"patient_no !=null and patient_no!='' \">"
        + "and patient_no = #{patient_no} "
        + "</if>"
        + "<if test=\"patient_name !=null and patient_name!='' \">"
        + "and patient_name = #{patient_name} "
        + "</if>"
        + "<if test=\"patient_phone !=null and patient_phone!='' \">"
        + "and patient_phone = #{patient_phone} "
        + "</if>"
        + "<if test=\"patient_sex !=null and patient_sex!='' \">"
        + "and patient_sex = #{patient_sex} "
        + "</if>"
        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> findPatients(Map<String, Object> map);



    @Insert("<script>"
        + "insert into "
        + "t_patient(patient_no,patient_name,patient_phone,patient_sex,patient_age,patient_memo) "
        + "values(#{patient_no},#{patient_name},#{patient_phone},#{patient_sex},#{patient_age},#{patient_memo})"
        + "</script>")
    public int insertPatient(Map map);

    @Update("<script>"
        + "update t_patient "
        + "set "
        + "patient_name=#{patient_name},patient_phone=#{patient_phone},"
        + "patient_sex=#{patient_sex},patient_age=#{patient_age},patient_memo=#{patient_memo} "
        + "where patient_no = #{patient_no}"
        + "</script>")
    public int updatePatient(Map map);

    @Delete("<script>"
        + "delete from t_patient "
        + "where 1=1 "
        + "and patient_no = #{patient_no}"
        + "</script>")
    public int deletePatient(Map map);
}
