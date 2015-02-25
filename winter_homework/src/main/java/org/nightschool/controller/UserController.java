package org.nightschool.controller;

import org.nightschool.mapper.UserMapper;
import org.nightschool.model.User;
import org.nightschool.mybatis.MyBatisUtil;

import javax.ws.rs.*;
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
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean login(User u) throws IOException {
        UserMapper mapper = MyBatisUtil.getFactory().openSession().getMapper(UserMapper.class);
        boolean login =mapper.isPasswordCorrect(u);
        return login;
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean register(User u) throws IOException {
        UserMapper mapper = MyBatisUtil.getFactory().openSession().getMapper(UserMapper.class);
        System.out.println(u.getUserName()+u.getPassword());
        boolean result=mapper.insert(u);
        return result;
    }
}
