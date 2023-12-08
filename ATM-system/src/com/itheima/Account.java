package com.itheima;

public class Account {
    public String getCardid() {
        return cardid;
    }

    public String getUsername() {
        return username+(sex=="男"?"先生":"女士");
    }

    public String getSex() {
        return sex;
    }

    public String getPassword() {
        return password;
    }

    public double getMoney() {
        return money;
    }

    public double getLimit() {
        return limit;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }


    private String sex;
    private String password;
    private double money;
    private double limit;
    private String cardid;
    private String username;

}
