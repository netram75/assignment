package com.library;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (authorRepository.count() > 0) return;

        Author a1 = authorRepository.save(new Author("George Orwell",       "orwell@example.com",    "British",    1903));
        Author a2 = authorRepository.save(new Author("J.K. Rowling",        "rowling@example.com",   "British",    1965));
        Author a3 = authorRepository.save(new Author("F. Scott Fitzgerald", "fitzgerald@example.com","American",   1896));
        Author a4 = authorRepository.save(new Author("Gabriel García Márquez","garcia@example.com",  "Colombian",  1927));
        Author a5 = authorRepository.save(new Author("Toni Morrison",       "morrison@example.com",  "American",   1931));
        Author a6 = authorRepository.save(new Author("Leo Tolstoy",         "tolstoy@example.com",   "Russian",    1828));
        Author a7 = authorRepository.save(new Author("Jane Austen",         "austen@example.com",    "British",    1775));
        Author a8 = authorRepository.save(new Author("Haruki Murakami",     "murakami@example.com",  "Japanese",   1949));
        Author a9 = authorRepository.save(new Author("Chimamanda Adichie",  "adichie@example.com",   "Nigerian",   1977));
        Author a10 = authorRepository.save(new Author("Fyodor Dostoevsky", "dostoevsky@example.com", "Russian",    1821));

        bookRepository.save(new Book("Nineteen Eighty-Four",          "Dystopian",    1949, "978-0451524935", a1));
        bookRepository.save(new Book("Animal Farm",                   "Political",    1945, "978-0451526342", a1));
        bookRepository.save(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 1997, "978-0590353427", a2));
        bookRepository.save(new Book("The Great Gatsby",              "Classic",      1925, "978-0743273565", a3));
        bookRepository.save(new Book("One Hundred Years of Solitude", "Magical Realism", 1967, "978-0060883287", a4));
        bookRepository.save(new Book("Beloved",                       "Historical Fiction", 1987, "978-1400033416", a5));
        bookRepository.save(new Book("War and Peace",                 "Historical Fiction", 1869, "978-1400079988", a6));
        bookRepository.save(new Book("Pride and Prejudice",           "Romance",      1813, "978-0141439518", a7));
        bookRepository.save(new Book("Norwegian Wood",                "Literary Fiction", 1987, "978-0375704024", a8));
        bookRepository.save(new Book("Purple Hibiscus",               "Literary Fiction", 2003, "978-1616953638", a9));
    }
}
