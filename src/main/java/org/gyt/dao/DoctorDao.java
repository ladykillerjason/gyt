package org.gyt.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface DoctorDao {


    @Select("<script>"
        + "select doc_no docNo,doc_name docName,doc_pass docPass,doc_phone docPhone,doc_title docTitle,doc_role docRole from t_doctor "
        + "where 1=1 "
        + "<if test=\"doc_no !=null and doc_no!='' \">"
        + "and doc_no = #{doc_no} "
        + "</if>"
        + "<if test=\"doc_name !=null and doc_name!='' \">"
        + "and doc_name = #{doc_name} "
        + "</if>"
        + "<if test=\"doc_pass !=null and doc_pass!='' \">"
        + "and doc_pass = #{doc_pass} "
        + "</if>"
        + "<if test=\"doc_phone !=null and doc_phone!='' \">"
        + "and doc_phone = #{doc_phone} "
        + "</if>"
        + "<if test=\"doc_title !=null and doc_title!='' \">"
        + "and doc_title = #{doc_title} "
        + "</if>"
        + "<if test=\"doc_role !=null and doc_role!='' \">"
        + "and doc_role = #{doc_role} "
        + "</if>"
        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> findDoctors(Map<String, Object> map);


    @Insert("<script>"
        + "insert into "
        + "t_doctor(doc_no,doc_pass,doc_name,doc_phone,doc_title,doc_role) "
        + "values(#{doc_no},#{doc_pass},#{doc_name},#{doc_phone},#{doc_title},#{doc_role})"
        + "</script>")
    public int insertDoctor(Map map);

    @Update("<script>"
        + "update t_doctor "
        + "set "
        + "doc_pass=#{doc_pass},doc_name=#{doc_name},doc_phone=#{doc_phone},"
        + "doc_title=#{doc_title},doc_role=#{doc_role} "
        + "where doc_no = #{doc_no}"
        + "</script>")
    public int updateDoctor(Map map);

}
