package vn.edu.iuh.fit.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class AcceptSendDevice {
    private long deviceId;
    private String sourceAddress;
    private String username;
}
