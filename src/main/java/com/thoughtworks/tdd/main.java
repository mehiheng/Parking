package com.thoughtworks.tdd;

import com.thoughtworks.tdd.controller.parkControl;
import com.thoughtworks.tdd.view.parkview;

public class main {
    public static void main(String[] args) throws Exception {
        parkview view=new parkview();
        parkControl parkControl1=new parkControl(view);
        parkControl1.setParkingBoy();
        parkControl1.begin();
    }
}
