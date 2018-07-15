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
//                System.out.println("车已停满，请晚点再来");
                return "车已停满，请晚点再来";
//                break;
            }
            a.size = a.size - 1;
//          String carId = getCarId();
//          Receipt receipt = new Receipt();
            a.cardList.put(receipt.id, carId);
            tempcar = carId;
            tempid = receipt.id;
            System.out.println("停车成功，您的小票是：\n" +
                    tempid);
            break;
        }
        cardList.put(tempid, tempcar);
        return  "停车成功，您的小票是：\n" +
                tempid;
    }

//    public String getCarId() {
//        System.out.println("请输入车牌号:");
//        Scanner in = new Scanner(System.in);
//        return in.next();
//    }

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
            System.out.println("非法小票，无法取出车，请查证后再输");
            return "非法小票，无法取出车，请查证后再输";
        }
        for (ParkingLot a : list) {
            if (a.cardList.containsKey(id)) {
                a.size += 1;
                System.out.println("车已取出，您的车牌号是: " + a.cardList.get(id));
                String temp=a.cardList.get(id);
                cardList.remove(id);
                a.cardList.remove(id);
                return "车已取出，您的车牌号是: " + temp;
//                break;
            }
        }
        return null;
    }
}
