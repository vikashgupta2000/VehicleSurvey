package factory;

import org.junit.Assert;
import org.junit.Test;
import service.DayCalculator;
import service.serviceImpl.TwoAxleDayCalculator;

public class DayCalculatorFactoryTest {

    @Test
    public void shouldInitializeObjectByName() {
        DayCalculator dayCalculator = Enum.valueOf(DayCalculatorFactory.class, "TWO_AXLE").getDayCalculatorService();
        if (!(dayCalculator instanceof TwoAxleDayCalculator)) {
            Assert.fail();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorIfNameDoesnotExists() {
        Enum.valueOf(DayCalculatorFactory.class, "DUMMY").getDayCalculatorService();
    }
}
