package org.nightschool.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.nightschool.model.User;

import java.util.List;

/**
 * Created by Administrator on 2015/2/21.
 */
public interface UserMapper {
    @Select("select * from web_user")
    public List<User> getUsers();

    @Insert("insert into web_user(name, password) Values(#{userName},#{password});")
    public boolean insert(User user);

    @Select("select * from web_user where name like #{name}")
    @Results(value = {
            @Result(property = "userName", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    public User findByName(String name);

    @Select("select count(*) from web_user where name like #{name}")
    public boolean isUserExist(String name);

    @Select("select count(*) from web_user where name = #{userName} and password = #{password}")
    @Result(javaType = Boolean.class, jdbcType = JdbcType.INTEGER)
    public boolean isPasswordCorrect(User user);
    /*modify*/
}
