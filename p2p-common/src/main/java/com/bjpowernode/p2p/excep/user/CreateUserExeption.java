package com.bjpowernode.p2p.excep.user;


import com.bjpowernode.p2p.excep.P2pCustomException;

public class CreateUserExeption extends P2pCustomException {
    public CreateUserExeption() {
        super();
    }

    public CreateUserExeption(String message) {
        super(message);
    }
}
