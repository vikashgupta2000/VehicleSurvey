package factory;

import service.VehicleMovementService;
import service.serviceImpl.TwoAxleVehicleMovementService;

public enum VehicleFactory {
    TWO_AXLE {
        @Override
        public VehicleMovementService getVehicleService() {
            return new TwoAxleVehicleMovementService();
        }
    };
    public abstract VehicleMovementService getVehicleService();
}
