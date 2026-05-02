package com.library.service;

import com.library.entity.Author;
import com.library.repository.AuthorRepository;
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
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author("George Orwell", "orwell@example.com", "British", 1903);
        author.setId(1L);
    }

    @Test
    void getAllAuthors_shouldReturnAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author));
        List<Author> result = authorService.getAllAuthors();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("George Orwell");
        verify(authorRepository).findAll();
    }

    @Test
    void getAuthorById_shouldReturnAuthor_whenFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        Optional<Author> result = authorService.getAuthorById(1L);
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("George Orwell");
    }

    @Test
    void getAuthorById_shouldReturnEmpty_whenNotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Author> result = authorService.getAuthorById(99L);
        assertThat(result).isEmpty();
    }

    @Test
    void saveAuthor_shouldSave_whenEmailIsUnique() {
        when(authorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author result = authorService.saveAuthor(author);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("George Orwell");
        verify(authorRepository).save(author);
    }

    @Test
    void saveAuthor_shouldThrow_whenEmailAlreadyExists() {
        when(authorRepository.findByEmail(anyString())).thenReturn(Optional.of(author));

        assertThatThrownBy(() -> authorService.saveAuthor(author))
            .isInstanceOf(DataIntegrityViolationException.class)
            .hasMessageContaining("already exists");

        verify(authorRepository, never()).save(any());
    }

    @Test
    void updateAuthor_shouldUpdate_whenValid() {
        Author updated = new Author("George Orwell Updated", "orwell@example.com", "British", 1903);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorRepository.findByEmail("orwell@example.com")).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author result = authorService.updateAuthor(1L, updated);
        assertThat(result).isNotNull();
        verify(authorRepository).save(any(Author.class));
    }

    @Test
    void updateAuthor_shouldThrow_whenAuthorNotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.updateAuthor(99L, author))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("not found");
    }

    @Test
    void deleteAuthor_shouldCallRepositoryDelete() {
        doNothing().when(authorRepository).deleteById(1L);
        authorService.deleteAuthor(1L);
        verify(authorRepository).deleteById(1L);
    }
}
