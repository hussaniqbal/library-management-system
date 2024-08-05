package com.aeon.library.service;

import com.aeon.library.exception.LibraryException;
import com.aeon.library.model.Borrower;
import com.aeon.library.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerService {
    @Autowired
    private BorrowerRepository borrowerRepository;


    public Borrower registerBorrower(Borrower borrower) {
        // Check if the borrower already exists
        Borrower existingBorrower = borrowerRepository.findByEmail(borrower.getEmail());

        if (existingBorrower != null) {
            throw LibraryException.borrowerAlreadyExists();
        }

        // Save and return the new borrower
        return borrowerRepository.save(borrower);
    }

    public List<Borrower> getAllBorrowers() {
        // Retrieve and return all borrowers
        return borrowerRepository.findAll();
    }
}
