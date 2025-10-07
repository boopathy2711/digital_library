package com.SBP1.digital_library.service.BookFilter;

import com.SBP1.digital_library.enums.BookFilter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookFilterFactory{

    private final Map<BookFilter, BookFilterStratergy> stratergies = new HashMap<>();

    public BookFilterFactory(BookTitleFilter bookTitleFilter, BookIdFilter bookIdFilter, BookAuthorFilter bookAuthorFilter){
        stratergies.put(BookFilter.BOOK_TITLE, bookTitleFilter);
        stratergies.put(BookFilter.BOOK_NO, bookIdFilter);
        stratergies.put(BookFilter.AUTHOR,bookAuthorFilter);
    }

    public BookFilterStratergy getStratergy(BookFilter bookFilter){
        return stratergies.get(bookFilter);
    }
}
