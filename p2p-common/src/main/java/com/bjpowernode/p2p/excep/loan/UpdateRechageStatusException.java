package com.bjpowernode.p2p.excep.loan;


import com.bjpowernode.p2p.excep.P2pCustomException;

public class UpdateRechageStatusException extends P2pCustomException {
    public UpdateRechageStatusException() {
        super();
    }

    public UpdateRechageStatusException(String message) {
        super(message);
    }
}
