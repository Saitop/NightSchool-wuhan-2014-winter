package integration;

import org.junit.Test;
import org.nightschool.dao.JDBCService;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JDBCServiceIntegrationTest {

    @Test
    public void shouldGetDataFromDatabase() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        JDBCService jdbcService = new JDBCService();

        ResultSet resultSet = jdbcService.select("select * from item");

        resultSet.next();

        assertThat(resultSet.getString("name"), is("小清新光盘"));
    }

    @Test
    public void should_Get_Data_After_Insert_Data() throws Exception {
        JDBCService jdbcService = new JDBCService();
        boolean flag = jdbcService.insert("INSERT INTO item(name, img_url, description, price, count) VALUES('cyy','url','price',10,100)");
        ResultSet resultSet = jdbcService.select("select * from item where name ='cyy'");
        resultSet.next();
        assertThat(resultSet.getString("name"), is("cyy"));
    }
}
