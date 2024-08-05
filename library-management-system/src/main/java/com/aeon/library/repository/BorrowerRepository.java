package com.aeon.library.repository;

import com.aeon.library.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    Borrower findByEmail(String email);
}
