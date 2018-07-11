package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class ParkingLot {
    int size;
    private Map<Receipt,Car> carList=new HashMap<>();

    public ParkingLot(int i) {
        size=i;
    }

    public Receipt park(Car car) {
        if(size==0){
        throw new ParkingLotFullException();
        }
        Receipt receipt=new Receipt();
        carList.put(receipt,car);
        return receipt;

    }

    public Car unPark(Receipt receipt) {
        return carList.remove(receipt);
    }

    public boolean isFull() {
        if(size==0){
            return true;
        }else{
            return  false;
        }
    }
}
