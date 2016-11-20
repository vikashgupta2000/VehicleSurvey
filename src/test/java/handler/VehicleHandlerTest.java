package handler;

import constants.Direction;
import service.VehicleMovementService;

public enum VehicleHandlerTest {
    A {
        @Override
        public void vehicleDirection(int value, VehicleMovementService vehicleMovementService) {
            vehicleMovementService.addVehicle(Direction.NORTH, value);
        }
    },
    B {
        @Override
        public void vehicleDirection(int value, VehicleMovementService vehicleMovementService) {
            vehicleMovementService.addVehicle(Direction.SOUTH, value);
        }
    };

    public abstract void vehicleDirection(int value, VehicleMovementService vehicleMovementService);
}
