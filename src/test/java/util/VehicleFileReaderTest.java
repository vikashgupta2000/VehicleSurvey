package util;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class VehicleFileReaderTest {

    @Test
    public void shouldReadFileFromResources() throws IOException {
        String fileName = "TestSampleData.txt";
        VehicleFileReader fileReader = new VehicleFileReader();
        List<String> rawVehicleData = fileReader.readFieldAndConvertToList(fileName);
        Assert.assertNotNull(rawVehicleData);
        Assert.assertEquals(10, rawVehicleData.size());
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowErrorIfWrongFileNameIsProvided() throws IOException {
        String fileName = "WrongFileName.txt";
        VehicleFileReader fileReader = new VehicleFileReader();
        List<String> rawVehicleData = fileReader.readFieldAndConvertToList(fileName);
    }
}
