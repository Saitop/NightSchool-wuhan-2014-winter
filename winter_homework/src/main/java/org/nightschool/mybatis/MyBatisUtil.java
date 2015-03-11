package org.nightschool.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jane on 2015/1/10.
 */
public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    public static SqlSessionFactory getFactory() throws IOException {
        String resource = "mybatis/config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        if(sqlSessionFactory == null)
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
}
