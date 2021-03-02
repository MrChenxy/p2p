package com.bjpowernode.p2p.excep.loan;


import com.bjpowernode.p2p.excep.P2pCustomException;

public class CreateBidInfoException extends P2pCustomException {
    public CreateBidInfoException() {
        super();
    }

    public CreateBidInfoException(String message) {
        super(message);
    }
}
