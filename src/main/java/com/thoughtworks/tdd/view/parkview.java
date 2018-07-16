package com.thoughtworks.tdd.view;

import com.thoughtworks.tdd.ParkingLot;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class parkview {

    public void showOriginBegin() throws IOException {
        System.out.println("1.停车服务\n" +
                "2.停车场管理\n" +
                "请输入您要进入的页面：");
    }

    public void showManagerBegin() throws IOException {
        System.out.println("1.查看停车场详情\n" +
                "2.添加停车场\n" +
                "3.删除停车场");
    }

    public void showParkBegin() throws IOException {
        System.out.println("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
    }

    public String commandInput(){
        Scanner in = new Scanner(System.in);
        return (in.next());
    }

    public void unParkGetIdview(){
        System.out.println("请输入小票编号：");
    }

    public String unParkGetId() {
        Scanner in = new Scanner(System.in);
        return in.next();
    }

    public String getCarId() {
        Scanner in = new Scanner(System.in);
        return in.next();
    }

    public void getCarIdview(){
        System.out.println("请输入车牌号:");
    }

    public String outputParksuccessview(String a){
        System.out.println("停车成功，您的小票是：\n" +a);
        return "停车成功，您的小票是：\n" +a;
    }

    public String unparkview(String a){
        if (a == null) {
            System.out.println("非法小票，无法取出车，请查证后再输");
            return "非法小票，无法取出车，请查证后再输";
        }else {
            System.out.println("车已取出，您的车牌号是: " +a);
            return "车已取出，您的车牌号是: " +a;
        }
    }

    public String wrongInput(){
        System.out.println("非法指令，请查证后再输");
        return "非法指令，请查证后再输";
    }

    public String fullView(){
        System.out.println("车已停满，请晚点再来");
        return "车已停满，请晚点再来";
    }

    public void insertView(){
        System.out.println("请输入你套添加的停车场信息（格式为：名称，车位）：");
    }

    public String[] getInsert () throws IOException {
        Scanner in = new Scanner(System.in);
//        if(in.next().contains(","))
//        {
            String[] list=(in.next().split(","));
            return  list;
//        else{
//            System.out.println("添加停车场格式错误");
//            throw new IOException();
//        }
    }

    public void showSucceccInsert() {
        System.out.println("停车场添加成功！");
    }

    public void getDeleteIdview() {
        System.out.println("请输入需要删除的被管理停车场ID:");
    }

    public String getDeleteId() {
        Scanner in = new Scanner(System.in);
        return in.next();
    }

    public void showAllview() {
        System.out.println("|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================");
    }

    public void showAll(List<ParkingLot> list) {
        for(ParkingLot p:list){
            System.out.println("|"+(list.indexOf(p)+1)+"|"+p.name+"|"+p.originsize+"(个)|"+(p.originsize-p.size)+"(俩)|"+p.size+"个|");
        }
    }

    public void showEnd(List<ParkingLot> list) {
        int all=0;
        int allpark=0;
        int allrest=0;
        for(ParkingLot p:list){
            all+=p.originsize;
            allpark+=(p.originsize-p.size);
            allrest+=p.size;
        }
        System.out.println("总车位："+all+"(个)\n" +
                "停车总量："+allpark+"（辆）\n" +
                "总剩余车位："+allrest+"（个）");
    }

    public void showDeleteSuccess() {
        System.out.println("停车场删除成功！");
    }

    public void showNotEmpty() {
        System.out.println("此停车场中，依然停有汽车，无法删除！");
    }

    public void showWrongLot() {
        System.out.println("此停车场不存在！");
    }

    public void inputWrongNumber() {
        System.out.println("添加停车场格式错误");
    }
}