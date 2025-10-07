package com.SBP1.digital_library.service.BookFilter;

import com.SBP1.digital_library.dto.BookFilterResponse;
import com.SBP1.digital_library.enums.Operator;
import com.SBP1.digital_library.model.Books;
import com.SBP1.digital_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookTitleFilter implements BookFilterStratergy {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<BookFilterResponse> getFilteredBook(Operator operator, String value) {
        if(operator.equals(Operator.EQUALS)){
            List<Books> books = bookRepository.findByTitle(value);
            return books.stream().map(book -> convertToBookFilterResponse(book)).collect(Collectors.toList());
        }
        else if(operator.equals(Operator.LIKE)){
            List<Books> books = bookRepository.findByTitleLike(value + "%");
            return books.stream().map(book -> convertToBookFilterResponse(book)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private BookFilterResponse convertToBookFilterResponse(Books books){
        return BookFilterResponse.builder().bookTitle(books.getTitle()).
                bookNo(books.getBookNo()).
                bookType(books.getBookType()).
                securityAmount(books.getSecurityAmount()).
                authorName(books.getAuthor().getName()).
                authorEmail(books.getAuthor().getEmail()).
                createdOn(books.getCreatedOn()).
                updatedOn(books.getUpdatedOn()).build();
    }
}
