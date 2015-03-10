package mapper;

import org.junit.Test;
import org.nightschool.mapper.CommodityMapper;
import org.nightschool.model.Commodity;
import org.nightschool.mybatis.MyBatisUtil;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2015/2/25.
 */
public class CommodityMapperTest {
    CommodityMapper mapper;
    @Test
    public void test_insert_one_record() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        mapper.insert(new Commodity("book","desc","imgUrl",12,2,200,22,1,true,false));
        assertThat(mapper.getCommodities().size(), is(4));
    }
    @Test
    public void should_get_all_commodities() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        List<Commodity> commodities = mapper.getCommodities();
        assertThat(commodities.size(),is(3));
    }
    @Test
    public void the_size_of_commodities_will_reduce_one_after_delete() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        mapper.deleteByID(1);
        List<Commodity> commodities = mapper.getCommodities();
        assertThat(commodities.size(),is(2));
    }
    @Test
    public void should_get_commodity_if_id_is_right() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        Commodity commodity = mapper.getById(1);
        assertThat(commodity.getName(),is("牙刷"));
    }
    @Test
    public void get_mutil_commditier_by_id_array() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
     int a[]={1,2};
        List<Commodity> commodityList = mapper.getByIdList(a);
        assertThat(commodityList.size(),is(2));
    }

    @Test
    public void modify_num_by_id() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(CommodityMapper.class);
        assertThat(mapper.getById(1).getStock(),is(23));
        assertThat(mapper.getById(1).getSalesVolume(),is(12));
        mapper.updateNumById(2,1);
        assertThat(mapper.getById(1).getStock(),is(21));
        assertThat(mapper.getById(1).getSalesVolume(),is(14));
    }
}

