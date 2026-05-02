package com.library.service;

import com.library.entity.Author;
import com.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author saveAuthor(Author author) {
        if (authorRepository.findByEmail(author.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException(
                "An author with email '" + author.getEmail() + "' already exists.");
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author existing = authorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + id));

        Optional<Author> emailConflict = authorRepository.findByEmail(updatedAuthor.getEmail());
        if (emailConflict.isPresent() && !emailConflict.get().getId().equals(id)) {
            throw new DataIntegrityViolationException(
                "Email '" + updatedAuthor.getEmail() + "' is already used by another author.");
        }

        existing.setName(updatedAuthor.getName());
        existing.setEmail(updatedAuthor.getEmail());
        existing.setNationality(updatedAuthor.getNationality());
        existing.setBirthYear(updatedAuthor.getBirthYear());
        return authorRepository.save(existing);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAuthorsWithBooks() {
        return authorRepository.findAuthorsWithBooks();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email, Long excludeId) {
        Optional<Author> found = authorRepository.findByEmail(email);
        return found.isPresent() && !found.get().getId().equals(excludeId);
    }
}
