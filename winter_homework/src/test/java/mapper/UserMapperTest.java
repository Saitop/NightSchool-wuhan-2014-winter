package mapper;

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
        mapper = MyBatisUtil.getFactory().openSession().getMapper(UserMapper.class);
        mapper.insert(new User("Jane", "password"));
    }

    @Test
    public void should_get_users_and_num_is_zero() throws Exception {
        List<User> users=mapper.getUsers();
        assertThat(users.size(),is(1));
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
        boolean r=true;
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
        User u=new User("Jane","password");
        boolean passwordCorrect = mapper.isPasswordCorrect(u);
        assertTrue(passwordCorrect);
    }
    @Test
    public void password_is_wrong() throws Exception {
        User u=new User("Jane","password0");
        boolean passwordCorrect = mapper.isPasswordCorrect(u);
        assertFalse(passwordCorrect);
    }
}
