package org.gyt.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TreatBillDao {

    @Select("<script>"
        + "select d.doc_name docName,p1.patient_name patientName,p2.proj_name projectName,"
        + "b.treat_total_count treatTotalCount,b.treat_use_count treatUseCount,"
        + "b.create_time createTime,b.is_over isOver,b.over_time overTime "
        + "from t_treat_bill b left join t_doctor d on b.kaidan_id = d.id "
        + "left join t_patient p1 on b.patient_id = p1.id "
        + "left join t_project p2 on b.proj_id = p2.id "
        + "where 1=1 "
        + "<if test=\"id !=null and id!='' \">"
        + "and id = #{id} "
        + "</if>"
        + "<if test=\"doc_no !=null and doc_no!='' \">"
        + "and d.doc_no = #{doc_no} "
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

        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> findTreatBills(Map<String, Object> map);

    /**
     * 数据数目
     *
     * @param map
     * @return
     */
    public Long getTotalTreatBills(Map<String, Object> map);

    @Insert("<script>"
        + "insert into "
        + "t_treat_bill(kaidan_id,patient_id,proj_id,treat_total_count,treat_use_count,create_time,is_over,over_time) "
        + "values(#{kaidan_id},#{patient_id},#{proj_id},#{treat_total_count},#{treat_use_count},#{create_time},#{is_over},#{over_time})"
        + "</script>")
    public int insertTreatBill(Map map);


}
