package com.pa.devbox.domain.entity.rest;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 12/5/16 22:07.
 * Email: whailong2010@gmail.com
 */
@JsonObject
public class Inner {

    private String __op = "Increment";

    private int amount = 1;

    public String get__op() {
        return __op;
    }

    public void set__op(String __op) {
        this.__op = __op;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
