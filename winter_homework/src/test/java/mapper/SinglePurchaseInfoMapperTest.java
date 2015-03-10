package mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nightschool.mapper.SinglePurchaseInfoMapper;
import org.nightschool.model.SinglePurchaseInfo;
import org.nightschool.mybatis.MyBatisUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2015/2/26.
 */
public class SinglePurchaseInfoMapperTest {
    SinglePurchaseInfoMapper mapper;
    SqlSession session;

    @Before
    public void before() throws IOException {
        session = MyBatisUtil.getFactory().openSession();
        mapper= session.getMapper(SinglePurchaseInfoMapper.class);
    }
    @After
    public void after() throws IOException {
        session.close();
    }

    @Test
    public void get_all_single_purchase_info() throws Exception {
        assertThat(mapper.getSinglePurchases().size(),is(3));
    }

    @Test
    public void inser_one_purchase_record() throws Exception {
        mapper.insert(new SinglePurchaseInfo(2,1,2,new Date(),"addCart"));
        assertThat(mapper.getSinglePurchases().size(),is(4));
    }

    @Test
    public void get_single_record_where_status_is_inCart() throws Exception {
        List<SinglePurchaseInfo> list = mapper.getInfoByStatus("inCart");
        assertThat(list.size(),is(2));
    }

    @Test
    public void get_single_record_by_id() throws Exception {
        mapper= MyBatisUtil.getFactory().openSession().getMapper(SinglePurchaseInfoMapper.class);
        SinglePurchaseInfo info = mapper.getInfoById(1);
        assertThat(info.getStatus(), is("inCart"));
    }

    @Test
    public void delete_one_data_by_id() throws Exception {
        mapper.delete(1);
        List<SinglePurchaseInfo> list = mapper.getSinglePurchases();
        assertThat(list.size(),is(2));
    }

    @Test
    public void update_status() throws Exception {
        int a[]={1,2};
        mapper.updateMutilStatus("Order",a);
        List<SinglePurchaseInfo> list = mapper.getSinglePurchases();
        assertThat(list.get(0).getStatus(),is("Order"));
    }

    @Test
    public void update_num() throws Exception {
        mapper.updateNum(3,1);
        SinglePurchaseInfo purchaseInfo = mapper.getInfoById(1);
        assertThat(purchaseInfo.getNum(),is(3));
    }

    @Test
    public void return_true_if_exist_in_cart() throws Exception {
        SinglePurchaseInfo purchaseInfo = mapper.getInfoById(1);
        int count=mapper.isInCart(purchaseInfo);
        assertTrue(count > 0);
    }
}
