package com.SBP1.digital_library.dto;

import com.SBP1.digital_library.enums.BookType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookCreationResponse {
    private String title;
    private String bookNo;
    private BookType bookType;
    private Integer securityAmount;
    private String authorId;
    private String authorName;
    private String authorEmail;
}
