package com.SBP1.digital_library.model;

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
@IdClass(AuthorCompositeKey.class)
public class Author{

    @Id
    private String id;

    @Id
    @Column(length = 50, nullable = true, unique = true)
    private String email;

    @Column(length = 50, nullable = false)
    private String name;

    private Date createdOn;

    private Date updatedOn;

    @OneToMany(mappedBy = "author")
    private List<Books> booksList;

}
