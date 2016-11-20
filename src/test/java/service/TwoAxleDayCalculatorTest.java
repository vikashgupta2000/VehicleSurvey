package service;

import constants.Direction;
import junit.framework.Assert;
import org.junit.Test;
import service.serviceImpl.TwoAxleDayCalculator;

public class TwoAxleDayCalculatorTest {

    @Test
    public void shouldReturnSameDayNumberIfFirstAxleTimeIsGreaterThanSecondAxleTimeOfPreviousVehicle() {
        TwoAxleDayCalculator twoAxleDayCalculator = new TwoAxleDayCalculator();
        int firstDayFirstVehicleFirstAxleDayCount = twoAxleDayCalculator.calculateDayNumber(Direction.NORTH, 1000l);
        int firstDayFirstVehicleSecondAxleDayCount = twoAxleDayCalculator.calculateDayNumber(Direction.NORTH, 1020l);
        Assert.assertEquals(firstDayFirstVehicleFirstAxleDayCount, firstDayFirstVehicleSecondAxleDayCount);
        Assert.assertEquals(0, firstDayFirstVehicleFirstAxleDayCount);

        int firstDaySecondVehicleFirstAxleDayCount = twoAxleDayCalculator.calculateDayNumber(Direction.NORTH, 1030l);
        int firstDaySecondVehicleSecondAxleDayCount = twoAxleDayCalculator.calculateDayNumber(Direction.NORTH, 1040l);
        Assert.assertEquals(firstDaySecondVehicleFirstAxleDayCount, firstDaySecondVehicleSecondAxleDayCount);
        Assert.assertEquals(0, firstDaySecondVehicleFirstAxleDayCount);
    }

    @Test
    public void shouldReturnDifferentDayNumberIfFirstAxleTimeIsSmallerThanSecondAxleTimeOfPreviousVehicle() {
        TwoAxleDayCalculator twoAxleDayCalculator = new TwoAxleDayCalculator();
        int firstDayFirstVehicleFirstAxleDayCount = twoAxleDayCalculator.calculateDayNumber(Direction.NORTH, 1000l);
        int firstDayFirstVehicleSecondAxleDayCount = twoAxleDayCalculator.calculateDayNumber(Direction.NORTH, 1020l);
        Assert.assertEquals(firstDayFirstVehicleFirstAxleDayCount, firstDayFirstVehicleSecondAxleDayCount);
        Assert.assertEquals(0, firstDayFirstVehicleFirstAxleDayCount);

        int secondDaySecondVehicleFirstAxleDayCount = twoAxleDayCalculator.calculateDayNumber(Direction.NORTH, 100l);
        int secondDaySecondVehicleSecondAxleDayCount = twoAxleDayCalculator.calculateDayNumber(Direction.NORTH, 110l);
        Assert.assertEquals(secondDaySecondVehicleFirstAxleDayCount, secondDaySecondVehicleSecondAxleDayCount);
        Assert.assertEquals(1, secondDaySecondVehicleFirstAxleDayCount);
    }
}
