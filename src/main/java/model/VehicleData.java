package model;

import java.time.Duration;

public class VehicleData {
    private Duration distanceFromPreviousVehicle = Duration.ZERO;

    public Duration getDistanceFromPreviousVehicle() {
        return distanceFromPreviousVehicle;
    }

    public void setDistanceFromPreviousVehicle(Duration distanceFromPreviousVehicle) {
        this.distanceFromPreviousVehicle = distanceFromPreviousVehicle;
    }
}
