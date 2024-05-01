package vn.edu.iuh.fit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Customer implements Serializable {
    @Id
    @Column(name = "email", columnDefinition = "VARCHAR(100)")
    private String email;
    @Column(name = "full_name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String fullName;
    @Column(name = "password", columnDefinition = "VARCHAR(100)", nullable = false)
    private String password;
    @Column(name = "address", columnDefinition = "VARCHAR(100)")
    private String address;
    @Column(name = "phone_number", columnDefinition = "VARCHAR(20)", unique = true, nullable = false)
    private String phoneNumber;
    @Column(name = "gender", columnDefinition = "VARCHAR(10)")
    private String gender;
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "bank_account_number", columnDefinition = "VARCHAR(25)", nullable = false)
    private String bankAccountNumber;
    @Column(name = "bank_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String bankName;
    @JsonIgnore
    @OneToMany(mappedBy = "customerID", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Device> devices;

    public Customer(String email) {
        this.email = email;
    }

    public Customer(String email, String fullName, String password, String address, String phoneNumber, String gender, LocalDate dob, String bankAccountNumber, String bankName) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dob = dob;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
