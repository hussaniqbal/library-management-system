package com.aeon.library.controller;

import com.aeon.library.exception.LibraryException;
import com.aeon.library.model.Book;
import com.aeon.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<?> registerBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.registerBook(book);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (LibraryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
