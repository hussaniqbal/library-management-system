package com.aeon.library.repository;

import com.aeon.library.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    Optional<BorrowBook> findByBookIdAndReturnedFalse(Long bookId);
}
