package org.nightschool.model;

import java.util.Date;

/**
 * Created by Administrator on 2015/2/28.
 */
public class SinglePurchaseInfo {
    private int buyerId;
    private int commodityId;
    private int num;
    private Date date;
    private String status;

    public SinglePurchaseInfo(int buyerId, int commodityId, int num, Date date, String status) {
        this.buyerId = buyerId;
        this.commodityId = commodityId;
        this.num = num;
        this.date = date;
        this.status = status;
    }
    public SinglePurchaseInfo(){

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public int getNum() {
        return num;
    }

    public Date getDate() {
        return date;
    }
}
