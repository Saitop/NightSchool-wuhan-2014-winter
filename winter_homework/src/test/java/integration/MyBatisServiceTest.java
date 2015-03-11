package integration;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.nightschool.mybatis.MyBatisUtil;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2015/2/21.
 */
public class MyBatisServiceTest {
    @Test
    public void test_should_get_session() throws Exception {
        SqlSession session= MyBatisUtil.getFactory().openSession();
        assertNotNull(session);
    }
}
