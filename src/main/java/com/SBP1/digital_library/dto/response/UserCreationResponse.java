package com.SBP1.digital_library.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserCreationResponse {

    private String userName;

    private String userEmail;

    private String userPhone;

    private String userAddress;

}
