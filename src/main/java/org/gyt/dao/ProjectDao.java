package org.gyt.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface ProjectDao {

    @Select("<script>"
        + "select id,project_no projectNo,project_name projectName,project_price projectPrice "
        + "from t_project "
        + "where 1=1 "
        + "<if test=\"id !=null and id!='' \">"
        + "and id = #{id} "
        + "</if>"
        + "<if test=\"project_no !=null and project_no!='' \">"
        + "and project_no = #{project_no} "
        + "</if>"
        + "<if test=\"project_name !=null and project_name!='' \">"
        + "and project_name = #{project_name} "
        + "</if>"
        + "<if test=\"start !=null and start!='' \">"
        + "limit #{start},#{size} "
        + "</if>"
        + "</script>")
    public List<Map> findProjects(Map<String, Object> map);

    @Insert("<script>"
        + "insert into "
        + "t_project(project_no,project_name,project_price) "
        + "values(#{project_no},#{project_name},#{project_price})"
        + "</script>")
    public int insertProject(Map map);

    @Update("<script>"
        + "update t_project "
        + "set "
        + "project_no = #{project_no},project_name=#{project_name},project_price=#{project_price},"
        + "where id = #{id}"
        + "</script>")
    public int updateProject(Map map);

}
