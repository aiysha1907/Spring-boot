package com.aiysa.library.service;

import com.aiysa.library.entity.Author;
import com.aiysa.library.entity.Book;
import com.aiysa.library.repository.AuthorRepository;
import com.aiysa.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Transactional
    public Author createAuthorWithBooks() {
        Author author = new Author("Robert C. Martin");

        Book cleanCode = new Book(
                "Clean Code",
                "9780132350884"
        );

        Book cleanArchitecture = new Book(
                "Clean Architecture",
                "9780134494166"
        );

        author.addBook(cleanCode);
        author.addBook(cleanArchitecture);

        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Transactional
    public void updateBookTitle(Long bookId, String newTitle) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Book not found"));

        book.setTitle(newTitle);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalArgumentException("Book not found");
        }

        bookRepository.deleteById(bookId);
    }
}