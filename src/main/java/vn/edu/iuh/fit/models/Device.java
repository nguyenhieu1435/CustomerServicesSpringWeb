package vn.edu.iuh.fit.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.enums.*;

import java.io.Serializable;

@Entity
@Table(name = "devices")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Device implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long deviceId;
    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;
    @Column(name = "model", columnDefinition = "VARCHAR(50)", nullable = false)
    private String model;
    @Column(name = "camera_status", nullable = false)
    private CameraStatus cameraStatus;
    @Column(name = "battery_status", nullable = false)
    private BatteryStatus batteryStatus;
    @Column(name = "display_status", nullable = false)
    private DisplayStatus displayStatus;
    @Column(name = "face_detection_status", nullable = false)
    private FaceDetectionStatus faceDetectionStatus;
    @Column(name = "finger_print_status", nullable = false)
    private FingerPrintStatus fingerPrintStatus;
    @Column(name = "using_month", nullable = false)
    private int usingMonth;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price_suggested", nullable = false)
    private double priceSuggested;
    @Column(name = "accept", columnDefinition = "boolean default 0")
    private boolean accept;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "email", nullable = false)
    private Customer customerID;

    @JsonBackReference
    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL)
    private Delivery delivery;

    public Device(String name, String model, CameraStatus cameraStatus, BatteryStatus batteryStatus, DisplayStatus displayStatus, FaceDetectionStatus faceDetectionStatus, FingerPrintStatus fingerPrintStatus, int usingMonth, String description, double priceSuggested, boolean accept, Customer customerID, Delivery delivery) {
        this.name = name;
        this.model = model;
        this.cameraStatus = cameraStatus;
        this.batteryStatus = batteryStatus;
        this.displayStatus = displayStatus;
        this.faceDetectionStatus = faceDetectionStatus;
        this.fingerPrintStatus = fingerPrintStatus;
        this.usingMonth = usingMonth;
        this.description = description;
        this.priceSuggested = priceSuggested;
        this.accept = accept;
        this.customerID = customerID;
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId=" + deviceId +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", cameraStatus=" + cameraStatus +
                ", batteryStatus=" + batteryStatus +
                ", displayStatus=" + displayStatus +
                ", faceDetectionStatus=" + faceDetectionStatus +
                ", fingerPrintStatus=" + fingerPrintStatus +
                ", usingMonth=" + usingMonth +
                ", description='" + description + '\'' +
                ", priceSuggested=" + priceSuggested +
                ", accept=" + accept +
                ", customerID=" + customerID +
                '}';
    }
}
