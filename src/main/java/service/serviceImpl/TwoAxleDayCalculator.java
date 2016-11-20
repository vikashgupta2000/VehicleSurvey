package service.serviceImpl;

import constants.Direction;
import service.DayCalculator;

import java.util.HashMap;
import java.util.Map;

public class TwoAxleDayCalculator implements DayCalculator {
    private Map<Direction, Integer> _dayCountForDirection;
    private long _lastRecorderTimeForNorthBoundVehicle;
    private long _lastRecorderTimeForSouthBoundVehicle;

    public TwoAxleDayCalculator() {
        _dayCountForDirection = new HashMap<>();
        for(Direction direction : Direction.values()) {
            _dayCountForDirection.put(direction, 0);
        }
    }


    @Override
    public Integer calculateDayNumber(Direction direction, long timeInMillis) {
        Integer _dayCount = _dayCountForDirection.get(direction);
        switch (direction) {
            case NORTH :
                if(_lastRecorderTimeForNorthBoundVehicle > timeInMillis) {
                    _dayCount = _dayCountForDirection.get(Direction.NORTH) + 1;
                    _dayCountForDirection.put(Direction.NORTH, _dayCount);
                }
                _lastRecorderTimeForNorthBoundVehicle = timeInMillis;
                break;
            case SOUTH:
                if(_lastRecorderTimeForSouthBoundVehicle > timeInMillis) {
                    _dayCount = _dayCountForDirection.get(Direction.SOUTH) + 1;
                    _dayCountForDirection.put(Direction.SOUTH, _dayCount);
                }
                _lastRecorderTimeForSouthBoundVehicle = timeInMillis;
                break;
        }
        return _dayCount;
    }
}
