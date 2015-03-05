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
    public void get_commodity_id_by_name_if_operater_is_right() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        int id=mapper.getIdByName("牙刷",1);
        assertTrue(id==1);
    }

    @Test
    public void test_insert_one_record() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        mapper.insert(new Commodity("book","desc","imgUrl",12,2,200,22,1));
        assertThat(mapper.getCommodities().size(), is(2));
    }

    @Test
    public void test_delete_one_record() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        mapper.insert(new Commodity("book","desc","imgUrl",12,2,200,22,1));
        assertThat(mapper.getCommodities().size(),is(2));
        int id=mapper.getIdByName("book",1);
        mapper.deleteByID(id);
        assertThat(mapper.getCommodities().size(),is(1));
    }

    @Test
    public void should_get_commodity_if_id_is_right() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        Commodity commodity = mapper.getById(1);
        assertThat(commodity.getName(),is("牙刷"));
    }
}
