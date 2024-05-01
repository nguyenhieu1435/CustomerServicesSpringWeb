package vn.edu.iuh.fit.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.Delivery;
import vn.edu.iuh.fit.repositories.DeliveryRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryService {
    private DeliveryRepository deliveryRepository;

    public Delivery save(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Optional<Delivery> getDeliveryByDeliveryIdAndPhoneNumber(long deliveryId, String phoneNumber) {
        return deliveryRepository.getDeliveryByDeliveryIdAndPhoneNumber(deliveryId, phoneNumber);
    }
}
