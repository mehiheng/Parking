package com.thoughtworks.tdd;

import java.util.*;

public class ParkingBoy {
    public List<ParkingLot> list = new ArrayList();
    Map<String, String> cardList = new HashMap<>();

    public Receipt parkcar(Car theCar) {
        for (ParkingLot a : list) {
            if (a.size == 0 && a != list.get(list.size() - 1)) {
                continue;
            } else if (a.size == 0) {
                throw new ParkingLotFullException();
            }
            a.size = a.size - 1;
            break;
        }
        Receipt receipt = new Receipt();
        cardList.put(receipt.id, theCar.carId);
        return receipt;
    }

    public String parkcar(String carId, Receipt receipt) {
        String tempid = null;
        String tempcar = null;
        for (ParkingLot a : list) {
            if (a.size == 0 && a != list.get(list.size() - 1)) {
                continue;
            } else if (a.size <= 0) {
            }
            a.size = a.size - 1;
            a.cardList.put(receipt.id, carId);
            tempcar = carId;
            tempid = receipt.id;
            break;
        }
        cardList.put(tempid, tempcar);
        return tempid;
    }

    public void add(ParkingLot parkingLot) {
        list.add(parkingLot);
    }

    public String unPark(Receipt receipt) {
        for (ParkingLot a : list) {
            if (a.carList.containsKey(receipt)) {
                a.size = a.size + 1;
                return cardList.remove(receipt.id);
            }
        }
        return cardList.remove(receipt.id);
    }

    public String unpark(String id) {
        if (!cardList.containsKey(id)) {
            return null;
        }
        for (ParkingLot a : list) {
            if (a.cardList.containsKey(id)) {
                a.size += 1;
                String temp=a.cardList.get(id);
                cardList.remove(id);
                a.cardList.remove(id);
                return  temp;
            }
        }
        return null;
    }
}
