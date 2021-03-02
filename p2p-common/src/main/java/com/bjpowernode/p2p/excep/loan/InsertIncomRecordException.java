package com.bjpowernode.p2p.excep.loan;


import com.bjpowernode.p2p.excep.P2pCustomException;

public class InsertIncomRecordException extends P2pCustomException {
    public InsertIncomRecordException() {
        super();
    }

    public InsertIncomRecordException(String message) {
        super(message);
    }
}
