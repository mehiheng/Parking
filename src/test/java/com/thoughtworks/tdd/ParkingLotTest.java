package com.thoughtworks.tdd;

import com.sun.xml.internal.fastinfoset.tools.TransformInputOutput;
import com.thoughtworks.tdd.controller.parkControl;
import com.thoughtworks.tdd.view.parkview;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class ParkingLotTest {

    @Test
    public void should_park_successfully_given_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);

        try {
            parkingLot.park(new Car("A0001"));
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }


    @Test
    public void should_park_failed_given_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);

        try {
            parkingLot.park(new Car("A0001"));
            fail("should park successfully");
        } catch (ParkingLotFullException exception) {

        }
    }


    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car("A0001");
        Receipt receipt = parkingLot.park(theCar);

        assertThat(parkingLot.unPark(receipt), is(theCar));

    }

    @Test
    public void should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car("A0001");
        Receipt receipt = parkingLot.park(theCar);

        Receipt anotherReceipt = new Receipt();

        assertThat(parkingLot.unPark(anotherReceipt), not(theCar));
    }


    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);

        assertThat(parkingLot.isFull(), is(true));
    }

    @Test
    public void should_be_false_when_call_isFull_given_parking_lot_is_not_full(){
        ParkingLot parkingLot = new ParkingLot(1);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car("A0001");
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_park_successfullly_when_call_park_again_given_a_full_parking_lot_take_out_a_car(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car("A0001");
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        try {
            parkingLot.park(new Car("A0001"));
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }



    @Test
    public void should_park_car_to_next_park_when_park_is_full(){
        ParkingBoy ParkingLotBoy2=new ParkingBoy();
        ParkingLot ParkingLot = new ParkingLot(1);
        ParkingLot ParkingLot2 = new ParkingLot(1);
        ParkingLotBoy2.add(ParkingLot);
        ParkingLotBoy2.add(ParkingLot2);
        Car theCar = new Car("A0001");
        Car theCar1 = new Car("B0001");
        try {
            ParkingLotBoy2.parkcar(theCar);
            ParkingLotBoy2.parkcar(theCar1);
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }

    @Test
    public void should_not_park_when_all_full(){
        ParkingBoy ParkingBoy=new ParkingBoy();
        ParkingLot ParkingLot = new ParkingLot(1);
        ParkingLot ParkingLot2 = new ParkingLot(1);
        ParkingBoy.add(ParkingLot);
        ParkingBoy.add(ParkingLot2);
        Car theCar = new Car("A0001");
        Car theCar1 = new Car("B0001");
        Car theCar2 = new Car("C0001");
        try {
            ParkingBoy.parkcar(theCar);
            ParkingBoy.parkcar(theCar1);
            ParkingBoy.parkcar(theCar2);
            fail("should park successfully");
        } catch (ParkingLotFullException exception) {

        }
    }

    @Test
    public void should_parkingboy_get_specific_car_when_call_unPark_given_receipt_is_right(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy ParkingBoy=new ParkingBoy();
        ParkingBoy.add(parkingLot);
        Car theCar = new Car("A0001");
        Receipt receipt = ParkingBoy.parkcar(theCar);

        assertThat(ParkingBoy.unPark(receipt), is(theCar.carId));

    }

    @Test
    public void should_parkingboy_get_two_specific_car_when_call_unPark_given_two_receipt_is_right(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot2= new ParkingLot(1);
        ParkingBoy ParkingBoy=new ParkingBoy();
        ParkingBoy.add(parkingLot);
        ParkingBoy.add(parkingLot2);
        Car theCar = new Car("A0001");
        Car theCar2 = new Car("A0001");
        Receipt receipt = ParkingBoy.parkcar(theCar);
        Receipt receipt2 = ParkingBoy.parkcar(theCar2);
        assertThat(ParkingBoy.unPark(receipt), is(theCar.carId));
        assertThat(ParkingBoy.unPark(receipt2), is(theCar2.carId));
    }

    @Test
    public void park_in_turns(){
        ParkingBoy ParkingBoy=new ParkingBoy();
        ParkingLot ParkingLot = new ParkingLot(1);
        ParkingLot ParkingLot2 = new ParkingLot(1);
        ParkingBoy.add(ParkingLot);
        ParkingBoy.add(ParkingLot2);
        Car theCar = new Car("A0001");
        ParkingBoy.parkcar(theCar);
        assertThat(ParkingLot.size, is(0));

    }
    @Test
    public void parkboy_should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy ParkingBoy=new ParkingBoy();
        ParkingBoy.add(parkingLot);
        Car theCar = new Car("A0001");
        Receipt receipt = ParkingBoy.parkcar(theCar);

        Receipt anotherReceipt = new Receipt();

        assertThat(parkingLot.unPark(anotherReceipt), not(theCar.carId));
    }

//    @Test
//    public void command_3_park() throws IOException {
//        parkview view = mock(parkview.class);
//        parkControl pc=new parkControl(view);
//        pc.setParkingBoy();
//        Mockito.when(view.commandInput()).thenReturn("10");
//        Mockito.when(view.wrongInput()).thenReturn("非法指令，请查证后再输");
//        assertThat(pc.judge(view.commandInput()), is("非法指令，请查证后再输"));
//    }
//
//    @Test
//    public void command_1_park() throws IOException {
//        parkview view = mock(parkview.class);
//        parkControl pc=new parkControl(view);
//        pc.setParkingBoy();
//        Mockito.when(view.commandInput()).thenReturn("1");
//        Mockito.when(view.getCarIdview()).thenReturn("A1");
//        Mockito.when(view.outputParksuccessview(anyString())).thenReturn("停车成功，您的小票是：\n" + "1");
//        assertThat(pc.judge(view.commandInput()), is("停车成功，您的小票是：\n" +
//                "1"));
//    }
//pc.parkingBoy.cardList.keySet().iterator().next()
//    @Test
//    public void command_2_unpark() throws IOException {
//        parkview view = mock(parkview.class);
//        parkControl pc=new parkControl(view);
//        pc.setParkingBoy();
//        Mockito.when(view.commandInput()).thenReturn("1");
//        Mockito.when(view.getCarIdview()).thenReturn("A1");
//        pc.judge(view.commandInput());
//        Mockito.when(view.commandInput()).thenReturn("2");
//        Mockito.when(view.unParkGetIdview()).thenReturn( pc.parkingBoy.cardList.keySet().iterator().next());
//        assertThat(pc.judge(view.commandInput()), is("车已取出，您的车牌号是: A1" ));
//    }
//
//
//    @Test
//    public void command_2_wrong_input_unpark() throws IOException {
//        parkview view = mock(parkview.class);
//        parkControl pc=new parkControl(view);
//        pc.setParkingBoy();
////        Mockito.when(view.showBegin()).thenReturn(1);
//        Receipt receipt=new Receipt();
//        Receipt receipt2=new Receipt();
//        pc.parkingBoy.parkcar(view.getCarIdview(),receipt);
//        Mockito.when(view.unParkGetIdview()).thenReturn(receipt2.id);
//        assertThat(pc.parkingBoy.unPark(view.unParkGetIdview()), is("非法小票，无法取出车，请查证后再输" ));
//    }
//
//    @Test
//    public void full_test() throws IOException {
//        parkview view = mock(parkview.class);
//        parkControl pc=new parkControl(view);
//        pc.setParkingBoy();
////        Mockito.when(view.showBegin()).thenReturn(1);
//        Mockito.when(view.getCarIdview()).thenReturn("A1");
//        Receipt receipt=new Receipt();
//        pc.park(view.getCarIdview(),receipt);
//        Mockito.when(view.getCarIdview()).thenReturn("A2");
//        Receipt receipt1=new Receipt();
//        pc.park(view.getCarIdview(),receipt1);
//        Mockito.when(view.getCarIdview()).thenReturn("A3");
//        Receipt receipt2=new Receipt();
//        assertThat((pc.park(view.getCarIdview(),receipt2)), is("车已停满，请晚点再来"));
//    }
//
//    @Test
//    public void full_off_a_car_park_again_test() throws IOException {
//        parkview view = mock(parkview.class);
//        parkControl pc=new parkControl(view);
//        pc.setParkingBoy();
////        Mockito.when(view.showBegin()).thenReturn(1);
//        Mockito.when(view.getCarIdview()).thenReturn("A1");
//        Receipt receipt=new Receipt();
//        pc.park(view.getCarIdview(),receipt);
//        Mockito.when(view.getCarIdview()).thenReturn("A2");
//        Mockito.when(view.unParkGetIdview()).thenReturn(receipt.id);
//        pc.unPark(view.unParkGetIdview());
//        Receipt receipt1=new Receipt();
//        pc.park(view.getCarIdview(),receipt1);
//        Mockito.when(view.getCarIdview()).thenReturn("A3");
//        Receipt receipt2=new Receipt();
//        assertThat((pc.park(view.getCarIdview(),receipt2)),is("停车成功，您的小票是：\n" +
//                receipt2.id));
//    }
//




}


