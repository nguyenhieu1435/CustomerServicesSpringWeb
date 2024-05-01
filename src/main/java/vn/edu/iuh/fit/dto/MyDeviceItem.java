package vn.edu.iuh.fit.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.enums.*;
import vn.edu.iuh.fit.models.Device;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class MyDeviceItem {
    private Long deviceId;
    private String name;
    private String model;
    private CameraStatus cameraStatus;
    private BatteryStatus batteryStatus;
    private DisplayStatus displayStatus;
    private FaceDetectionStatus faceDetectionStatus;
    private FingerPrintStatus fingerPrintStatus;
    private int usingMonth;
    private String description;
    private double priceSuggested;
    private boolean accept;
    private String userEmail;
    private long deliveryId;

    public MyDeviceItem(Device device, String userEmail, long deliveryId) {
        this.deviceId = device.getDeviceId();
        this.name = device.getName();
        this.model = device.getModel();
        this.cameraStatus = device.getCameraStatus();
        this.batteryStatus = device.getBatteryStatus();
        this.displayStatus = device.getDisplayStatus();
        this.faceDetectionStatus = device.getFaceDetectionStatus();
        this.fingerPrintStatus = device.getFingerPrintStatus();
        this.usingMonth = device.getUsingMonth();
        this.description = device.getDescription();
        this.priceSuggested = device.getPriceSuggested();
        this.accept = device.isAccept();
        this.userEmail = userEmail;
        this.deliveryId = deliveryId;
    }
}
