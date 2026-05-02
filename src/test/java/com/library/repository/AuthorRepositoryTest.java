package com.library.repository;

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
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    private Author savedAuthor;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        savedAuthor = authorRepository.save(
            new Author("Test Author", "test@example.com", "British", 1970));
    }

    @Test
    void findByEmail_shouldReturnAuthor_whenEmailExists() {
        Optional<Author> found = authorRepository.findByEmail("test@example.com");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test Author");
    }

    @Test
    void findByEmail_shouldReturnEmpty_whenEmailNotFound() {
        Optional<Author> found = authorRepository.findByEmail("notfound@example.com");
        assertThat(found).isEmpty();
    }

    @Test
    void findByNationality_shouldReturnMatchingAuthors() {
        authorRepository.save(new Author("Another Author", "another@example.com", "British", 1980));
        authorRepository.save(new Author("US Author", "us@example.com", "American", 1975));

        List<Author> britishAuthors = authorRepository.findByNationality("British");
        assertThat(britishAuthors).hasSize(2);
    }

    @Test
    void findByNameContainingIgnoreCase_shouldBeCaseInsensitive() {
        authorRepository.save(new Author("John Smith", "john@example.com", "American", 1965));
        List<Author> results = authorRepository.findByNameContainingIgnoreCase("john");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("John Smith");
    }

    @Test
    void findAuthorsWithBooks_shouldReturnOnlyAuthorsWithBooks() {
        Author authorWithoutBook = authorRepository.save(
            new Author("No Book Author", "nobook@example.com", "French", 1960));
        bookRepository.save(new Book("Test Book", "Fiction", 2020, "978-0000000001", savedAuthor));

        List<Author> authorsWithBooks = authorRepository.findAuthorsWithBooks();
        assertThat(authorsWithBooks).contains(savedAuthor);
        assertThat(authorsWithBooks).doesNotContain(authorWithoutBook);
    }

    @Test
    void save_shouldPersistAuthor() {
        Author newAuthor = new Author("New Author", "new@example.com", "German", 1990);
        Author result = authorRepository.save(newAuthor);
        assertThat(result.getId()).isNotNull();
        assertThat(authorRepository.count()).isEqualTo(2);
    }

    @Test
    void deleteById_shouldRemoveAuthor() {
        authorRepository.deleteById(savedAuthor.getId());
        assertThat(authorRepository.findById(savedAuthor.getId())).isEmpty();
    }
}
