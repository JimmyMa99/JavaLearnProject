package com.itheima;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private Account loginAccount = null;
    private Scanner sc = new Scanner(System.in);
    /**启动ATM 展示欢迎界面*/
    public void start() {
        while (true) {
            System.out.println("==欢迎您进入ATM系统==");
            System.out.println("1.用户登录");
            System.out.println("2.用户开户");
            System.out.println("请选择");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    // 用户登录

                    System.out.println("用户登录");
                    login();
                    break;
                case 2:
//                    register();
                    System.out.println("用户开户");
                    createAccount();
                    break;
                default:
                    System.out.println("没有该操作！！！");
            }
        }
    }
    /** 完成用户开户操作*/
    private void createAccount(){
        //1.创建用户对象，用于封装开户信息
        Account acc = new Account();
        //2.需要用户输入自己的开户信息，赋值给用户对象
        System.out.println("请输入账户信息：");
        String name=sc.next();
        acc.setUsername(name);

        System.out.println("请输入性别：");

        while (true) {
            String sex=sc.next();
            switch (sex){
                case "男":
                    acc.setSex("男");
                    break;
                case "女":
                    acc.setSex("女");
                    break;
                default:
                    System.out.println("性别输入错误，只能输入男/女，重新输入");
                    continue;
            }
            break;
        }

        System.out.println("请输入密码：");
        String password=sc.next();
        System.out.println("请再次输入密码：");
        String password2=sc.next();
        while (true) {
            if (password.equals(password2)){
                acc.setPassword(password);
                break;
            }else {
                System.out.println("两次密码不一致，请重新输入");
                password=sc.next();
                System.out.println("请再次输入密码：");
                password2=sc.next();
            }
        }
        System.out.println("请输入取现额度");
        double limit=sc.nextDouble();
        acc.setLimit(limit);

        //3.将用户对象添加到集合中
        String cardID=getCardid();
        System.out.println("开户成功"+acc.getUsername()+"您的卡号为："+cardID);
        acc.setCardid(cardID);
        accounts.add(acc);

    }
    /**返回一个8位的随机数*/
    private String getCardid(){
        String cardid="";
        Random random=new Random();
        for (int i = 0; i < 8; i++) {
            int num=random.nextInt(10);
            cardid+=num;
        }
        //判断卡号是否已经存在
        if (getAcountByCardid(cardid)!=null){
            return getCardid();
        }

        return cardid;
    }
    private void login(){
        System.out.println("==用户登录==");
        if(accounts.size()==0){
            System.out.println("没有用户，请先开户");
            return;
        }
        System.out.println("请输入卡号：");
        String cardid=sc.next();
        Account acc=getAcountByCardid(cardid);
        if (acc==null){
            System.out.println("卡号不存在，请重新输入");
            login();
        }
        else {
            System.out.println("请输入密码：");
            String password = sc.next();
            while (true) {
                if (acc.getPassword().equals(password)) {
                    System.out.println("恭喜你"+acc.getUsername()+"登录成功");
                    loginAccount = acc;
                    showUserCommand();
                    break;

                } else {
                    System.out.println("密码错误，请重新输入");
                    password = sc.next();
                }
            }
            //进入用户操作界面
            System.out.println("登录成功");
            //展示登录界面
        }

    }

    private void showUserCommand(){
        System.out.println("==用户操作界面==");
        System.out.println(loginAccount.getUsername()+"您好，"+"您的卡号是"+loginAccount.getCardid()+"您可以选择以下操作：");
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("0.查询用户信息");
            System.out.println("1.查询余额");
            System.out.println("2.存款");
            System.out.println("3.取款");
            System.out.println("4.转账");
            System.out.println("5.修改密码");
            System.out.println("6.退卡");
            System.out.println("7.注销账号");
            System.out.println("请选择：");
            String command=sc.next();
            switch (command){
                case"0":
                    getLoginAccount();
                    break;
                case "1":
                    //查询余额
                    System.out.println("您的余额为："+loginAccount.getMoney());
                    break;
                case "2":
                    //存款
                    saveMoney();
                    break;
                case "3":
                    //取款
                    getMoney();
                    break;
                case "4":
                    //转账
                    trainer();
                    break;
                case "5":
                    //修改密码
                    changePassword();
                    break;
                case "6":
                    //退卡
                    System.out.println("退卡成功");
                    loginAccount=null;
                    return;
                case "7":
                    //注销账号
                    delectAccount();
                default:
                    System.out.println("没有该操作");
                    //
            }
        }

    }
    private void  getLoginAccount(){
        System.out.println("==用户信息==");
        System.out.println("卡号："+loginAccount.getCardid());
        System.out.println("姓名："+loginAccount.getUsername());
        System.out.println("性别"+loginAccount.getSex());
        System.out.println("余额"+loginAccount.getMoney());
        System.out.println("取款额度"+loginAccount.getLimit());
    }
    private void changePassword(){
        System.out.println("请输入原密码：");
        String password=sc.next();
        if (password.equals(loginAccount.getPassword())){
            System.out.println("请输入新密码：");
            String password2=sc.next();
            System.out.println("请再次输入新密码：");
            String password3=sc.next();
            if (password2.equals(password3)){
                loginAccount.setPassword(password2);
                System.out.println("密码修改成功");
            }else {
                System.out.println("两次密码不一致，修改失败");
            }
        }else {
            System.out.println("原密码错误，修改失败");
        }
    }
    private void delectAccount(){
        System.out.println("请输入密码：");
        String password4=sc.next();
        if (password4.equals(loginAccount.getPassword())){
            accounts.remove(loginAccount);
            System.out.println("注销成功");
            loginAccount=null;
            return;
        }else {
            System.out.println("密码错误，注销失败");
        }
    }
    private void trainer(){
        System.out.println("请输入对方卡号：");
        while (true) {
            String cardid=sc.next();
            Account acc=getAcountByCardid(cardid);
            if (acc==null){
                System.out.println("卡号不存在，请重新输入");
                continue;
            }
            System.out.println("请输入转账金额：");
            double money3=sc.nextDouble();
            if (money3>loginAccount.getMoney()){
                System.out.println("余额不足，转账失败");
                break;
            }else {
                loginAccount.setMoney(loginAccount.getMoney()-money3);
                acc.setMoney(acc.getMoney()+money3);
                System.out.println("转账成功，您的余额为："+loginAccount.getMoney());
                break;
            }
        }
    }
    private void saveMoney(){
        System.out.println("请输入存款金额：");
        double money=sc.nextDouble();
        loginAccount.setMoney(loginAccount.getMoney()+money);
        System.out.println("存款成功，您的余额为："+loginAccount.getMoney());
    }

    private void getMoney(){
        System.out.println("请输入取款金额：");
        double money2=sc.nextDouble();
        if (money2>loginAccount.getMoney()){
            System.out.println("余额不足，取款失败");
        }else {
            if (loginAccount.getLimit() < money2) {
                System.out.println("超过取款额度，取款失败");
                return;
            }
            loginAccount.setMoney(loginAccount.getMoney()-money2);
            System.out.println("取款成功，您的余额为："+loginAccount.getMoney());
        }
    }
    private Account getAcountByCardid(String cardid){
        for(int i = 0; i<accounts.size(); i++){
            Account acc=accounts.get(i);
            if (acc.getCardid().equals(cardid)){
                return acc;
            }
        }
        return null;//如果没有找到，返回null
    }
}
