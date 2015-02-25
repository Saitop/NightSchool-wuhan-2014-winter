package org.nightschool.model;

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

    public Commodity() {
    }

    public Commodity(String name) {
        this.name = name;
    }
}
