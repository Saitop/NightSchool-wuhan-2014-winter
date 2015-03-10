package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.nightschool.model.Commodity;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/2/22.
 */
public interface CommodityMapper {
    @Insert("insert into commodity(name, com_desc,img_url,old_price,new_price,stock,sales_volume,creator_id,free_shipping,seven_days_return) " +
            "Values(#{name},#{comDesc},#{imgUrl},#{oldPrice},#{newPrice},#{stock},#{salesVolume},#{creatorId},#{freeShipping},#{sevenDaysReturn});")
    public boolean insert(Commodity commodity);

    @Select("select * from commodity")
    @Results(value = {
            @Result(property = "comDesc", column = "com_desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "imgUrl", column = "img_url", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "oldPrice", column = "old_price", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "newPrice", column = "new_price", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "stock", column = "stock", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "salesVolume", column = "sales_volume", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "creatorId", column = "creator_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "freeShipping", column = "free_shipping", javaType = Boolean.class, jdbcType = JdbcType.BOOLEAN),
            @Result(property = "sevenDaysReturn", column = "seven_days_return", javaType = Boolean.class, jdbcType = JdbcType.BOOLEAN),
            @Result(property = "publishDate", column = "publish_date", javaType = Date.class, jdbcType = JdbcType.DATE)})
    public List<Commodity> getCommodities();

    @Delete("delete from commodity where id=#{id}")
    void deleteByID(int id);

    @Select("select * from commodity where id=#{0}")
    @Results(value = {
            @Result(property = "comDesc", column = "com_desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "imgUrl", column = "img_url", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "oldPrice", column = "old_price", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "newPrice", column = "new_price", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "stock", column = "stock", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "salesVolume", column = "sales_volume", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "creatorId", column = "creator_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "freeShipping", column = "free_shipping", javaType = Boolean.class, jdbcType = JdbcType.BOOLEAN),
            @Result(property = "sevenDaysReturn", column = "seven_days_return", javaType = Boolean.class, jdbcType = JdbcType.BOOLEAN),
            @Result(property = "publishDate", column = "publish_date", javaType = Date.class, jdbcType = JdbcType.DATE)})
    public Commodity getById(int id);

    @Select
    ({"<script>",
            "SELECT *",
            "FROM commodity",
            "WHERE id IN",
            "<foreach item='item' index='index' collection='list'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    @Results(value = {
            @Result(property = "comDesc", column = "com_desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "imgUrl", column = "img_url", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "oldPrice", column = "old_price", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "newPrice", column = "new_price", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "stock", column = "stock", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "salesVolume", column = "sales_volume", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "creatorId", column = "creator_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "freeShipping", column = "free_shipping", javaType = Boolean.class, jdbcType = JdbcType.BOOLEAN),
            @Result(property = "sevenDaysReturn", column = "seven_days_return", javaType = Boolean.class, jdbcType = JdbcType.BOOLEAN),
            @Result(property = "publishDate", column = "publish_date", javaType = Date.class, jdbcType = JdbcType.DATE)})
    public  List<Commodity> getByIdList(@Param("list") int[] id);

    @Update("update commodity set sales_volume=sales_volume+#{0},stock=stock-#{0}where id=#{1}")
    public void updateNumById(int num,int id);

    @Delete
            ({"<script>",
                    "DELETE",
                    "FROM commodity",
                    "WHERE id IN",
                    "<foreach item='item' index='index' collection='list'",
                    "open='(' separator=',' close=')'>",
                    "#{item}",
                    "</foreach>",
                    "</script>"} )
    public void deleteByIdList(@Param("list") int[] id);
}
