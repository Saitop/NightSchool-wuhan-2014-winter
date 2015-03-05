package org.nightschool.model;

import java.util.Date;

/**
 * Created by Administrator on 2015/2/28.
 */
public class SinglePurchaseInfo {
    private int buyerId;
    private int commodityId;
    private int id;
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

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
