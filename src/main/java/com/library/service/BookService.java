package com.library.service;

import com.library.dto.BookWithAuthorDTO;
import com.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book saveBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    List<BookWithAuthorDTO> getAllBooksWithAuthorDetails();

    boolean existsByIsbn(String isbn, Long excludeId);
}
