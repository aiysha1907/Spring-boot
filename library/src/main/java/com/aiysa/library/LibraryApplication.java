package com.aiysa.library;

import com.aiysa.library.entity.Author;
import com.aiysa.library.entity.Book;
import com.aiysa.library.service.LibraryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    CommandLineRunner run(LibraryService libraryService) {
        return args -> {
            // CREATE
            Author author = libraryService.createAuthorWithBooks();

            System.out.println("Created author: " + author.getName());
            System.out.println("Author ID: " + author.getId());

            // READ
            System.out.println("\nAll authors:");

            libraryService.getAllAuthors()
                    .forEach(existingAuthor ->
                            System.out.println(
                                    existingAuthor.getId()
                                            + " - "
                                            + existingAuthor.getName()
                            )
                    );

            System.out.println("\nBooks written by the author:");

            libraryService.getBooksByAuthor(author.getId())
                    .forEach(book ->
                            System.out.println(
                                    book.getId()
                                            + " - "
                                            + book.getTitle()
                            )
                    );

            // UPDATE
            Book firstBook = libraryService
                    .getBooksByAuthor(author.getId())
                    .getFirst();

            libraryService.updateBookTitle(
                    firstBook.getId(),
                    "Clean Code - Updated Edition"
            );

            System.out.println("\nBook title updated");

            // DELETE
            /*
            libraryService.deleteBook(firstBook.getId());
            System.out.println("Book deleted");
            */
        };
    }
}