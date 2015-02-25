package mapper;

import org.junit.Test;
import org.nightschool.mapper.CommodityMapper;
import org.nightschool.model.Commodity;
import org.nightschool.mybatis.MyBatisUtil;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2015/2/25.
 */
public class CommodityMapperTest {
    CommodityMapper mapper;

    @Test
    public void get_commodity_id_by_name() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        mapper.insert(new Commodity("Paper"));
        int id=mapper.getIdByName("Paper");
        assertTrue(id > 1);
    }

    @Test
    public void test_insert_one_record() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        mapper.insert(new Commodity());
        assertThat(mapper.getCommodities().size(), is(1));
    }

    @Test
    public void test_delete_one_record() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        mapper.insert(new Commodity("User"));
        assertThat(mapper.getCommodities().size(),is(1));
        int id=mapper.getIdByName("User");
        mapper.deleteByID(id);
        assertThat(mapper.getCommodities().size(),is(0));
    }


}
