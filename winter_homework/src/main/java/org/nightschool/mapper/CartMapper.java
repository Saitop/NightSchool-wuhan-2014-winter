package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.nightschool.model.SinglePurchaseInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/2/26.
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

    @Insert("insert into single_purchase_info(buyer_id,commodity_id,num,date,status)" +
            "Values(#{buyerId},#{commodityId},#{num},#{date},#{status});")
    public boolean insert(SinglePurchaseInfo singlePurchaseInfo);

    @Select("select count(*)from single_purchase_info where buyer_id=#{buyerId} and commodity_id=#{commodityId} and status = 'inCart'")
    @Result(javaType = Boolean.class, jdbcType = JdbcType.INTEGER)
    public boolean isInCart(SinglePurchaseInfo singlePurchaseInfo);

    @Update("update single_purchase_info set num = #{0} where id=#{1} ")
    public boolean updateNum(int num, int id);

    @Delete("delete from single_purchase_info where id = #{id};")
    public boolean delete(int id);

    @Delete
            ({"<script>",
                    "delete",
                    "from single_purchase_info",
                    "WHERE id IN",
                    "<foreach item='item' index='index' collection='list'",
                    "open='(' separator=',' close=')'>",
                    "#{item}",
                    "</foreach>",
                    "</script>"})
    public boolean deleteMutil(@Param("list") int[] id);

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
    /*only used in test case*/
    @Select("select * from single_purchase_info where id = #{id};")
    public SinglePurchaseInfo getInfoById(int id);
    @Select("select count(*) from single_purchase_info;")
    public int getSize();
}
