package factory;

import service.DayCalculator;
import service.serviceImpl.TwoAxleDayCalculator;

public enum DayCalculatorFactory {
    TWO_AXLE {
        @Override
        public DayCalculator getDayCalculatorService() {
            return new TwoAxleDayCalculator();
        }
    };
    public abstract DayCalculator getDayCalculatorService();
}
