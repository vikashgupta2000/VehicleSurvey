package handler;

import constants.Direction;
import service.VehicleMovementService;

public enum VehicleHandler {
    A {
        @Override
        public void vehicleDirection(VehicleMovementService vehicleMovementService, long timeInMillis) {
            vehicleMovementService.addVehicle(Direction.NORTH, timeInMillis);
        }
    },
    B {
        @Override
        public void vehicleDirection(VehicleMovementService vehicleMovementService, long timeInMillis) {
            vehicleMovementService.addVehicle(Direction.SOUTH, timeInMillis);
        }
    };

    public abstract void vehicleDirection(VehicleMovementService vehicleMovementService, long timeInMillis);
}
