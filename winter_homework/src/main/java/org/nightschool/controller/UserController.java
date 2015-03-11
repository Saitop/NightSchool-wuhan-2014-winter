package org.nightschool.controller;

import org.apache.ibatis.session.SqlSession;
import org.nightschool.mapper.UserMapper;
import org.nightschool.model.User;
import org.nightschool.mybatis.MyBatisUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by Administrator on 2015/2/25.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController{
    SqlSession session;
    UserMapper mapper;
    public UserController() throws IOException {
        this.session = MyBatisUtil.getFactory().openSession();
        this.mapper = session.getMapper(UserMapper.class);
    }

    @POST
    @Path("isUserExist")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean isUserExist(String name) throws IOException {
        String subString=(String)name.subSequence(1,name.length()-1);
        boolean userExist = mapper.isUserExist(subString);
        return userExist;
    }
    @POST
    @Path("isAdmin")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean isAdmin(String name) throws IOException {
        String subString=(String)name.subSequence(1,name.length()-1);
        boolean isAdmin = mapper.isAdmin(subString);
        return isAdmin;
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public int login(User u) throws IOException {
        if(mapper.isPasswordCorrect(u)){
            return mapper.getIdByName(u.getUserName());
        }
        else{
        return 0;
        }
    }

    @POST
    @Path("adminLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    public int adminLogin(User u) throws IOException {
        if(mapper.isAdminPasswordCorrect(u)){
            return mapper.getAdminIdByName(u.getUserName());
        }
        else{
        return 0;
        }
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean register(User u) throws IOException {
        boolean result=mapper.insert(u);
        session.commit();
        return result;
    }
    @GET
    @Path("getAdminId")
   public int getAdminIdByName(String name) throws IOException {
        int idByName = mapper.getAdminIdByName(name);
        return  idByName;
    }
}
