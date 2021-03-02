package com.bjpowernode.p2p.excep.loan;


import com.bjpowernode.p2p.excep.P2pCustomException;

public class UpdateIncomeStatusException extends P2pCustomException {
    public UpdateIncomeStatusException() {
        super();
    }

    public UpdateIncomeStatusException(String message) {
        super(message);
    }
}
