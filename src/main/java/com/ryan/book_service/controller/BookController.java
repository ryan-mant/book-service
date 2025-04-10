package com.ryan.book_service.controller;

import com.ryan.book_service.client.CambioService;
import com.ryan.book_service.dto.response.CambioResponseDto;
import com.ryan.book_service.model.Book;
import com.ryan.book_service.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Objects;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("api/book")
@CrossOrigin
@RequiredArgsConstructor
public class BookController {
    private final Environment environment;
    private final BookRepository repository;
    private final CambioService cambioService;

    @Operation(summary = "Get book by id")
    @GetMapping
    public ResponseEntity<Book> getBook(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "currency") String currency
    ) {
        var book = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        var port = environment.getProperty("local.server.port");
        var cambioResponseDto = cambioService.getCambio(book.getPrice(), "USD", currency);
        book.setEnvironment("book port: " + port + " cambio port: " + Objects.requireNonNull(cambioResponseDto.getBody()).environment());
        book.setPrice(Objects.requireNonNull(cambioResponseDto.getBody()).convertedValue());
        return ResponseEntity.ok(book);
    }
}
