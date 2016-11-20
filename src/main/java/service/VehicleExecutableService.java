package service;

import constants.Direction;
import factory.DayCalculatorFactory;
import factory.VehicleFactory;
import handler.VehicleHandler;
import singleton.VehicleStorage;
import util.VehicleFileReader;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class VehicleExecutableService {
    public void addVehicleData(final String vehicleType, final String path) throws FileNotFoundException {
        List<String> list = new VehicleFileReader().readFieldAndConvertToList(path);
        // Set CalcuatorService in singleton to calibrate distance between vehicles
        if(null == VehicleStorage.getInstance().getDayCalculator()) {
            DayCalculator dayCalculator = Enum.valueOf(DayCalculatorFactory.class, vehicleType).getDayCalculatorService();
            VehicleStorage.getInstance().setDayCalculator(dayCalculator);
        }

        VehicleMovementService vehicleMovementService = Enum.valueOf(VehicleFactory.class, vehicleType).getVehicleService();

        for(String value : list) {
            String direction = value.substring(0, 1);
            Enum.valueOf(VehicleHandler.class, direction).vehicleDirection(vehicleMovementService, Long.parseLong(value.substring(1)));
        }
    }

    public Map<Integer, Map<Direction, Map<Duration, Integer>>> getVehicleCountByDuration(final String vehicleType, Duration durationTime) {
        VehicleMovementService vehicleMovementService = Enum.valueOf(VehicleFactory.class, vehicleType).getVehicleService();
        return vehicleMovementService.getTotalVehicleCountByDayDirectionAndTime(durationTime);
    }

    public Map<Integer, Map<Direction, Map<Duration, Long>>> getAverageDistanceBetweenVehiclesByTimeDuration(final String vehicleType, Duration durationTime) {
        VehicleMovementService vehicleMovementService = Enum.valueOf(VehicleFactory.class, vehicleType).getVehicleService();
        return vehicleMovementService.getAverageVehicleDistributionByDayDirectionAndTime(durationTime);
    }
}
