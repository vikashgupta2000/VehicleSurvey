package service.serviceImpl;

import constants.Direction;
import model.TwoAxleVehicleData;
import model.VehicleData;
import org.apache.commons.collections4.CollectionUtils;
import service.VehicleMovementService;
import singleton.VehicleStorage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TwoAxleVehicleMovementService implements VehicleMovementService {

    @Override
    public void addVehicle(Direction direction, long timeInMillis) {
        int dayCount = VehicleStorage.getInstance().getDayCalculator().calculateDayNumber(direction, timeInMillis);
        addOrUpdate(dayCount, direction, timeInMillis);
    }

    @Override
    public Map<Integer, Map<Direction, Map<Duration, Integer>>> getTotalVehicleCountByDayDirectionAndTime(Duration durationTime) {
        //Map< DayCount, Map< Direction, Map< Time duration, number of vehicles>>>
        Map<Integer, Map<Direction, Map<Duration, Integer>>> _dayVehicleDataMap = new LinkedHashMap<>();

        Map<Integer, Map<Direction, List<VehicleData>>> vehicleDataMap = VehicleStorage.getInstance().getVehicleData();
        for(Map.Entry<Integer, Map<Direction, List<VehicleData>>> entryDayNumber : vehicleDataMap.entrySet()) {
            Map<Direction, Map<Duration, Integer>> _directionVehicleDataMap = new LinkedHashMap<>();
            _dayVehicleDataMap.put(entryDayNumber.getKey(), _directionVehicleDataMap);

            for(Map.Entry<Direction, List<VehicleData>> entryDirection : entryDayNumber.getValue().entrySet()) {
                Map<Duration, Integer> _durationVehicleData = new LinkedHashMap<>();
                _directionVehicleDataMap.put(entryDirection.getKey(), _durationVehicleData);

                int _vehicleCount = 0;
                Duration _timeInterval = durationTime;
                for(VehicleData vehicleData : entryDirection.getValue()) {
                    if(!(_timeInterval.minus(((TwoAxleVehicleData)vehicleData).getFirstAxleDuration()).isNegative())) {
                        _vehicleCount++;
                    } else {
                        _durationVehicleData.put(_timeInterval, _vehicleCount);
                        _vehicleCount = 1;
                        _timeInterval = _timeInterval.plus(durationTime);
                    }
                }
                _durationVehicleData.put(_timeInterval, _vehicleCount);
            }
        }
        return _dayVehicleDataMap;
    }

    @Override
    public Map<Integer, Map<Direction, Map<Duration, Long>>> getAverageVehicleDistributionByDayDirectionAndTime(Duration durationTime) {
        //Map< DayCount, Map< Direction, Map< Time duration, Average distance between cars in milli seconds>>>
        Map<Integer, Map<Direction, Map<Duration, Long>>> _dayVehicleDataMap = new LinkedHashMap<>();

        Map<Integer, Map<Direction, List<VehicleData>>> vehicleDataMap = VehicleStorage.getInstance().getVehicleData();
        for(Map.Entry<Integer, Map<Direction, List<VehicleData>>> entryDayNumber : vehicleDataMap.entrySet()) {
            Map<Direction, Map<Duration, Long>> _directionVehicleDataMap = new LinkedHashMap<>();
            _dayVehicleDataMap.put(entryDayNumber.getKey(), _directionVehicleDataMap);

            for(Map.Entry<Direction, List<VehicleData>> entryDirection : entryDayNumber.getValue().entrySet()) {
                Map<Duration, Long> _durationVehicleData = new LinkedHashMap<>();
                _directionVehicleDataMap.put(entryDirection.getKey(), _durationVehicleData);

                int _vehicleCount = 0;
                Duration _totalDistanceByDuration = Duration.ZERO;
                Duration _timeInterval = durationTime;
                for(VehicleData vehicleData : entryDirection.getValue()) {
                    if(!(_timeInterval.minus(((TwoAxleVehicleData)vehicleData).getFirstAxleDuration()).isNegative())) {
                        _vehicleCount++;
                        _totalDistanceByDuration = _totalDistanceByDuration.plus(((TwoAxleVehicleData)vehicleData).getDistanceFromPreviousVehicle());
                    } else {
                        _durationVehicleData.put(_timeInterval, _totalDistanceByDuration.toMillis()/_vehicleCount);
                        _vehicleCount = 1;
                        _totalDistanceByDuration = Duration.ZERO;
                        _totalDistanceByDuration = _totalDistanceByDuration.plus(((TwoAxleVehicleData)vehicleData).getDistanceFromPreviousVehicle());
                        _timeInterval = _timeInterval.plus(durationTime);
                    }
                }
                _durationVehicleData.put(_timeInterval, _totalDistanceByDuration.toMillis()/_vehicleCount);
            }
        }
        return _dayVehicleDataMap;
    }

    private void addOrUpdate(int dayCount, Direction direction, long timeInMillis) {
        Map<Integer, Map<Direction, List<VehicleData>>> _vehicleDayCountDataMap =  VehicleStorage.getInstance().getVehicleData();
        if(!_vehicleDayCountDataMap.containsKey(dayCount)) {
            _vehicleDayCountDataMap.put(dayCount, new HashMap<Direction, List<VehicleData>>());
        }
        Map<Direction, List<VehicleData>> _vehicleDirectionDataMap = _vehicleDayCountDataMap.get(dayCount);
        if(!_vehicleDirectionDataMap.containsKey(direction)) {
            _vehicleDirectionDataMap.put(direction, new ArrayList<VehicleData>());
        }

        List<VehicleData> _vehicleDataList = _vehicleDirectionDataMap.get(direction);

        if (CollectionUtils.isEmpty(_vehicleDataList)) {
            TwoAxleVehicleData _vehicleData = create(timeInMillis);
            _vehicleDataList.add(_vehicleData);
        } else {
            TwoAxleVehicleData _lastVehicleData = (TwoAxleVehicleData) _vehicleDataList.get(_vehicleDataList.size() - 1);
            if (null == _lastVehicleData.getSecondAxleDuration()) {
                _lastVehicleData.setSecondAxleDuration(timeInMillis);
            } else {
                TwoAxleVehicleData _vehicleData = create(timeInMillis);
                _vehicleData.calculateDistanceFromPreviousVehicle(_lastVehicleData.getSecondAxleDuration());
                _vehicleDataList.add(_vehicleData);
            }
        }
    }

    private TwoAxleVehicleData create(long timeInMillis) {
        TwoAxleVehicleData vehicleData = new TwoAxleVehicleData();
        vehicleData.setFirstAxleDuration(timeInMillis);
        return vehicleData;
    }

}
