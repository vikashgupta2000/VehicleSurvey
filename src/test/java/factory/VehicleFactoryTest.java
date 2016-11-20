package factory;

import org.junit.Assert;
import org.junit.Test;
import service.VehicleMovementService;
import service.serviceImpl.TwoAxleVehicleMovementService;

public class VehicleFactoryTest {

    @Test
    public void shouldInitializeObjectByName() {
        VehicleMovementService vehicleMovementService = Enum.valueOf(VehicleFactory.class, "TWO_AXLE").getVehicleService();
        if (!(vehicleMovementService instanceof TwoAxleVehicleMovementService)) {
            Assert.fail();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorIfNameDoesnotExists() {
        Enum.valueOf(VehicleFactory.class, "DUMMY_ERROR").getVehicleService();
    }
}
