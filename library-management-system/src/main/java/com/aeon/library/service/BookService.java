package com.aeon.library.service;


import com.aeon.library.exception.LibraryException;
import com.aeon.library.model.Book;
import com.aeon.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book registerBook(Book book) {
        return saveBook(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    private Book saveBook(Book book) {
        List<Book> existingBooks = bookRepository.findByIsbn(book.getIsbn());

        if (!existingBooks.isEmpty()) {
            for (Book existing : existingBooks) {
                if (existing.getTitle().equals(book.getTitle()) && existing.getAuthor().equals(book.getAuthor())) {
                    return bookRepository.save(book);
                }
            }
            throw LibraryException.bookWithSameIsbnDifferentDetails();
        }

        return bookRepository.save(book);
    }
}
