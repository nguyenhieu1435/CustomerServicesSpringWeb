package vn.edu.iuh.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class DeviceInformation {
    private String name;
    private String brand;
    private String model;
    private double batteryCapacity;
    private double ram_MB;
    private double storage_GB;
    private double usdPrice;


    @Override
    public String toString() {
        return "DeviceInformation{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                ", ram_MB=" + ram_MB +
                ", storage_GB=" + storage_GB +
                ", usdPrice=" + usdPrice +
                '}';
    }


}
