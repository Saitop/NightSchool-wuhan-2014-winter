package org.nightschool.controller;

import org.apache.ibatis.session.SqlSession;
import org.nightschool.mapper.UserMapper;
import org.nightschool.model.User;
import org.nightschool.mybatis.MyBatisUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by Administrator on 2015/2/22.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController{
    @POST
    @Path("isUserExist")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean isUserExist(String name) throws IOException {
        UserMapper mapper = MyBatisUtil.getFactory().openSession().getMapper(UserMapper.class);
        String subString=(String)name.subSequence(1,name.length()-1);
        boolean userExist = mapper.isUserExist(subString);
        return userExist;
    }
    @POST
    @Path("isAdmin")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean isAdmin(String name) throws IOException {
        UserMapper mapper = MyBatisUtil.getFactory().openSession().getMapper(UserMapper.class);
        String subString=(String)name.subSequence(1,name.length()-1);
        boolean isAdmin = mapper.isAdmin(subString);
        return isAdmin;
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean login(User u) throws IOException {
        UserMapper mapper = MyBatisUtil.getFactory().openSession().getMapper(UserMapper.class);
        boolean login =mapper.isPasswordCorrect(u);

        return login;
    }
    @POST
    @Path("adminLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean adminLogin(User u) throws IOException {
        UserMapper mapper = MyBatisUtil.getFactory().openSession().getMapper(UserMapper.class);
        boolean login =mapper.isAdminPasswordCorrect(u);
        return login;
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean register(User u) throws IOException {
        SqlSession session = MyBatisUtil.getFactory().openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        boolean result=mapper.insert(u);
        session.commit();
        return result;
    }
}
