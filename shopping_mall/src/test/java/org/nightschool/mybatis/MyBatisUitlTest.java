package org.nightschool.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Jane on 2015/1/10.
 */
public class MyBatisUitlTest {
    @Test
    public void test_should_get_seesion_factory() throws Exception {
        SqlSessionFactory factory= MyBatisUtil.getFactory();
        assertNotNull(factory);
    }
}
