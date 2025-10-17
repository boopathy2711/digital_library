package com.SBP1.digital_library.controller;

import com.SBP1.digital_library.dto.GenericResponse;
import com.SBP1.digital_library.dto.request.BookCreationRequest;
import com.SBP1.digital_library.dto.response.BookCreationResponse;
import com.SBP1.digital_library.dto.response.BookFilterResponse;
import com.SBP1.digital_library.enums.BookFilter;
import com.SBP1.digital_library.enums.Operator;
import com.SBP1.digital_library.service.BookService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public GenericResponse addBook(@RequestBody @Validated BookCreationRequest bookCreationRequest) {
        BookCreationResponse bookCreationResponse = bookService.addBook(bookCreationRequest);
        return GenericResponse.builder().data(bookCreationResponse).code(0).message("Book Added Successfully").build();
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

