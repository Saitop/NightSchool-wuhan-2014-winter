package org.nightschool.controller;

import org.apache.ibatis.session.SqlSession;
import org.nightschool.mapper.CartMapper;
import org.nightschool.mapper.CommodityMapper;
import org.nightschool.model.SinglePurchaseInfo;
import org.nightschool.mybatis.MyBatisUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/2/28.
 */
@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
public class CartController {
    CartMapper mapper;
    SqlSession session;
    public CartController() throws IOException {
        this.session= MyBatisUtil.getFactory().openSession();
        this.mapper = this.session.getMapper(CartMapper.class);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SinglePurchaseInfo> getCart(int id) throws IOException {
        List<SinglePurchaseInfo> inCart = mapper.getCart("inCart", id);
        return inCart;
    }
    @GET
    @Path("/num/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public int getCartSize(@PathParam("id")int id) throws IOException {
        int cartSize = mapper.getCartSize("inCart", id);
        return cartSize;
    }
    @POST
    @Path("addToCart")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SinglePurchaseInfo> addToCart(SinglePurchaseInfo s) throws IOException {
        mapper.insert(s);
        session.commit();
        List<SinglePurchaseInfo> inCart = mapper.getCart("inCart", s.getBuyerId());
        return inCart;
    }
    @POST
    @Path("isInCart")
    public boolean isInCart(SinglePurchaseInfo s) throws IOException {
        boolean inCart = mapper.isInCart(s);
        return inCart;

    }
    @POST
    @Path("modify/{id}")
    public void modifyNumInCart(int count,@PathParam("id") int id) throws IOException {
        mapper.updateNum(count, id);
        session.commit();
    }
    @POST
    @Path("changeStatus/{uid}")
    public List<SinglePurchaseInfo> changeStatus(int[] select,@PathParam("uid")int uid) throws IOException {
        List<SinglePurchaseInfo> singlePurchaseInfos = mapper.getMutilInfoByIdArray(select);
        for(SinglePurchaseInfo s:singlePurchaseInfos){
            CommodityMapper commodityMapper = session.getMapper(CommodityMapper.class);
            int id=s.getCommodityId();
            int num=s.getNum();
            commodityMapper.updateSaleAndStockById(num,id);
        }
        mapper.updateMutilStatus("Order", select);
        session.commit();
        return getCart(uid);
    }
    @POST
    @Path("deleteMore/{uid}")
    public List<SinglePurchaseInfo> deleteMore(int[] select,@PathParam("uid")int uid) throws IOException {
        mapper.deleteMutil(select);
        session.commit();
        return getCart(uid);
    }
    @POST
    @Path("delete/{id}")
    public void  removeFromCart(@PathParam("id")int id) throws IOException {
        mapper.delete(id);
        session.commit();
    }
}
