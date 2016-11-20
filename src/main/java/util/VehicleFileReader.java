package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleFileReader {

    public List<String> readFieldAndConvertToList(String fileName) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileURL = classLoader.getResource(fileName);
        if(null == fileURL) {
            throw new FileNotFoundException();
        }
        File file = new File(fileURL.getFile());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
