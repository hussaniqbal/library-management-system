package com.aeon.library.exception;

public class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }

    public static LibraryException borrowerNotFound() {
        return new LibraryException("Borrower does not exist in our system");
    }

    public static LibraryException bookNotFound() {
        return new LibraryException("Book does not exist in our system");
    }

    public static LibraryException bookAlreadyBorrowed() {
        return new LibraryException("Book is already borrowed");
    }

    public static LibraryException bookBorrowNotFound() {
        return new LibraryException("Book borrow does not exist");
    }

    public static LibraryException bookAlreadyReturned() {
        return new LibraryException("Book already returned");
    }

    public static LibraryException bookWithSameIsbnDifferentDetails() {
        return new LibraryException("Book with the same ISBN must have the same title and author");
    }

    public static LibraryException borrowerAlreadyExists() {
        return new LibraryException("Borrower with the same details already exists");
    }
    public static LibraryException borrowerWithSameEmailExists() {
        return new LibraryException("Borrower with the same email already exists.");
    }

}
