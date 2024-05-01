package vn.edu.iuh.fit.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class SignInBody {
    private String email;
    private String password;

}
