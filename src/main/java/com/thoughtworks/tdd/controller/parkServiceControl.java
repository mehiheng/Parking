package com.thoughtworks.tdd.controller;

import com.thoughtworks.tdd.ParkingBoy;
import com.thoughtworks.tdd.ParkingLot;
import com.thoughtworks.tdd.Receipt;
import com.thoughtworks.tdd.view.parkview;

import java.io.IOException;

public class parkServiceControl {
    public parkview view;
    public parkControl pc;
    public parkManageControl pmc;
    public ParkingBoy parkingBoy=new ParkingBoy();


    public parkServiceControl(parkview view,parkControl pc,parkManageControl pmc) {
        this.view = view;
        this.pc=pc;
        this.pmc=pmc;
    }

    public String begin() throws IOException {
        view.showOriginBegin();
        return view.commandInput();
    }


    public String originJudge(String command) throws IOException {
        if(command.equals("1")){
            String a=pc.begin();
           pc.judge(a);
           return null;
        }
        else if(command.equals("2")){
            String a=pmc.begin();
            pmc.manageJudge(a);
            return null;
        }else{
            String a=view.wrongInput();
            return a;
        }
    }

}
