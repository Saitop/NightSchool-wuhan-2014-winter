package mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.nightschool.mapper.SinglePurchaseInfoMapper;
import org.nightschool.model.SinglePurchaseInfo;
import org.nightschool.mybatis.MyBatisUtil;

import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2015/2/28.
 */
public class SinglePurchaseInfoMapperTest {
    SinglePurchaseInfoMapper mapper;
    @Test
    public void inser_one_purchase_record() throws Exception {
        mapper= MyBatisUtil.getFactory().openSession().getMapper(SinglePurchaseInfoMapper.class);
        mapper.insert(new SinglePurchaseInfo(2,1,2,new Date(),"addCart"));
    }

    @Test
    public void get_single_record_where_status_is_inCart() throws Exception {
        mapper= MyBatisUtil.getFactory().openSession().getMapper(SinglePurchaseInfoMapper.class);
        List<SinglePurchaseInfo> list = mapper.getInfoByStatus("inCart");
        assertThat(list.size(),is(1));
    }

    @Test
    public void delete_one_data_by_id() throws Exception {
        mapper= MyBatisUtil.getFactory().openSession().getMapper(SinglePurchaseInfoMapper.class);
        mapper.deleteSinglePurchaseInfo(1);
        List<SinglePurchaseInfo> list = mapper.getInfoByStatus("inCart");
        assertThat(list.size(),is(0));
    }

    @Test
    public void get_info_by_id() throws Exception {
        mapper= MyBatisUtil.getFactory().openSession().getMapper(SinglePurchaseInfoMapper.class);
        SinglePurchaseInfo info = mapper.getInfoById(1);
        assertThat(info.getStatus(), is("inCart"));
    }

    @Ignore
    @Test
    public void change_info_status() throws Exception {
        mapper= MyBatisUtil.getFactory().openSession().getMapper(SinglePurchaseInfoMapper.class);
        SqlSession sqlSession = MyBatisUtil.getFactory().openSession();
        SinglePurchaseInfoMapper m= sqlSession.getMapper(SinglePurchaseInfoMapper.class);
        m.updateStatusSinglePurchaseInfo("Order", 2);
        sqlSession.commit();
        assertThat(mapper.getInfoById(1).getStatus(), is("Order"));
    }

    @Test
    public void should_get_more_than_one_if_in_cart() throws Exception {
        mapper= MyBatisUtil.getFactory().openSession().getMapper(SinglePurchaseInfoMapper.class);
        int inCart = mapper.isInCart(new SinglePurchaseInfo(2, 1, 2, new Date(), "addCart"));
        assertThat(inCart, is(1));
    }
    @Test
    public void should_get_zero_if_not_in_cart() throws Exception {
        mapper= MyBatisUtil.getFactory().openSession().getMapper(SinglePurchaseInfoMapper.class);
        int inCart = mapper.isInCart(new SinglePurchaseInfo(3, 1, 2, new Date(), "addCart"));
        assertThat(inCart,is(0));
    }


}
