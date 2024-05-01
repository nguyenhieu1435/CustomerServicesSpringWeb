package vn.edu.iuh.fit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.dto.SignInBody;
import vn.edu.iuh.fit.dto.SignUpBody;
import vn.edu.iuh.fit.dto.UserInfoLoginResponse;
import vn.edu.iuh.fit.models.Customer;
import vn.edu.iuh.fit.services.CustomerService;

@RestController
@RequestMapping(value = "/api/customer")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
    private CustomerService customerService;

    @PostMapping(value = "/sign-up", consumes = {MediaType.APPLICATION_JSON_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> signUp(@RequestBody SignUpBody signUpBody) {

        Customer customerExist = customerService.getCustomerByEmail(signUpBody.getEmail()).orElse(null);
        if (customerExist != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        customerExist = customerService.getCustomerByPhoneNumber(signUpBody.getPhoneNumber()).orElse(null);
        if (customerExist != null) {
            return ResponseEntity.badRequest().body("Phone number already exists");
        }

        Customer customer = new Customer(
                signUpBody.getEmail(),
                signUpBody.getFullName(),
                signUpBody.getPassword(),
                signUpBody.getAddress(),
                signUpBody.getPhoneNumber(),
                signUpBody.getGender(),
                signUpBody.getDob(),
                signUpBody.getBankAccountNumber(),
                signUpBody.getBankName()
        );
        customerService.signUp(customer);
        return ResponseEntity.ok("Sign up successfully");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInBody signInBody){
        Customer customer = customerService.signIn(signInBody.getEmail(), signInBody.getPassword()).orElse(null);
        if (customer == null){
            return ResponseEntity.badRequest().body("Email or password is incorrect");
        } else {
            System.out.println("Sign in successfully");
            UserInfoLoginResponse userInfoLoginResponse = new UserInfoLoginResponse(
                    customer.getEmail(),
                    customer.getFullName(),
                    customer.getAddress(),
                    customer.getPhoneNumber(),
                    customer.getGender(),
                    customer.getDob(),
                    customer.getBankAccountNumber(),
                    customer.getBankName()
            );
            return ResponseEntity.ok(userInfoLoginResponse);
        }
    }
}
