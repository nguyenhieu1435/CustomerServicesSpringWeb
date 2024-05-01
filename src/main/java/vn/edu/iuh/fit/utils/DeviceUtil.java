package vn.edu.iuh.fit.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.dto.DeviceInformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class DeviceUtil {

    public Optional<DeviceInformation> handleGetDeviceInformation(String name, String model) throws IOException {
        try {
            FileInputStream file = new FileInputStream(new File("data/ndtv_data_final.xlsx"));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            DeviceInformation deviceInformation = new DeviceInformation();
            DataFormatter formatter = new DataFormatter();
            for (Row row : sheet){
                Cell nameCell = row.getCell(0);
                Cell modelCell = row.getCell(2);
                String deviceName = formatter.formatCellValue(nameCell).toLowerCase();
                String modelName = formatter.formatCellValue(modelCell).toLowerCase();

                if (deviceName.equalsIgnoreCase(name)
                        || modelName.equalsIgnoreCase(model)
                        || deviceName.equalsIgnoreCase(model)
                        || modelName.equalsIgnoreCase(name)) {
                    deviceInformation.setName(deviceName);
                    deviceInformation.setModel(modelName);
                    String brandName = row.getCell(1).getStringCellValue();
                    double batteryCapacity  = (double) row.getCell(3).getNumericCellValue();
                    double ram_MB = (double) row.getCell(9).getNumericCellValue();
                    double storage_GB = (double) row.getCell(10).getNumericCellValue();
                    double usdPrice = (double) row.getCell(21).getNumericCellValue();

                    deviceInformation.setBrand(brandName);
                    deviceInformation.setBatteryCapacity(batteryCapacity);
                    deviceInformation.setRam_MB(ram_MB);
                    deviceInformation.setStorage_GB(storage_GB);
                    deviceInformation.setUsdPrice(usdPrice);

                    return Optional.of(deviceInformation);
                }
            }
            workbook.close();
            file.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
