package service;

import constants.Direction;

import java.time.Duration;
import java.util.Map;

public interface VehicleMovementService {
    void addVehicle(Direction direction, long timeInMillis);
    Map<Integer, Map<Direction, Map<Duration, Integer>>> getTotalVehicleCountByDayDirectionAndTime(Duration durationTime);
    Map<Integer, Map<Direction, Map<Duration, Long>>> getAverageVehicleDistributionByDayDirectionAndTime(Duration durationTime);
}
