package vn.edu.iuh.fit.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.models.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {


    public Page<Device> findAllByCustomerID_EmailAndAcceptIsTrue(String email, Pageable pageable);
}