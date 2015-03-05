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
    @Insert("insert into commodity(name, com_desc,img_url,old_price,new_price,stock,sales_volume,owner_id,publish_date) " +
            "Values(#{name},#{comDesc},#{imgUrl},#{oldPrice},#{newPrice},#{stock},#{salesVolume},#{ownerId},current_date);")
    public boolean insert(Commodity commodity);

    @Select("select * from commodity")
    @Results(value = {
            @Result(property = "comDesc", column = "com_desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "imgUrl", column = "img_url", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "oldPrice", column = "old_price", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "newPrice", column = "new_price", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "stock", column = "stock", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "salesVolume", column = "sales_volume", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "ownerId", column = "owner_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "publishDate", column = "publish_date", javaType = Date.class, jdbcType = JdbcType.DATE)})
    public List<Commodity> getCommodities();

    @Delete("delete from commodity where id=#{id}")
    void deleteByID(int id);

    @Select("select id from commodity where name=#{0}and owner_id=#{1}")
    int getIdByName(String name,int sellerId);

    @Select("select * from commodity where id=#{0}")
    @Results(value = {
            @Result(property = "comDesc", column = "com_desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "imgUrl", column = "img_url", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "oldPrice", column = "old_price", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "newPrice", column = "new_price", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "stock", column = "stock", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "salesVolume", column = "sales_volume", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "ownerId", column = "owner_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "publishDate", column = "publish_date", javaType = Date.class, jdbcType = JdbcType.DATE)})
    public Commodity getById(int id);

}
