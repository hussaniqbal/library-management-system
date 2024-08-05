package com.aeon.library.controller;

import com.aeon.library.exception.LibraryException;
import com.aeon.library.model.BorrowBook;
import com.aeon.library.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrow-book")
public class BorrowBookController {

    @Autowired
    private BorrowBookService borrowBookService;

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestParam Long borrowerId, @RequestParam Long bookId) {
        try {
            BorrowBook borrowedBook = borrowBookService.borrowBook(borrowerId, bookId);
            return new ResponseEntity<>(borrowedBook, HttpStatus.CREATED);
        } catch (LibraryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestParam Long loanId) {
        try {
            BorrowBook returnedBook = borrowBookService.returnBook(loanId);
            return new ResponseEntity<>(returnedBook, HttpStatus.OK);
        } catch (LibraryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}