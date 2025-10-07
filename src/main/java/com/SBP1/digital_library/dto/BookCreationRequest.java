package com.SBP1.digital_library.dto;

import com.SBP1.digital_library.enums.BookType;
import com.SBP1.digital_library.model.Books;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.awt.print.Book;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookCreationRequest {
    @NotBlank(message = "Book title should not be blank")
    private String title;

    @NotBlank(message = "Book No should not be blank")
    private String bookNo;

    @NotNull(message = "Book Type should not be null")
    private BookType bookType;

    @Positive(message = "Security Amount should not be negative")
    private Integer securityAmount;

    @NotBlank(message = "Author Name should not be blank")
    private String authorName;

    @NotBlank(message = "Author Email should not be blank")
    private String authorEmail;


    public Books toBook(){
        return Books.builder().
                title(this.title).
                bookNo(this.bookNo).
                bookType(this.bookType).
                securityAmount(this.securityAmount).
                createdOn(new Date()).
                updatedOn(new Date()).build();
    }

}
