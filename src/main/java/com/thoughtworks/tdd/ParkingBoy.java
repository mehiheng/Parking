package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingBoy {



    public List<ParkingLot> list=new ArrayList();
    Map<Receipt,Car> carList=new HashMap<>();

    public Receipt parkcar(Car theCar) {
        for(ParkingLot a:list){
            if(a.size==0&&a!=list.get(list.size()-1)){
                    continue;
                }
            else if(a.size==0){
                    throw new ParkingLotFullException();
                }
            a.size=a.size-1;
            break;
        }
        Receipt receipt=new Receipt();
        carList.put(receipt,theCar);
        return receipt;
    }

    public void add(ParkingLot parkingLot) {
        list.add(parkingLot);
    }

    public Car unPark(Receipt receipt) {
        for(ParkingLot a:list){
            if(a.carList.containsKey(receipt)){
                a.size=a.size+1;
            }
        }
        return carList.remove(receipt);
    }
}
