package org.nightschool.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.nightschool.model.Commodity;

import java.util.List;

/**
 * Created by Administrator on 2015/2/22.
 */
public interface CommodityMapper {
    @Insert("insert into commodity(name, com_desc,img_url,old_price,new_price,stock,sales_volume,owner_id) " +
            "Values(#{name},#{comDesc},#{imgUrl},#{oldPrice},#{newPrice},#{stock},#{salesVolume},#{ownerId});")
    public boolean insert(Commodity commodity);

    @Select("select * from commodity")
    public List<Commodity> getCommodities();

    @Delete("delete from commodity where id=#{id}")
    void deleteByID(int id);

    @Select("select id from commodity where name=#{name}")
    int getIdByName(String name);
}
