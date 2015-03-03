package org.nightschool.controller;

import org.nightschool.mapper.CartMapper;
import org.nightschool.model.SinglePurchaseInfo;
import org.nightschool.mybatis.MyBatisUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/2/28.
 */
@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
public class CartController {
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SinglePurchaseInfo> getCart() throws IOException {
        CartMapper mapper = MyBatisUtil.getFactory().openSession().getMapper(CartMapper.class);
        List<SinglePurchaseInfo> inCart = mapper.getCart("inCart", 1);
        System.out.println("*********"+inCart.get(0).getDate());
        return inCart;
    }
}
