package singleton;

import constants.Direction;
import model.VehicleData;
import service.DayCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleStorage {
    private VehicleStorage() {}
    private static VehicleStorage vehicleStorage;

    public static VehicleStorage getInstance() {
        if(null == vehicleStorage) {
            synchronized (VehicleStorage.class) {
                if (null == vehicleStorage) {
                    vehicleStorage = new VehicleStorage();
                }
            }
        }
        return vehicleStorage;
    }

    /**
     * Map< Day_Count, Map< Direction of moving vehicle , List< Vehicle movement Information >>>
     */
    private Map<Integer, Map<Direction, List<VehicleData>>> vehicleData = new HashMap<>();

    /**
     * Calculator to get the day count for vehicle running in a direction.
     */
    private DayCalculator dayCalculator;

    public Map<Integer, Map<Direction, List<VehicleData>>> getVehicleData() {
        return vehicleData;
    }

    public void setVehicleData(Map<Integer, Map<Direction, List<VehicleData>>> vehicleData) {
        this.vehicleData = vehicleData;
    }

    public DayCalculator getDayCalculator() {
        return dayCalculator;
    }

    public void setDayCalculator(DayCalculator dayCalculator) {
        this.dayCalculator = dayCalculator;
    }

}
