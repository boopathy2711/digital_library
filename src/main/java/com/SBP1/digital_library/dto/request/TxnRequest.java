package com.SBP1.digital_library.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TxnRequest {

    @NotBlank(message = "User Email ID is must not be blank")
    private String userEmail;

    @NotBlank(message = "Book No must not be blank")
    private String bookNo;

}
