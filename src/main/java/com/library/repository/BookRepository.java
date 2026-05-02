package com.library.repository;

import com.library.dto.BookWithAuthorDTO;
import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByGenre(String genre);

    List<Book> findByAuthorId(Long authorId);

    List<Book> findByTitleContainingIgnoreCase(String title);

    // Custom inner join query between Book and Author tables
    @Query("SELECT new com.library.dto.BookWithAuthorDTO(" +
           "b.id, b.title, b.genre, b.publishedYear, b.isbn, " +
           "a.id, a.name, a.nationality) " +
           "FROM Book b INNER JOIN b.author a")
    List<BookWithAuthorDTO> findAllBooksWithAuthorDetails();

    @Query("SELECT new com.library.dto.BookWithAuthorDTO(" +
           "b.id, b.title, b.genre, b.publishedYear, b.isbn, " +
           "a.id, a.name, a.nationality) " +
           "FROM Book b INNER JOIN b.author a WHERE a.id = :authorId")
    List<BookWithAuthorDTO> findBooksWithAuthorDetailsByAuthorId(Long authorId);
}
