package org.nightschool.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.nightschool.model.Disk;

import java.util.List;

/**
 * Created by Administrator on 2015/1/10.
 */
public interface DiskMapper {
    @Select("select * from item")
    public List<Disk> getDisks();
    @Insert("insert into item(name, img_url, description, price, count) Values(#{name},#{imgUrl},#{desc},#{number},#{price})")
    public int insert(Disk disk);
}
