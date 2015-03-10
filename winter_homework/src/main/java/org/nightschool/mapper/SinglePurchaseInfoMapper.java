package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.nightschool.model.SinglePurchaseInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/2/28.
 */
public interface SinglePurchaseInfoMapper {
    @Select("select * from single_purchase_info;")
    public List<SinglePurchaseInfo> getSinglePurchases();

    @Insert("insert into single_purchase_info(buyer_id,commodity_id,num,date,status)" +
            "Values(#{buyerId},#{commodityId},#{num},#{date},#{status});")
    public boolean insert(SinglePurchaseInfo singlePurchaseInfo);

    @Select("select * from single_purchase_info where status = #{status};")
    @Results(value = {
            @Result(property = "commodityId", column = "commodity_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "buyerId", column = "buyer_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
    })
    public List<SinglePurchaseInfo> getInfoByStatus(String status);

    @Select("select * from single_purchase_info where id = #{id};")
    @Results(value = {
            @Result(property = "commodityId", column = "commodity_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "buyerId", column = "buyer_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
    })
    public SinglePurchaseInfo getInfoById(int id);

    @Select
            ({"<script>",
                    "select * from",
                    "single_purchase_info",
                    "WHERE id IN",
                    "<foreach item='item' index='index' collection='list'",
                    "open='(' separator=',' close=')'>",
                    "#{item}",
                    "</foreach>",
                    "</script>"})
    @Results(value = {
            @Result(property = "commodityId", column = "commodity_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "buyerId", column = "buyer_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "num", column = "num", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
    })
    public List<SinglePurchaseInfo> getMutilInfoByIdArray( @Param("list") int[] id);

    @Delete("delete from single_purchase_info where id = #{id};")
    public boolean delete(int id);

    @Update
            ({"<script>",
                    "update",
                    "single_purchase_info set status = #{0}",
                    "WHERE id IN",
                    "<foreach item='item' index='index' collection='list'",
                    "open='(' separator=',' close=')'>",
                    "#{item}",
                    "</foreach>",
                    "</script>"})
    public boolean updateMutilStatus(String status, @Param("list") int[] id);




    @Update("update single_purchase_info set num = #{0} where id=#{1} ")
    public boolean updateNum(int num, int id);

    @Select("select count(*)from single_purchase_info where buyer_id=#{buyerId} and commodity_id=#{commodityId} and status = 'inCart'")
    public int isInCart(SinglePurchaseInfo singlePurchaseInfo);


}
