package com.library.service;

import com.library.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(Long id);

    Author saveAuthor(Author author);

    Author updateAuthor(Long id, Author author);

    void deleteAuthor(Long id);

    List<Author> findAuthorsWithBooks();

    boolean existsByEmail(String email, Long excludeId);
}
