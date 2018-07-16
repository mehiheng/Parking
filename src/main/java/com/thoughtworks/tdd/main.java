package com.thoughtworks.tdd;

import com.thoughtworks.tdd.controller.parkControl;
import com.thoughtworks.tdd.controller.parkManageControl;
import com.thoughtworks.tdd.controller.parkServiceControl;
import com.thoughtworks.tdd.view.parkview;

public class main {
    public static void main(String[] args) throws Exception {
        parkview view=new parkview();
        parkControl pc=new parkControl(view);
        parkManageControl pmc=new parkManageControl(view,pc);
//        pc.setParkingBoy();
        parkServiceControl psc=new parkServiceControl(view,pc,pmc);
        while(true){
            String a=psc.begin();
            psc.originJudge(a);
        }
    }
}
