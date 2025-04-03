package com.ryan.book_service.controller;

import com.ryan.book_service.client.CambioService;
import com.ryan.book_service.dto.response.CambioResponseDto;
import com.ryan.book_service.model.Book;
import com.ryan.book_service.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("api/book")
@CrossOrigin
@RequiredArgsConstructor
public class BookController {
    private final Environment environment;
    private final BookRepository repository;
    private final CambioService cambioService;
    @GetMapping
    public ResponseEntity<Book> getBook(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "currency") String currency
    ) {
        var book = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        var port = environment.getProperty("local.server.port");
        var cambioResponseDto = cambioService.getCambio(book.getPrice(), "USD", currency);
        book.setEnvironment(port);
        assert cambioResponseDto != null;
        book.setPrice(Objects.requireNonNull(cambioResponseDto.getBody()).convertedValue());
        return ResponseEntity.ok(book);
    }
}
