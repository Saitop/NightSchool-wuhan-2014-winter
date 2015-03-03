package mapper;

import org.junit.Test;
import org.nightschool.mapper.OrderMapper;
import org.nightschool.model.Order;
import org.nightschool.mybatis.MyBatisUtil;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2015/2/28.
 */
public class OrderMapperTest {
    OrderMapper mapper;

    @Test
    public void create_one_new_order() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(OrderMapper.class);
        Order order=new Order(1,1,"Wait");
        mapper.insert(order);
        assertThat(mapper.getOrderByUserId(1).size(),is(1));
    }

    @Test
    public void get_order_by_user_id() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(OrderMapper.class);
        assertThat(mapper.getOrderByUserId(2).size(),is(1));
    }
    @Test
    public void get_order_by_seller_id() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(OrderMapper.class);
        assertThat(mapper.getOrderBySellerId(1).size(),is(1));
    }

    @Test
    public void delete_by_order_id() throws Exception {
        mapper = MyBatisUtil.getFactory().openSession().getMapper(OrderMapper.class);
        mapper.delete(1);
        assertThat(mapper.getOrderByUserId(2).size(),is(0));
    }
}
