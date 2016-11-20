package service;

import constants.Direction;
import model.VehicleData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.serviceImpl.TwoAxleDayCalculator;
import service.serviceImpl.TwoAxleVehicleMovementService;
import singleton.VehicleStorage;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TwoAxleVehicleMovementServiceTest {

    @Before
    public void initialize() {
        VehicleStorage.getInstance().setDayCalculator(new TwoAxleDayCalculator());
    }

    @Test
    public void shouldAddFirstAndSecondAxleDataInTheSameVehicleDataObject() {
        VehicleMovementService vehicleMovementService = new TwoAxleVehicleMovementService();
        vehicleMovementService.addVehicle(Direction.NORTH, 1000l);
        vehicleMovementService.addVehicle(Direction.NORTH, 1020l);

        Map<Integer, Map<Direction, List<VehicleData>>> vehicleDataMap = VehicleStorage.getInstance().getVehicleData();

        Assert.assertNotNull(vehicleDataMap);
        Assert.assertEquals(1, vehicleDataMap.size());
        Assert.assertEquals(1, vehicleDataMap.get(0).size());
        Assert.assertEquals(1, vehicleDataMap.get(0).get(Direction.NORTH).size());

    }

    @Test
    public void shouldAddFirstAndSecondVehicleDataInTheDifferentVehicleDataObject() {
        VehicleMovementService vehicleMovementService = new TwoAxleVehicleMovementService();
        //First Vehicle data
        vehicleMovementService.addVehicle(Direction.NORTH, 1000l);
        vehicleMovementService.addVehicle(Direction.NORTH, 1020l);
        // Second Vehicle Data
        vehicleMovementService.addVehicle(Direction.NORTH, 1060l);
        vehicleMovementService.addVehicle(Direction.NORTH, 1070l);

        Map<Integer, Map<Direction, List<VehicleData>>> vehicleDataMap = VehicleStorage.getInstance().getVehicleData();

        Assert.assertNotNull(vehicleDataMap);
        Assert.assertEquals(1, vehicleDataMap.size());
        Assert.assertEquals(1, vehicleDataMap.get(0).size());
        Assert.assertEquals(2, vehicleDataMap.get(0).get(Direction.NORTH).size());
    }

    @Test
    public void shouldUpdateDataInVehicleSingletonForDifferentDay() {
        VehicleMovementService vehicleMovementService = new TwoAxleVehicleMovementService();
        //First Day Vehicle Data
        vehicleMovementService.addVehicle(Direction.NORTH, 1100l);
        vehicleMovementService.addVehicle(Direction.NORTH, 1120l);
        //Second Day Vehicle Data
        vehicleMovementService.addVehicle(Direction.NORTH, 1011l);
        vehicleMovementService.addVehicle(Direction.NORTH, 1021l);

        Map<Integer, Map<Direction, List<VehicleData>>> vehicleDataMap = VehicleStorage.getInstance().getVehicleData();

        Assert.assertNotNull(vehicleDataMap);
        Assert.assertEquals(2, vehicleDataMap.size());
        //FirstDay Data
        Assert.assertEquals(1, vehicleDataMap.get(0).size());
        Assert.assertEquals(1, vehicleDataMap.get(0).get(Direction.NORTH).size());
        //Second Day Data
        Assert.assertEquals(1, vehicleDataMap.get(1).size());
        Assert.assertEquals(1, vehicleDataMap.get(1).get(Direction.NORTH).size());
    }

    @Test
    public void shouldReturnTotalVehicleCountByDuration() {
        addDummyData();
        VehicleMovementService vehicleMovementService = new TwoAxleVehicleMovementService();
        Map<Integer, Map<Direction, Map<Duration, Integer>>> vehicleData = vehicleMovementService.getTotalVehicleCountByDayDirectionAndTime(Duration.ofHours(1));

        Assert.assertEquals(1, vehicleData.get(0).size());
        Assert.assertEquals(1, vehicleData.get(0).get(Direction.NORTH).size());
        Assert.assertEquals(new Integer(3), vehicleData.get(0).get(Direction.NORTH).get(Duration.ofHours(1)));
    }

    @Test
    public void shouldReturnAverageVehicleCountByDuration() {
        addDummyData();
        VehicleMovementService vehicleMovementService = new TwoAxleVehicleMovementService();
        Map<Integer, Map<Direction, Map<Duration, Long>>> vehicleData = vehicleMovementService.getAverageVehicleDistributionByDayDirectionAndTime(Duration.ofHours(1));

        Assert.assertEquals(1, vehicleData.get(0).size());
        Assert.assertEquals(1, vehicleData.get(0).get(Direction.NORTH).size());
        Assert.assertEquals(new Long(403), vehicleData.get(0).get(Direction.NORTH).get(Duration.ofHours(1)));
    }

    @After
    public void cleanSingletonData() {
        VehicleStorage.getInstance().setVehicleData(new LinkedHashMap<Integer, Map<Direction, List<VehicleData>>>());
    }

    private void addDummyData() {
        VehicleMovementService vehicleMovementService = new TwoAxleVehicleMovementService();
        //First Vehicle data
        vehicleMovementService.addVehicle(Direction.NORTH, 1000l);
        vehicleMovementService.addVehicle(Direction.NORTH, 1020l);
        // Second Vehicle Data
        vehicleMovementService.addVehicle(Direction.NORTH, 1160l);
        vehicleMovementService.addVehicle(Direction.NORTH, 1170l);
        //Third Vehicle Data
        // Second Vehicle Data
        vehicleMovementService.addVehicle(Direction.NORTH, 2240l);
        vehicleMovementService.addVehicle(Direction.NORTH, 2250l);
    }
}
