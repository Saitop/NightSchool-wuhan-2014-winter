package org.nightschool.model;

import java.util.Date;

/**
 * Created by Administrator on 2015/2/21.
 */
public class Order {
    private int buyerId;
    private int sellerId;
    private Date date;
    private String status;

    public Order(int buyerId, int sellerId, String status) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.status = status;
    }

    public Order(){}

}
