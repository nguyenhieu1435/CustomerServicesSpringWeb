package vn.edu.iuh.fit.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class TrackingRequestBody {
    private long trackingID;
    private String phoneNumber;
}
