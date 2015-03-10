package org.nightschool.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.nightschool.model.SinglePurchaseInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/2/28.
 */
public interface CartMapper {
    @Select("select * from single_purchase_info where status = #{0} and buyer_id=#{1};")
    @Results(value = {
            @Result(property = "buyerId", column = "buyer_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "date", column = "date", javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(property = "commodityId", column = "commodity_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER) })
    public List<SinglePurchaseInfo> getCart(String status,int userID);

    @Select("select count(*) from single_purchase_info where status = #{0} and buyer_id=#{1};")
    public int getCartSize(String status,int userID);
}
