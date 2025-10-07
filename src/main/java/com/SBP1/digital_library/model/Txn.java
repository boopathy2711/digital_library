package com.SBP1.digital_library.model;

import com.SBP1.digital_library.enums.TxnStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Txn{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String txnId;

    @Enumerated
    private TxnStatus txnStatus;

    private Double settlementAmount;

    private Date issuedDate;

    private Date submittedDate;

    @ManyToOne
    @JoinColumn
    private LibUser libUser;

    @ManyToOne
    @JoinColumn
    private Books books;
}
