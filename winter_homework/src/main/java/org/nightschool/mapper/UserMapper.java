package org.nightschool.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.nightschool.model.User;

/**
 * Created by Administrator on 2015/2/21.
 */
public interface UserMapper {
    @Insert("insert into web_user(name, password) Values(#{userName},md5(#{password}||#{userName}));")
    public boolean insert(User user);

    @Select("select * from web_user where name like #{name};")
    @Results(value = {
            @Result(property = "userName", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    public User findByName(String name);

    @Select("select count(*) from (select name as username from web_user union select name as username from admin_user)as newTable where username like #{name};")
    @Result(javaType = Boolean.class, jdbcType = JdbcType.INTEGER)
    public boolean isUserExist(String name);

    @Select("select count(*) from admin_user where name like #{name};")
    public boolean isAdmin(String name);

    @Select("select count(*) from web_user where name = #{userName} and password =md5(#{password}||#{userName})")
    @Result(javaType = Boolean.class, jdbcType = JdbcType.INTEGER)
    public boolean isPasswordCorrect(User user);

    @Select("select count(*) from admin_user where name = #{userName} and password =md5(#{password}||#{userName})")
    @Result(javaType = Boolean.class, jdbcType = JdbcType.INTEGER)
    public boolean isAdminPasswordCorrect(User user);

    @Select("select id from web_user where name = #{userName}")
    public int getIdByName(String name);

    @Select("select id from admin_user where name = #{userName}")
    public int getAdminIdByName(String name);

}
