package com.aeon.library.controller;

import com.aeon.library.exception.LibraryException;
import com.aeon.library.model.Borrower;
import com.aeon.library.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    @Autowired
    private BorrowerService borrowerService;

    @PostMapping
    public ResponseEntity<?> registerBorrower(@RequestBody Borrower borrower) {
        try {
            Borrower savedBorrower = borrowerService.registerBorrower(borrower);
            return new ResponseEntity<>(savedBorrower, HttpStatus.CREATED);
        } catch (LibraryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Borrower>> getAllBorrowers() {
        List<Borrower> borrowers = borrowerService.getAllBorrowers();
        return new ResponseEntity<>(borrowers, HttpStatus.OK);
    }
}