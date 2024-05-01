package vn.edu.iuh.fit.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class SignUpBody {
    private String email;
    private String fullName;
    private String password;
    private String address;
    private String phoneNumber;
    private String gender;
    private LocalDate dob;
    private String bankAccountNumber;
    private String bankName;
}
