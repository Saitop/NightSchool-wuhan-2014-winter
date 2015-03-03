package org.nightschool.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.nightschool.model.Order;

import java.util.List;

/**
 * Created by Administrator on 2015/2/28.
 */
public interface OrderMapper {
    @Insert("insert into db_order(buyer_id,seller_id,date,status) " +
            "Values(#{buyerId},#{sellerId},current_date,#{status});")
    public boolean insert(Order order);

    @Select("select * from db_order where buyer_id=#{userId}")
    public List<Order> getOrderByUserId(int userId);

    @Select("select * from db_order where seller_id=#{sellerId}")
    public List<Order> getOrderBySellerId(int sellerId);

    @Delete("delete from db_order  where id=#{id}")
    public boolean delete(int id);
}
