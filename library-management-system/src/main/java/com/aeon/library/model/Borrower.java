package com.aeon.library.model;


import jakarta.persistence.*;

@Entity
@Table(name = "borrower")
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrower_id_gen")
    @SequenceGenerator(name = "borrower_id_gen", sequenceName = "borrower_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    public Borrower(Long borrowerId) {
        this.id = borrowerId;
    }

    public Borrower() {

    }

    public String getEmail() {
        return email;
    }
}

