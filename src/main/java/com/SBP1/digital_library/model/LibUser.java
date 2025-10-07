package com.SBP1.digital_library.model;

import com.SBP1.digital_library.enums.UserStatus;
import com.SBP1.digital_library.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class LibUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 15, nullable = false, unique = true)
    private String phoneNo;

    @Column(length = 50, nullable = true)
    private String email;

    private String address;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private Date createdOn;

    private Date updatedOn;

    @OneToMany (mappedBy = "libUser")
    private List<Books> booksList;

    @OneToMany(mappedBy = "libUser")
    private List<Txn> txnList;
}
