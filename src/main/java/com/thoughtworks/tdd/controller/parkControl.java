package com.thoughtworks.tdd.controller;

import com.thoughtworks.tdd.Car;
import com.thoughtworks.tdd.ParkingBoy;
import com.thoughtworks.tdd.ParkingLot;
import com.thoughtworks.tdd.view.parkview;

import java.io.IOException;
import java.util.Scanner;

public class parkControl {
    parkview view;
    ParkingBoy parkingBoy=new ParkingBoy();
    int command;

    public parkControl(parkview view) {
        this.view = view;
    }

    public void setParkingBoy(){
        ParkingLot lot1=new ParkingLot(2);
        ParkingLot lot2=new ParkingLot(3);
        parkingBoy.add(lot1);
        parkingBoy.add(lot2);
    }

    public void begin() throws IOException {
         command=view.showBegin();
         judge();
    }


    public void judge() throws IOException {
        if(command==1){
            parkingBoy.parkcar();
            begin();
        }
        else if(command==2){
           parkingBoy.unpark();
           begin();
        }else{
            System.out.println("非法指令，请查证后再输");
            begin();
        }
    }

}
