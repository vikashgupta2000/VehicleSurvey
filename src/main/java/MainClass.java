import adapter.DisplayVehicleData;
import service.VehicleExecutableService;

import java.io.FileNotFoundException;
import java.time.Duration;

public class MainClass {

    public static void main(String[] args) throws FileNotFoundException {
        final String vehicleType = "TWO_AXLE";
        final String path = "VehicleSurveyCodingChallengeSampleData.txt";

        VehicleExecutableService vehicleExecutableService = new VehicleExecutableService();
        vehicleExecutableService.addVehicleData(vehicleType, path);

        DisplayVehicleData displayVehicleData = new DisplayVehicleData();
        displayVehicleData.displayVehicleCountByTimeDuration(vehicleType, Duration.ofHours(6)); // Pass the duration for which the vehicle count has to be shown

        System.out.println("=================================================================");

        displayVehicleData.displayAverageDistanceBetweenVehiclesByTimeDuration(vehicleType, Duration.ofHours(12));  // Pass the duration for which the average vehicle distribution has to be shown
    }

}
