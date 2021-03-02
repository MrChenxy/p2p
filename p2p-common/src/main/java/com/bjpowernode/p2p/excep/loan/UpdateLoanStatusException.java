package com.bjpowernode.p2p.excep.loan;


import com.bjpowernode.p2p.excep.P2pCustomException;

public class UpdateLoanStatusException extends P2pCustomException {
    public UpdateLoanStatusException() {
        super();
    }

    public UpdateLoanStatusException(String message) {
        super(message);
    }
}
