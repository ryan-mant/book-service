package com.ryan.book_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate launchDate;
    @Column(name = "price", nullable = false)
    private double price;
    @Transient
    private String currency;
    @Transient
    private String environment;
}
