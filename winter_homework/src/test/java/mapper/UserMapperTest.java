package mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.nightschool.mapper.UserMapper;
import org.nightschool.model.User;
import org.nightschool.mybatis.MyBatisUtil;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 2015/2/21.
 */
public class UserMapperTest {
    UserMapper mapper;

    public UserMapperTest() throws IOException {
        SqlSession session = MyBatisUtil.getFactory().openSession();
        mapper = session.getMapper(UserMapper.class);
    }

    @Test
    public void should_get_users_and_num_is_three() throws Exception {
        List<User> users=mapper.getUsers();
        assertThat(users.size(),is(3));
    }
    @Test
    public void should_get_data_after_insert_user() throws Exception {
        List<User> users = mapper.getUsers();
        int len=users.size();
        mapper.insert(new User("Jane2", "password2"));
        users = mapper.getUsers();
        assertThat(users.size(), is(1+len));
    }


    @Test
    public void insert_data_success(){
        boolean r=false;
        r = mapper.insert(new User("Jane2", "password2"));
        assertTrue(r);
    }
//    @Test
//    public void insert_data_unsuccess(){
//        boolean r= mapper.insert(new User("Jane2", "passworghfsdjdjdsjfshgdfjjdd2"));
//        assertTrue(r);
//    }
    @Test
    public void find_user_by_name() throws Exception {
        User jane = mapper.findByName("Jane");
       assertThat(jane.getUserName(), is("Jane"));
    }

    @Test
    public void is_user_exist() throws Exception {
        boolean userExist = mapper.isUserExist("Jane");
        assertTrue(userExist);
    }
    @Test
    public void is_user_not_exist() throws Exception {
        boolean userExist = mapper.isUserExist("Jim");
        assertFalse(userExist);
    }

    @Test
    public void password_is_right() throws Exception {
        User u=new User("Jane","Password");
        boolean passwordCorrect = mapper.isPasswordCorrect(u);
        assertTrue(passwordCorrect);
    }
    @Test
    public void password_is_wrong() throws Exception {
        User u=new User("Jane","Password0");
        boolean passwordCorrect = mapper.isPasswordCorrect(u);
        assertFalse(passwordCorrect);
    }

    @Test
    public void should_get_true_if_is_admin() throws Exception {
       String name="admin";
        boolean isAdmin = mapper.isAdmin(name);
        assertTrue(isAdmin);
    }


    @Test
    public void should_get_false_if_is_not_admin() throws Exception {
        String name="Jane";
        boolean isAdmin = mapper.isAdmin(name);
        assertFalse(isAdmin);
    }

    @Test
    public void get_id_by_name() throws Exception {
        String name="Jane";
       int id= mapper.getIdByName(name);
        assertThat(id,is(1));
    }
}
