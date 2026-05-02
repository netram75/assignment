package com.library.repository;

import com.library.dto.BookWithAuthorDTO;
import com.library.entity.Author;
import com.library.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;
    private Book savedBook;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        author = authorRepository.save(new Author("Jane Doe", "jane@example.com", "American", 1975));
        savedBook = bookRepository.save(new Book("Test Book", "Fiction", 2020, "978-0000000001", author));
    }

    @Test
    void findByIsbn_shouldReturnBook_whenIsbnExists() {
        Optional<Book> found = bookRepository.findByIsbn("978-0000000001");
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Test Book");
    }

    @Test
    void findByIsbn_shouldReturnEmpty_whenIsbnNotFound() {
        Optional<Book> found = bookRepository.findByIsbn("999-9999999999");
        assertThat(found).isEmpty();
    }

    @Test
    void findByGenre_shouldReturnMatchingBooks() {
        bookRepository.save(new Book("Another Fiction", "Fiction", 2021, "978-0000000002", author));
        bookRepository.save(new Book("Science Book", "Science", 2022, "978-0000000003", author));

        List<Book> fictionBooks = bookRepository.findByGenre("Fiction");
        assertThat(fictionBooks).hasSize(2);
    }

    @Test
    void findByAuthorId_shouldReturnBooksForAuthor() {
        bookRepository.save(new Book("Second Book", "Romance", 2018, "978-0000000004", author));
        List<Book> books = bookRepository.findByAuthorId(author.getId());
        assertThat(books).hasSize(2);
    }

    @Test
    void findAllBooksWithAuthorDetails_shouldReturnInnerJoinResult() {
        List<BookWithAuthorDTO> result = bookRepository.findAllBooksWithAuthorDetails();
        assertThat(result).hasSize(1);
        BookWithAuthorDTO dto = result.get(0);
        assertThat(dto.getBookTitle()).isEqualTo("Test Book");
        assertThat(dto.getAuthorName()).isEqualTo("Jane Doe");
        assertThat(dto.getAuthorNationality()).isEqualTo("American");
    }

    @Test
    void findByTitleContainingIgnoreCase_shouldBeCaseInsensitive() {
        List<Book> results = bookRepository.findByTitleContainingIgnoreCase("test");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).isEqualTo("Test Book");
    }

    @Test
    void save_shouldPersistBook() {
        Book newBook = new Book("New Book", "History", 2023, "978-0000000099", author);
        Book result = bookRepository.save(newBook);
        assertThat(result.getId()).isNotNull();
        assertThat(bookRepository.count()).isEqualTo(2);
    }
}
