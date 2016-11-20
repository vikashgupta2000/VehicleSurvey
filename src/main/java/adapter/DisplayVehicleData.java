package adapter;

import constants.Direction;
import service.VehicleExecutableService;

import java.time.Duration;
import java.util.Map;

public class DisplayVehicleData {

    public void displayVehicleCountByTimeDuration(String vehicleType, Duration durationTime) {
        VehicleExecutableService vehicleExecutableService = new VehicleExecutableService();
        Map<Integer, Map<Direction, Map<Duration, Integer>>> dayCountVehicleDataMap = vehicleExecutableService.getVehicleCountByDuration(vehicleType, durationTime);
        for (Map.Entry<Integer, Map<Direction, Map<Duration, Integer>>> dayCountVehicleDataEntry : dayCountVehicleDataMap.entrySet()) {
            System.out.println("Day Count : " + dayCountVehicleDataEntry.getKey());
            for (Map.Entry<Direction, Map<Duration, Integer>> directionVehicleDataMap : dayCountVehicleDataEntry.getValue().entrySet()) {
                System.out.println("    Direction : " + directionVehicleDataMap.getKey());
                for (Map.Entry<Duration, Integer> durationVehicleData : directionVehicleDataMap.getValue().entrySet()) {
                    System.out.println("        DurationTime :: " + durationVehicleData.getKey().toMinutes() + "(in minutes)  Vehicle Count :: " + durationVehicleData.getValue());
                }
            }
        }
    }

    public void displayAverageDistanceBetweenVehiclesByTimeDuration(String vehicleType, Duration durationTime) {
        VehicleExecutableService vehicleExecutableService = new VehicleExecutableService();
        Map<Integer, Map<Direction, Map<Duration, Long>>> dayCountVehicleDataMap = vehicleExecutableService.getAverageDistanceBetweenVehiclesByTimeDuration(vehicleType, durationTime);
        for (Map.Entry<Integer, Map<Direction, Map<Duration, Long>>> dayCountVehicleDataEntry : dayCountVehicleDataMap.entrySet()) {
            System.out.println("Day Count : " + dayCountVehicleDataEntry.getKey());
            for (Map.Entry<Direction, Map<Duration, Long>> directionVehicleDataMap : dayCountVehicleDataEntry.getValue().entrySet()) {
                System.out.println("    Direction : " + directionVehicleDataMap.getKey());
                for (Map.Entry<Duration, Long> durationVehicleData : directionVehicleDataMap.getValue().entrySet()) {
                    System.out.println("        DurationTime :: " + durationVehicleData.getKey().toMinutes() + "(in minutes)  Average vehicle distance in milli sec :: " + durationVehicleData.getValue());
                }
            }
        }
    }
}
