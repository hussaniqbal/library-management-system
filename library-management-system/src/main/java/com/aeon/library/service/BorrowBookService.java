package com.aeon.library.service;

import com.aeon.library.exception.LibraryException;
import com.aeon.library.model.Book;
import com.aeon.library.model.BorrowBook;
import com.aeon.library.model.Borrower;
import com.aeon.library.repository.BookRepository;
import com.aeon.library.repository.BorrowBookRepository;
import com.aeon.library.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BorrowBookService {
    @Autowired
    private BorrowBookRepository borrowBookRepository;
    @Autowired
    private BorrowerRepository borrowerRepository;
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BorrowBook borrowBook(Long borrowerId, Long bookId) {
        Borrower borrower = findBorrower(borrowerId);
        Book book = findBook(bookId);
        BorrowBook existingBorrow = findExistingBorrow(bookId);

        if (existingBorrow != null) {
            throw LibraryException.bookAlreadyBorrowed();
        }

        return createNewBorrow(borrower, book);
    }

    @Transactional
    public BorrowBook returnBook(Long loanId) {
        BorrowBook borrowBook = findBorrowBook(loanId);

        if (borrowBook.isReturned()) {
            throw LibraryException.bookAlreadyReturned();
        }

        return markAsReturned(borrowBook);
    }

    private Borrower findBorrower(Long borrowerId) {
        return borrowerRepository.findById(borrowerId).orElseThrow(LibraryException::borrowerNotFound);
    }

    private Book findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(LibraryException::bookNotFound);
    }

    private BorrowBook findExistingBorrow(Long bookId) {
        return borrowBookRepository.findByBookIdAndReturnedFalse(bookId).orElse(null);
    }

    private BorrowBook createNewBorrow(Borrower borrower, Book book) {
        BorrowBook borrowBook = new BorrowBook();
        borrowBook.setBorrower(borrower);
        borrowBook.setBook(book);
        borrowBook.setReturned(false);
        return borrowBookRepository.save(borrowBook);
    }

    private BorrowBook findBorrowBook(Long loanId) {
        return borrowBookRepository.findById(loanId).orElseThrow(LibraryException::bookBorrowNotFound);
    }

    private BorrowBook markAsReturned(BorrowBook borrowBook) {
        borrowBook.setReturned(true);
        return borrowBookRepository.save(borrowBook);
    }

}
