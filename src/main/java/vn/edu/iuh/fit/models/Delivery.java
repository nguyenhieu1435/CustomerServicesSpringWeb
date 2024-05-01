package vn.edu.iuh.fit.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.enums.DeliveryStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long deliveryId;
    @Column(name = "source_address", columnDefinition = "VARCHAR(150)", nullable = false)
    private String sourceAddress;
    @Column(name = "destination_address", columnDefinition = "VARCHAR(150)", nullable = false)
    private String destinationAddress;
    @Column(name = "status", nullable = false)
    private DeliveryStatus status;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    @JsonManagedReference
    private Device device;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Delivery(String sourceAddress, String destinationAddress, DeliveryStatus status, String description, Device device, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.status = status;
        this.description = description;
        this.device = device;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
