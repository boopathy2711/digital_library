package com.SBP1.digital_library.model;

import com.SBP1.digital_library.enums.BookType;
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
public class Books{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String title;

    @Column(length = 20, unique = true)
    private String bookNo;

    @Enumerated(EnumType.STRING)
    private BookType bookType;

    @Column(nullable = false)
    private Integer securityAmount;

    private Date createdOn;

    private Date updatedOn;

    @ManyToOne
    @JoinColumn
    private LibUser libUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "author_id", referencedColumnName = "id"),
            @JoinColumn(name = "author_email", referencedColumnName = "email")
    })
    private Author author;

    @OneToMany (mappedBy = "books")
    private List<Txn> txnList;
}
