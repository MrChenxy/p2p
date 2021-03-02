package com.bjpowernode.p2p.excep.user;


import com.bjpowernode.p2p.excep.P2pCustomException;

public class CreateFinanceAccountException extends P2pCustomException {
    public CreateFinanceAccountException() {
        super();
    }

    public CreateFinanceAccountException(String message) {
        super(message);
    }
}
