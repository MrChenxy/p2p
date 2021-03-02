package com.bjpowernode.p2p.vo;

import java.math.BigDecimal;

public class InvestTop {
    private String phone;
    private BigDecimal money;

    public InvestTop() {
    }

    public InvestTop(String phone, BigDecimal money) {
        this.phone = phone;
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
