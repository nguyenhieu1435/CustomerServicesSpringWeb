package vn.edu.iuh.fit.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ReceiveDeviceInfoBody {
    private String name;
    private String model;
    private int usingMonth;
    private int displayStatus;
    private int cameraStatus;
    private int batteryStatus;
    private int faceDetectionStatus;
    private int fingerPrintStatus;
    private String description;
    private String username;

}
