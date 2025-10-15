package com.SBP1.digital_library.repository;

import com.SBP1.digital_library.dto.response.BookFilterResponse;
import com.SBP1.digital_library.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Books, Integer> {
    @Query(value = """
    SELECT 
        title AS title,
        book_no AS bookNo,
        book_type AS bookType,
        security_amount AS securityAmount,
        created_on AS createdOn,
        updated_on AS updatedOn
    FROM books
    """, nativeQuery = true)
    List<BookFilterResponse> findAllBooks();

    List<Books> findByBookNo(String value);
    List<Books> findByTitle(String value);
    List<Books> findByTitleLike(String value);
    List<Books> findByAuthorName(String value);
    Books findEntityByBookNo(String value);
}
