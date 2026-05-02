package com.library.dto;

public class BookWithAuthorDTO {

    private Long bookId;
    private String bookTitle;
    private String genre;
    private Integer publishedYear;
    private String isbn;
    private Long authorId;
    private String authorName;
    private String authorNationality;

    public BookWithAuthorDTO(Long bookId, String bookTitle, String genre, Integer publishedYear,
                              String isbn, Long authorId, String authorName, String authorNationality) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorNationality = authorNationality;
    }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Integer getPublishedYear() { return publishedYear; }
    public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getAuthorNationality() { return authorNationality; }
    public void setAuthorNationality(String authorNationality) { this.authorNationality = authorNationality; }
}
