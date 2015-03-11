package mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nightschool.mapper.CommodityMapper;
import org.nightschool.model.Commodity;
import org.nightschool.mybatis.MyBatisUtil;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2015/2/23.
 */
public class CommodityMapperTest {
    CommodityMapper mapper;
    private SqlSession session;

    @Before
    public void before() throws IOException {
        session = MyBatisUtil.getFactory().openSession();
        mapper= session.getMapper(CommodityMapper.class);
    }
    @After
    public void after() throws IOException {
        session.close();
    }

    @Test
    public void should_get_all_commodities() throws Exception {
        List<Commodity> commodities = mapper.getCommodities();
        assertThat(commodities.size(),is(3));
    }

    @Test
    public void test_insert_one_record() throws Exception {
        mapper.insert(new Commodity("book","desc","imgUrl",12,2,200,22,1,true,false));
        assertThat(mapper.getCommodities().size(), is(4));
    }


    @Test
    public void the_size_of_commodities_will_reduce_two_after_delete() throws Exception {
        int deleteId[]={1,2};
        mapper.deleteByIdList(deleteId);
        List<Commodity> commodities = mapper.getCommodities();
        assertThat(commodities.size(),is(1));
    }
    @Test
    public void should_get_commodity_if_id_is_right() throws Exception {
        Commodity commodity = mapper.getById(1);
        assertThat(commodity.getName(),is("牙刷"));
    }
    @Test
    public void get_mutil_commditier_by_id_array() throws Exception {
     int a[]={1,2};
        List<Commodity> commodityList = mapper.getByIdList(a);
        assertThat(commodityList.size(),is(2));
    }

    @Test
    public void modify_sales_and_stock_by_id_and_buy_num() throws Exception {
        assertThat(mapper.getById(1).getStock(),is(23));
        assertThat(mapper.getById(1).getSalesVolume(),is(12));
        mapper.updateSaleAndStockById(2,1);
        assertThat(mapper.getById(1).getStock(),is(21));
        assertThat(mapper.getById(1).getSalesVolume(),is(14));
    }

    @Test
    public void modify_commodity() throws Exception {
        Commodity commodity = mapper.getById(1);
        commodity.setComDesc("新的描述信息");
        mapper.update(commodity);
        assertThat(mapper.getById(1).getComDesc(),is("新的描述信息"));

    }
}

