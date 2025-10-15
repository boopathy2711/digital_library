package com.SBP1.digital_library.dto.response;

import com.SBP1.digital_library.enums.TxnStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TxnResponse {

    private String bookNo;

    private String userEmail;

    private TxnStatus txnStatus;
}
