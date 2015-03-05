package org.nightschool.controller;

import org.apache.ibatis.session.SqlSession;
import org.nightschool.mapper.CartMapper;
import org.nightschool.mapper.SinglePurchaseInfoMapper;
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
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SinglePurchaseInfo> getCart(int id) throws IOException {
        CartMapper mapper = MyBatisUtil.getFactory().openSession().getMapper(CartMapper.class);
        List<SinglePurchaseInfo> inCart = mapper.getCart("inCart", id);
        return inCart;
    }

    @POST
    @Path("addToCart")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SinglePurchaseInfo> addToCart(SinglePurchaseInfo s) throws IOException {
        SqlSession session = MyBatisUtil.getFactory().openSession();
        SinglePurchaseInfoMapper mapper = session.getMapper(SinglePurchaseInfoMapper.class);
        mapper.insert(s);
        session.commit();
        CartMapper cartMapper = MyBatisUtil.getFactory().openSession().getMapper(CartMapper.class);
        List<SinglePurchaseInfo> inCart = cartMapper.getCart("inCart", 2);
        return inCart;
    }

    @POST
    @Path("isInCart")
    public boolean isInCart(SinglePurchaseInfo s) throws IOException {
        SqlSession session = MyBatisUtil.getFactory().openSession();
        SinglePurchaseInfoMapper mapper = session.getMapper(SinglePurchaseInfoMapper.class);
        if (mapper.isInCart(s) > 0) {
            return true;
        } else
            return false;
    }
}
