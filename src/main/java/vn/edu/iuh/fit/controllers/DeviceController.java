package vn.edu.iuh.fit.controllers;


import lombok.AllArgsConstructor;
import org.apache.commons.math3.analysis.function.Divide;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.dto.*;
import vn.edu.iuh.fit.enums.*;
import vn.edu.iuh.fit.models.Customer;
import vn.edu.iuh.fit.models.Delivery;
import vn.edu.iuh.fit.models.Device;
import vn.edu.iuh.fit.repositories.DeviceRepository;
import vn.edu.iuh.fit.services.DeliveryService;
import vn.edu.iuh.fit.services.DeviceService;
import vn.edu.iuh.fit.utils.DeviceUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/device")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DeviceController {
    private DeviceUtil deviceUtil;
    private DeviceService deviceService;
    private DeliveryService deliveryService;


    @PostMapping("/accept-send-device")
    public ResponseEntity<String> acceptSendDevice(@RequestBody AcceptSendDevice acceptSendDevice){
        Device device = deviceService.getDeviceById(acceptSendDevice.getDeviceId()).orElse(null);
        if (device == null){
            return ResponseEntity.badRequest().body("Device not found");
        }
        device.setAccept(true);
        device = deviceService.save(device);

        Delivery delivery = new Delivery();

        delivery.setStatus(DeliveryStatus.DELIVERING);
        delivery.setDescription("Initial delivery");
        delivery.setSourceAddress(acceptSendDevice.getSourceAddress());
        delivery.setDestinationAddress("12 Nguyen Van Bao, P4, Go Vap, TP.HCM");
        delivery.setCreatedAt(LocalDateTime.now());
        delivery.setUpdatedAt(LocalDateTime.now());

        device = deviceService.save(device);
        delivery.setDevice(device);
        deliveryService.save(delivery);

        return ResponseEntity.ok("Accept send device successfully");
    }

    @PostMapping("/receive-divice-info")
    public ResponseEntity<?> receiveDeviceInfo(@RequestBody ReceiveDeviceInfoBody receiveDeviceInfoBody) throws IOException {
        DeviceInformation deviceInformation = deviceUtil.handleGetDeviceInformation(receiveDeviceInfoBody.getName()
                , receiveDeviceInfoBody.getModel()).orElse(null);
        if (deviceInformation == null){
            return ResponseEntity.badRequest().body("We are not support this device yet");
        }
        double suggestPrice = deviceInformation.getUsdPrice();
        // 0 is repair, 1 have problem, 2 normal
        if (receiveDeviceInfoBody.getDisplayStatus() == 0){
            suggestPrice = suggestPrice - (suggestPrice * 0.075);
        } else if (receiveDeviceInfoBody.getDisplayStatus() == 1){
            suggestPrice = suggestPrice - (suggestPrice * 0.1);
        } else {
            suggestPrice = suggestPrice - (suggestPrice * 0.03);
        }
        // 0 is repair, 1 have problem, 2 normal
        if (receiveDeviceInfoBody.getBatteryStatus() == 0){
            suggestPrice = suggestPrice - (suggestPrice * 0.075);
        } else if (receiveDeviceInfoBody.getBatteryStatus() == 1){
            suggestPrice = suggestPrice - (suggestPrice * 0.1);
        } else {
            suggestPrice = suggestPrice - (suggestPrice * 0.03);
        }
        // 0 is repair, 1 have problem, 2 normal
        if (receiveDeviceInfoBody.getCameraStatus() == 0){
            suggestPrice = suggestPrice - (suggestPrice * 0.075);
        } else if (receiveDeviceInfoBody.getCameraStatus() == 1){
            suggestPrice = suggestPrice - (suggestPrice * 0.1);
        } else {
            suggestPrice = suggestPrice - (suggestPrice * 0.03);
        }
        // 0 is repair, 1 have problem, 2 normal, 3 have not
        if (receiveDeviceInfoBody.getFaceDetectionStatus() == 0){
            suggestPrice = suggestPrice - (suggestPrice * 0.075);
        } else if (receiveDeviceInfoBody.getFaceDetectionStatus() == 1){
            suggestPrice = suggestPrice - (suggestPrice * 0.1);
        } else if (receiveDeviceInfoBody.getFaceDetectionStatus() == 2){
            suggestPrice = suggestPrice - (suggestPrice * 0.03);
        }

        if (receiveDeviceInfoBody.getFingerPrintStatus() == 0){
            suggestPrice = suggestPrice - (suggestPrice * 0.075);
        } else if (receiveDeviceInfoBody.getFingerPrintStatus() == 1){
            suggestPrice = suggestPrice - (suggestPrice * 0.1);
        } else if (receiveDeviceInfoBody.getFingerPrintStatus() == 2){
            suggestPrice = suggestPrice - (suggestPrice * 0.03);
        }

        suggestPrice = suggestPrice - (suggestPrice * (receiveDeviceInfoBody.getUsingMonth() * 0.015));
        System.out.println("suggest: " + suggestPrice);

        Device device = new Device();
        device.setName(deviceInformation.getName());
        device.setModel(deviceInformation.getModel());
        device.setUsingMonth(receiveDeviceInfoBody.getUsingMonth());
        device.setDisplayStatus(DisplayStatus.fromValue(receiveDeviceInfoBody.getDisplayStatus()));
        device.setBatteryStatus(BatteryStatus.fromValue(receiveDeviceInfoBody.getBatteryStatus()));
        device.setCameraStatus(CameraStatus.fromValue(receiveDeviceInfoBody.getCameraStatus()));
        device.setFaceDetectionStatus(FaceDetectionStatus.fromValue(receiveDeviceInfoBody.getFaceDetectionStatus()));
        device.setFingerPrintStatus(FingerPrintStatus.fromValue(receiveDeviceInfoBody.getFingerPrintStatus()));
        device.setAccept(false);
        device.setDescription(receiveDeviceInfoBody.getDescription());
        device.setPriceSuggested(suggestPrice);
        device.setCustomerID(new Customer(receiveDeviceInfoBody.getUsername()));
        device = deviceService.save(device);


        return ResponseEntity.ok(device);
    }

    @GetMapping("/get-devices")
    public ResponseEntity<?> getDevices(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> sizeNo,
                                        @RequestParam String username){
        int pageNoFinal = pageNo.orElse(1);
        int sizeNoFinal = sizeNo.orElse(5);

        Page<Device> devices = deviceService.pagingDeviceByUsername(username, pageNoFinal - 1, sizeNoFinal);
        if (devices.isEmpty()){
            return ResponseEntity.ok("No device found");
        }

        List<MyDeviceItem> devicesListResponse = new ArrayList<>();
        devices.getContent().forEach(device -> {

            MyDeviceItem myDeviceItem = new MyDeviceItem(device, username, device.getDelivery().getDeliveryId());
            devicesListResponse.add(myDeviceItem);

        });
        ListingItemPaging listingItemPaging = new ListingItemPaging(pageNoFinal, sizeNoFinal, devicesListResponse);
        return ResponseEntity.ok(listingItemPaging);
    }
}
