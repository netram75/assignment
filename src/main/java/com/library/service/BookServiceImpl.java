package com.library.service;

import com.library.dto.BookWithAuthorDTO;
import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new DataIntegrityViolationException(
                "A book with ISBN '" + book.getIsbn() + "' already exists.");
        }
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Book existing = bookRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));

        Optional<Book> isbnConflict = bookRepository.findByIsbn(updatedBook.getIsbn());
        if (isbnConflict.isPresent() && !isbnConflict.get().getId().equals(id)) {
            throw new DataIntegrityViolationException(
                "ISBN '" + updatedBook.getIsbn() + "' is already used by another book.");
        }

        existing.setTitle(updatedBook.getTitle());
        existing.setGenre(updatedBook.getGenre());
        existing.setPublishedYear(updatedBook.getPublishedYear());
        existing.setIsbn(updatedBook.getIsbn());
        existing.setAuthor(updatedBook.getAuthor());
        return bookRepository.save(existing);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookWithAuthorDTO> getAllBooksWithAuthorDetails() {
        return bookRepository.findAllBooksWithAuthorDetails();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIsbn(String isbn, Long excludeId) {
        Optional<Book> found = bookRepository.findByIsbn(isbn);
        return found.isPresent() && !found.get().getId().equals(excludeId);
    }
}
