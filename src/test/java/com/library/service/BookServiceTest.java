package com.library.service;

import com.library.dto.BookWithAuthorDTO;
import com.library.entity.Author;
import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Author author;
    private Book book;

    @BeforeEach
    void setUp() {
        author = new Author("Jane Austen", "austen@example.com", "British", 1775);
        author.setId(1L);
        book = new Book("Pride and Prejudice", "Romance", 1813, "978-0141439518", author);
        book.setId(1L);
    }

    @Test
    void getAllBooks_shouldReturnAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        List<Book> result = bookService.getAllBooks();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Pride and Prejudice");
    }

    @Test
    void getBookById_shouldReturnBook_whenFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Optional<Book> result = bookService.getBookById(1L);
        assertThat(result).isPresent();
        assertThat(result.get().getIsbn()).isEqualTo("978-0141439518");
    }

    @Test
    void getBookById_shouldReturnEmpty_whenNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Book> result = bookService.getBookById(99L);
        assertThat(result).isEmpty();
    }

    @Test
    void saveBook_shouldSave_whenIsbnIsUnique() {
        when(bookRepository.findByIsbn(anyString())).thenReturn(Optional.empty());
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.saveBook(book);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Pride and Prejudice");
        verify(bookRepository).save(book);
    }

    @Test
    void saveBook_shouldThrow_whenIsbnAlreadyExists() {
        when(bookRepository.findByIsbn(anyString())).thenReturn(Optional.of(book));

        assertThatThrownBy(() -> bookService.saveBook(book))
            .isInstanceOf(DataIntegrityViolationException.class)
            .hasMessageContaining("already exists");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void updateBook_shouldUpdate_whenValid() {
        Book updatedBook = new Book("Pride & Prejudice", "Classic", 1813, "978-0141439518", author);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.findByIsbn(anyString())).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.updateBook(1L, updatedBook);
        assertThat(result).isNotNull();
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void updateBook_shouldThrow_whenBookNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.updateBook(99L, book))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("not found");
    }

    @Test
    void getAllBooksWithAuthorDetails_shouldReturnDTOList() {
        BookWithAuthorDTO dto = new BookWithAuthorDTO(
            1L, "Pride and Prejudice", "Romance", 1813, "978-0141439518",
            1L, "Jane Austen", "British");
        when(bookRepository.findAllBooksWithAuthorDetails()).thenReturn(Arrays.asList(dto));

        List<BookWithAuthorDTO> result = bookService.getAllBooksWithAuthorDetails();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAuthorName()).isEqualTo("Jane Austen");
    }

    @Test
    void deleteBook_shouldCallRepositoryDelete() {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteBook(1L);
        verify(bookRepository).deleteById(1L);
    }
}
