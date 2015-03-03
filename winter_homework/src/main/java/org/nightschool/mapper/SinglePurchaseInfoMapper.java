package org.nightschool.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.nightschool.model.SinglePurchaseInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/2/28.
 */
public interface SinglePurchaseInfoMapper {
    @Insert("insert into single_purchase_info(buyer_id,commodity_id,num,date,status)" +
            "Values(#{buyerId},#{commodityId},#{num},#{date},#{status});")
    public boolean insert(SinglePurchaseInfo singlePurchaseInfo);

    @Select("select * from single_purchase_info where status = #{status};")
    public List<SinglePurchaseInfo> getInfoByStatus(String status);

    @Select("select * from single_purchase_info where id = #{id};")
    public SinglePurchaseInfo getInfoById(int id);

    @Delete("delete from single_purchase_info where id = #{id};")
    public boolean deleteSinglePurchaseInfo(int id);

    @Update("update single_purchase_info set status = #{0} where id=#{1} ")
    public boolean updateStatusSinglePurchaseInfo(String status,int id);
}
