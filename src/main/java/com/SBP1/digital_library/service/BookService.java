package com.SBP1.digital_library.service;

import com.SBP1.digital_library.dto.BookCreationRequest;
import com.SBP1.digital_library.dto.BookCreationResponse;
import com.SBP1.digital_library.dto.BookFilterResponse;
import com.SBP1.digital_library.enums.BookFilter;
import com.SBP1.digital_library.enums.Operator;
import com.SBP1.digital_library.enums.UserFilter;
import com.SBP1.digital_library.model.Author;
import com.SBP1.digital_library.model.Books;
import com.SBP1.digital_library.repository.BookRepository;
import com.SBP1.digital_library.service.BookFilter.BookFilterFactory;
import com.SBP1.digital_library.service.BookFilter.BookFilterStratergy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookFilterFactory bookFilterFactory;

    public BookCreationResponse addBook(BookCreationRequest bookCreationRequest){
        Author authorFromDB = authorService.getAuthor(bookCreationRequest.getAuthorEmail());
//        if(authorFromDB==null){
//            authorFromDB = authorService.addAuthor(Author.builder().
//                    id(UUID.randomUUID().toString()).
//                    name(bookCreationRequest.getAuthorName())
//                    .email(bookCreationRequest.getAuthorEmail()).createdOn(new Date()).updatedOn(new Date()).build());
//        }
        Books books = bookCreationRequest.toBook();
        if(authorFromDB==null){
            books.setAuthor(Author.builder().
                    id(UUID.randomUUID().toString()).
                    name(bookCreationRequest.getAuthorName())
                    .email(bookCreationRequest.getAuthorEmail()).createdOn(new Date()).updatedOn(new Date()).build());
        }
        else{
            books.setAuthor(authorFromDB);
        }
        Books booksFromDB = bookRepository.save(books);
        return BookCreationResponse.builder().
                title(booksFromDB.getTitle()).
                bookType(booksFromDB.getBookType()).
                bookNo(booksFromDB.getBookNo()).
                securityAmount(booksFromDB.getSecurityAmount()).
                authorId(booksFromDB.getAuthor().getId()).
                authorName(booksFromDB.getAuthor().getName()).
                authorEmail(booksFromDB.getAuthor().getEmail()).build();
    }

    public List<BookFilterResponse> filter(BookFilter filterBy, Operator operator, String value){
        BookFilterStratergy stratergy = bookFilterFactory.getStratergy(filterBy);
        return stratergy.getFilteredBook(operator, value);
    }

    public List<BookFilterResponse> getBooks(){
        return bookRepository.findAllBooks();
    }

}
