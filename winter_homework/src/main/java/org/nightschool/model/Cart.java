package org.nightschool.model;

import java.util.List;

/**
 * Created by Administrator on 2015/2/19.
 */
public class Cart {
    private int buyerId;
    private List<List<SinglePurchaseInfo>> list;

    public List<List<SinglePurchaseInfo>> getList() {
        return list;
    }

}
