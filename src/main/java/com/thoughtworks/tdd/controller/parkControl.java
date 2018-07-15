package com.thoughtworks.tdd.controller;

import com.thoughtworks.tdd.ParkingBoy;
import com.thoughtworks.tdd.ParkingLot;
import com.thoughtworks.tdd.Receipt;
import com.thoughtworks.tdd.view.parkview;

import java.io.IOException;

public class parkControl {
    parkview view;
    ParkingBoy parkingBoy=new ParkingBoy();


    public parkControl(parkview view) {
        this.view = view;
    }

    public void setParkingBoy(){
        ParkingLot lot1=new ParkingLot(1);
        ParkingLot lot2=new ParkingLot(1);
        parkingBoy.add(lot1);
        parkingBoy.add(lot2);
    }

    public void begin() throws IOException {
         int command=view.showBegin();
         judge(command);
    }


    public String judge(int command) throws IOException {
        if(command==1){
            if(isFull()){
                begin();
            }else{
                String carId = view.getCarIdview();
                Receipt receipt = new Receipt();
                park(carId,receipt);
                begin();
            }

        }
        else if(command==2){
            String id = view.unParkGetIdview();
            unPark(id);
            begin();
        }else{
            canNotPark();
            begin();
        }
        return null;
    }

    private String canNotPark() {
        System.out.println("非法指令，请查证后再输");
        return "非法指令，请查证后再输";
    }

    public String unPark(String id) throws IOException {
        String a=parkingBoy.unpark(id);
        return a;
    }

    public String park(String carId, Receipt receipt) throws IOException {
        String a=parkingBoy.parkcar(carId,receipt);
        return a;
    }

    public Boolean isFull(){
        for (ParkingLot a : parkingBoy.list) {
            if (a.size == 0 && a != parkingBoy.list.get(parkingBoy.list.size() - 1)) {
                continue;
            } else if (a.size <= 0) {
                System.out.println("车已停满，请晚点再来");
                return true;
            }
        }
        return false;
    }

}
