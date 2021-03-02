package com.bjpowernode.p2p.po.ext;

import com.bjpowernode.p2p.po.loan.BidInfo;
import java.io.Serializable;

public class LoanBidInfo extends BidInfo implements Serializable {

    private static final long serialVersionUID = 4084408183250502740L;

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
