package vn.edu.iuh.fit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.dto.TrackingRequestBody;
import vn.edu.iuh.fit.models.Delivery;
import vn.edu.iuh.fit.services.DeliveryService;

import java.util.Optional;

@RestController
@RequestMapping("/api/delivery")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DeliveryController {
    private DeliveryService deliveryService;

    @PostMapping("/tracking-my-item")
    public ResponseEntity<?> handleTrackingMyItem(@RequestBody TrackingRequestBody trackingRequestBody){
        Optional<Delivery> deliveryOpt = deliveryService.getDeliveryByDeliveryIdAndPhoneNumber(trackingRequestBody.getTrackingID(), trackingRequestBody.getPhoneNumber());
        System.out.println("trackingRequestBody: " + trackingRequestBody);
        if (deliveryOpt.isEmpty()){
            return ResponseEntity.badRequest().body("Delivery not found");
        } else {
            System.out.println("Delivery found: " + deliveryOpt.get());
            return ResponseEntity.ok(deliveryOpt.get());
        }

    }
}
