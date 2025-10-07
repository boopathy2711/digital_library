package com.SBP1.digital_library.controller;

import com.SBP1.digital_library.dto.BookCreationRequest;
import com.SBP1.digital_library.dto.BookCreationResponse;
import com.SBP1.digital_library.dto.BookFilterResponse;
import com.SBP1.digital_library.dto.UserFilterResponse;
import com.SBP1.digital_library.enums.BookFilter;
import com.SBP1.digital_library.enums.Operator;
import com.SBP1.digital_library.enums.UserFilter;
import com.SBP1.digital_library.model.Author;
import com.SBP1.digital_library.model.Books;
import com.SBP1.digital_library.service.AuthorService;
import com.SBP1.digital_library.service.BookService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public BookCreationResponse addBook(@RequestBody BookCreationRequest bookCreationRequest) {
        return bookService.addBook(bookCreationRequest);
    }

    @GetMapping("/filter")
    public List<BookFilterResponse> filteredBy(@NotNull(message = "filterBy must not be null") @RequestParam("filterBy") BookFilter filterBy,
                                               @NotNull(message = "operator must not be null") @RequestParam("operator") Operator operator,
                                               @NotBlank(message = "value must not be blank") @RequestParam("value") String value){
        return bookService.filter(filterBy, operator, value);
    }

    @GetMapping("/allbooks")
    public List<BookFilterResponse> getBooks(){
        return bookService.getBooks();
    }
}

