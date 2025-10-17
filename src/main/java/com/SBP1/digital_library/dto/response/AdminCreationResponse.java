package com.SBP1.digital_library.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AdminCreationResponse {
    private String userName;

    private String userEmail;

    private String userPhone;

    private String userAddress;
}
