package com.bjpowernode.p2p.po.ext;

import com.bjpowernode.p2p.po.loan.BidInfo;
import java.io.Serializable;

public class UserBidInfo extends BidInfo implements Serializable {

    private static final long serialVersionUID = 1705444400232812642L;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
