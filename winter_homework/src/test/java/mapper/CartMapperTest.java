package mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nightschool.mapper.CartMapper;
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
public class CartMapperTest {
    CartMapper mapper;
    private SqlSession session;

    @Before
    public void before() throws IOException {
        session = MyBatisUtil.getFactory().openSession();
        mapper= session.getMapper(CartMapper.class);
    }
    @After
    public void after() throws IOException {
        session.close();
    }

    @Test
    public void get_cart_detail_info_by_user_id() throws Exception {
        List<SinglePurchaseInfo> singlePurchaseInfos = mapper.getCart("inCart", 2);
        assertThat(singlePurchaseInfos.size(),is(2));
    }
    @Test
    public void get_cart_size_by_user_id() throws Exception {
        int cartSize = mapper.getCartSize("inCart", 2);
        assertThat(cartSize,is(2));
    }
    @Test
    public void get_multi_purchase_by_id_list() throws Exception {
        int select[]={1,2};
        List<SinglePurchaseInfo> mutilInfoByIdArray = mapper.getMutilInfoByIdArray(select);
        assertThat(mutilInfoByIdArray.size(), is(2));
    }

    @Test
    public void insert_one_record() throws Exception {
        mapper.insert(new SinglePurchaseInfo(2,1,2,new Date(),"inCart"));
        assertThat(mapper.getCart("inCart", 2).size(),is(3));
    }
    @Test
    public void get_true_if_have_been_in_cart() throws Exception {
        int select[]={1};
        List<SinglePurchaseInfo> mutilInfoByIdArray = mapper.getMutilInfoByIdArray(select);
        SinglePurchaseInfo singlePurchaseInfo = mutilInfoByIdArray.get(0);
        assertTrue(mapper.isInCart(singlePurchaseInfo));
    }

    @Test
    public void update_num_by_single_purchase_id() throws Exception {
        mapper.updateNum(3,1);
        assertThat(mapper.getInfoById(1).getNum(),is(3));
    }

    @Test
    public void delete_one_data_by_id() throws Exception {
        int size = mapper.getSize();
        mapper.delete(1);
        assertThat(mapper.getSize(),is(size-1));
    }
    @Test
    public void delete_mutil_data_by_id_list() throws Exception {
        int select[]={1,2};
        int size = mapper.getSize();
        mapper.deleteMutil(select);
        assertThat(mapper.getSize(),is(size-2));
    }


    @Test
    public void update_mutil_status_by_id_list() throws Exception {
        int select[]={1,2};
        mapper.updateMutilStatus("Order",select);
        List<SinglePurchaseInfo> mutilInfoByIdArray = mapper.getMutilInfoByIdArray(select);
        for(SinglePurchaseInfo s:mutilInfoByIdArray){
            assertThat(s.getStatus(),is("Order"));
        }
    }
}
