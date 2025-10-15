package com.SBP1.digital_library.dto.response;

import com.SBP1.digital_library.enums.BookType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookFilterResponse {
    private String bookTitle;
    private String bookNo;
    private BookType bookType;
    private Integer securityAmount;
    private String authorName;
    private String authorEmail;
    private Date createdOn;
    private Date updatedOn;
}
