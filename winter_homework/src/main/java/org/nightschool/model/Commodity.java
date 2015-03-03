package org.nightschool.model;

import java.util.Date;

/**
 * Created by Administrator on 2015/2/19.
 */

public class Commodity {
    private String name;
    private String comDesc;
    private String imgUrl;
    private double oldPrice;
    private double newPrice;

    private int stock;
    private int salesVolume;
    private int ownerId;
    private Date publishDate;

    public Commodity() {
    }

    public Commodity(String name, String comDesc, String imgUrl, double oldPrice, double newPrice, int stock, int salesVolume, int ownerId) {
        this.name = name;
        this.comDesc = comDesc;
        this.imgUrl = imgUrl;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.stock = stock;
        this.salesVolume = salesVolume;
        this.ownerId = ownerId;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setComDesc(String comDesc) {
        this.comDesc = comDesc;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
