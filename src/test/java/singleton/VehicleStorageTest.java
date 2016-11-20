package singleton;

import constants.Direction;
import junit.framework.Assert;
import model.VehicleData;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleStorageTest {

    @Test
    public void shouldCreateOneInstanceEvenCalledMultipleTimes() {
        VehicleStorage vehicleStorage1 = VehicleStorage.getInstance();
        VehicleStorage vehicleStorage2 = VehicleStorage.getInstance();

        Assert.assertEquals(vehicleStorage1, vehicleStorage2);
    }

    @Test
    public void shouldRetainDataOnceSavedEvenCalledMultipleTimes() {
        VehicleStorage vehicleStorage = VehicleStorage.getInstance();
        Map<Integer, Map<Direction, List<VehicleData>>> vehicleData = new HashMap<>();
        vehicleData.put(1, new HashMap<>());
        vehicleStorage.setVehicleData(vehicleData);

        Assert.assertEquals(VehicleStorage.getInstance().getVehicleData(), vehicleData);
    }
}
