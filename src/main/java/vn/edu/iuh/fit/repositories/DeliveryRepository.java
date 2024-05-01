package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.models.Delivery;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Query("SELECT d FROM Delivery d join d.device dv join dv.customerID c WHERE d.deliveryId = ?1 and c.phoneNumber = ?2")
    Optional<Delivery> getDeliveryByDeliveryIdAndPhoneNumber(long deliveryId, String phoneNumber);

}