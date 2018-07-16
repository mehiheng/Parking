package com.thoughtworks.tdd.controller;

import com.thoughtworks.tdd.ParkingBoy;
import com.thoughtworks.tdd.ParkingLot;
import com.thoughtworks.tdd.Receipt;
import com.thoughtworks.tdd.view.parkview;

import java.io.IOException;

public class parkControl {
    public parkview view;
    public ParkingBoy parkingBoy=new ParkingBoy();


    public parkControl(parkview view) {
        this.view = view;
    }

//    public void setParkingBoy(){
//        ParkingLot lot1=new ParkingLot(1);
//        ParkingLot lot2=new ParkingLot(1);
//        parkingBoy.add(lot1);
//        parkingBoy.add(lot2);
//    }

    public String begin() throws IOException {
        view.showParkBegin();
        return view.commandInput();
    }


    public String judge(String command) throws IOException {
        if(command.equals("1")){
            if(isFull()){
                return view.fullView();
            }else{
                view.getCarIdview();
                String carId = view.getCarId();
                Receipt receipt = new Receipt();
                String a=parkingBoy.parkcar(carId,receipt);
                return view.outputParksuccessview(a);
            }
        }
        else if(command.equals("2")){
            view.unParkGetIdview();
            String id = view.unParkGetId();
            String a=parkingBoy.unpark(id);
            return  view.unparkview(a);
        }else{
            String a=view.wrongInput();
            return a;
        }
    }

    public Boolean isFull(){
        if(parkingBoy.list.size()==0){
                    return true;
                }
        for (ParkingLot a : parkingBoy.list) {
            if (a.size == 0 && a != parkingBoy.list.get(parkingBoy.list.size() - 1)) {
                continue;
            } else if (a.size == 0) {
                return true;
            }
        }
        return false;
    }

}
