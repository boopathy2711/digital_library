package com.SBP1.digital_library.dto.request;

import com.SBP1.digital_library.enums.UserStatus;
import com.SBP1.digital_library.model.LibUser;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreationRequest {

    @NotBlank(message = "User name must not be blank")
    private String userName;

    @NotBlank(message = "User Phone Number must not be blank")
    private String userPhone;

    private String userEmail;

    private String userAddress;

    public LibUser toLibUser(){
        return LibUser.builder().
                name(this.userName).
                phoneNo(this.userPhone).
                email(this.userEmail).
                address(this.userAddress).
                userStatus(UserStatus.ACTIVE).
                createdOn(new Date()).
                updatedOn(new Date()).build();
    }
}
