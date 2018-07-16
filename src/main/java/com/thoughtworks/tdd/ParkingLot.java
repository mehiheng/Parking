package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    public Map<String,String> cardList=new HashMap<>();
    public int size;
    public String name;
    public int originsize;
    public Map<Receipt,Car> carList=new HashMap<>();

    public ParkingLot(String n,int i,int r) {
        size=i;
        name=n;
        originsize=r;
    }

    public Receipt park(Car car) {
        if(size==0){
        throw new ParkingLotFullException();
        }
        size=size-1;
        Receipt receipt=new Receipt();
        carList.put(receipt,car);
        return receipt;

    }

    public Car unPark(Receipt receipt) {
        size=size+1;
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
