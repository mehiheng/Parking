package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;


public class ParkingLotTest {

    @Test
    public void should_park_successfully_given_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);

        try {
            parkingLot.park(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }


    @Test
    public void should_park_failed_given_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);

        try {
            parkingLot.park(new Car());
            fail("should park successfully");
        } catch (ParkingLotFullException exception) {

        }
    }


    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);

        assertThat(parkingLot.unPark(receipt), is(theCar));

    }

    @Test
    public void should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
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

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_park_successfullly_when_call_park_again_given_a_full_parking_lot_take_out_a_car(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        try {
            parkingLot.park(new Car());
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
        Car theCar = new Car();
        Car theCar1 = new Car();
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
        Car theCar = new Car();
        Car theCar1 = new Car();
        Car theCar2 = new Car();
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
        Car theCar = new Car();
        Receipt receipt = ParkingBoy.parkcar(theCar);

        assertThat(ParkingBoy.unPark(receipt), is(theCar));

    }

    @Test
    public void should_parkingboy_get_two_specific_car_when_call_unPark_given_two_receipt_is_right(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot2= new ParkingLot(1);
        ParkingBoy ParkingBoy=new ParkingBoy();
        ParkingBoy.add(parkingLot);
        ParkingBoy.add(parkingLot2);
        Car theCar = new Car();
        Car theCar2 = new Car();
        Receipt receipt = ParkingBoy.parkcar(theCar);
        Receipt receipt2 = ParkingBoy.parkcar(theCar2);
        assertThat(ParkingBoy.unPark(receipt), is(theCar));
        assertThat(ParkingBoy.unPark(receipt2), is(theCar2));
    }

    @Test
    public void park_in_turns(){
        ParkingBoy ParkingBoy=new ParkingBoy();
        ParkingLot ParkingLot = new ParkingLot(1);
        ParkingLot ParkingLot2 = new ParkingLot(1);
        ParkingBoy.add(ParkingLot);
        ParkingBoy.add(ParkingLot2);
        Car theCar = new Car();
        ParkingBoy.parkcar(theCar);
        assertThat(ParkingLot.size, is(0));

    }
    @Test
    public void parkboy_should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy ParkingBoy=new ParkingBoy();
        ParkingBoy.add(parkingLot);
        Car theCar = new Car();
        Receipt receipt = ParkingBoy.parkcar(theCar);

        Receipt anotherReceipt = new Receipt();

        assertThat(parkingLot.unPark(anotherReceipt), not(theCar));
    }
}
