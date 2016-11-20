package model;

import java.time.Duration;

public class TwoAxleVehicleData extends VehicleData{
    private Duration firstAxleDuration;
    private Duration secondAxleDuration;

    public Duration getFirstAxleDuration() {
        return firstAxleDuration;
    }

    public void setFirstAxleDuration(Duration firstAxleDuration) {
        this.firstAxleDuration = firstAxleDuration;
    }

    public void setFirstAxleDuration(long timeInMillis) {
        this.firstAxleDuration = Duration.ofMillis(timeInMillis);;
    }

    public Duration getSecondAxleDuration() {
        return secondAxleDuration;
    }

    public void setSecondAxleDuration(Duration secondAxleDuration) {
        this.secondAxleDuration = secondAxleDuration;
    }

    public void setSecondAxleDuration(long timeInMillis) {
        this.secondAxleDuration = Duration.ofMillis(timeInMillis);
    }

    public void calculateDistanceFromPreviousVehicle(Duration durationOfLastAxleFromPreviousVehicle) {
        this.setDistanceFromPreviousVehicle(firstAxleDuration.minus(durationOfLastAxleFromPreviousVehicle));
    }

}
