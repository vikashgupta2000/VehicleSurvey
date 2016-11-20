package service;

import constants.Direction;

public interface DayCalculator {
    Integer calculateDayNumber(Direction direction, long timeInMillis);
}
