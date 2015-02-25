package org.nightschool.model;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/2/21.
 */
public class Order {
    private int userId;
    private HashMap<Commodity,Integer> detail;
    private int orderStatus;
}
