package com.SBP1.digital_library.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookReturnRequest {

    private String bookNo;

    private String txnId;

    private String email;
}
