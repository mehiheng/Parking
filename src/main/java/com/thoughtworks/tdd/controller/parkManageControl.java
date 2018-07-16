package com.thoughtworks.tdd.controller;

import com.thoughtworks.tdd.ParkingBoy;
import com.thoughtworks.tdd.ParkingLot;
import com.thoughtworks.tdd.view.parkview;

import java.io.IOException;
import java.util.List;

public class parkManageControl {
    public parkview view;
    public parkControl pc;
    public ParkingBoy parkingBoy=new ParkingBoy();


    public parkManageControl(parkview view, parkControl pc) {
        this.view = view;
        this.pc=pc;
    }
    
    public String begin() throws IOException {
        view.showManagerBegin();
        return view.commandInput();
    }


    public String manageJudge(String command) throws IOException {
        if(command.equals("1")){
            view.showAllview();
            view.showAll(pc.parkingBoy.list);
            view.showEnd(pc.parkingBoy.list);
           return null;
        }
        else if(command.equals("2")){
            view.insertView();
//            try {
                String[] a=view.getInsert();
                if(a.length>1){
                    ParkingLot parkingLot=new ParkingLot(a[0],Integer.parseInt(a[1]),Integer.parseInt(a[1]));
                    pc.parkingBoy.add(parkingLot);
                    view.showSucceccInsert();
                }else{
                   view.inputWrongNumber();
                }
//            }catch (Exception e){
//            }
            return null;
        }else if(command.equals("3")){
            view.getDeleteIdview();
            String id=view.getDeleteId();
            if (pc.parkingBoy.list.size()==0) {
                view.showWrongLot();
            } else if (pc.parkingBoy.list.size()<Integer.parseInt(id)) {
                view.showWrongLot();
            } else if (pc.parkingBoy.list.get(Integer.parseInt(id) - 1).originsize == pc.parkingBoy.list.get(Integer.parseInt(id) - 1).size && pc.parkingBoy.list.size() != 0) {
                pc.parkingBoy.list.remove(Integer.parseInt(id) - 1);
                view.showDeleteSuccess();
            } else if (pc.parkingBoy.list.get(Integer.parseInt(id) - 1).originsize != pc.parkingBoy.list.get(Integer.parseInt(id) - 1).size) {
                view.showNotEmpty();
            }
            return null;
        }else{
            String a=view.wrongInput();
            return a;
        }
    }

}
