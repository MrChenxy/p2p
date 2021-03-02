package com.bjpowernode.p2p.excep.loan;


import com.bjpowernode.p2p.excep.P2pCustomException;

public class UpdateLeftProductMoneyException extends P2pCustomException {
    public UpdateLeftProductMoneyException() {
        super();
    }

    public UpdateLeftProductMoneyException(String message) {
        super(message);
    }
}
