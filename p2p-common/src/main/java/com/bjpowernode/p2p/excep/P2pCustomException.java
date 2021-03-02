package com.bjpowernode.p2p.excep;

// 自定义异常父类
public class P2pCustomException extends RuntimeException {
    public P2pCustomException() {
    }

    public P2pCustomException(String message) {
        super(message);
    }
}
