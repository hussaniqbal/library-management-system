package com.aeon.library.model;

import jakarta.persistence.*;

@Entity
@Table(name = "borrow_book")
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_book_id_gen")
    @SequenceGenerator(name = "borrow_book_id_gen", sequenceName = "borrow_book_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private Borrower borrower;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private boolean returned;

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
