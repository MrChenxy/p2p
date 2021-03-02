package com.bjpowernode.p2p.excep.loan;


import com.bjpowernode.p2p.excep.P2pCustomException;

public class UpdateFinaceAccountMoneyException extends P2pCustomException {
    public UpdateFinaceAccountMoneyException() {
        super();
    }

    public UpdateFinaceAccountMoneyException(String message) {
        super(message);
    }
}
