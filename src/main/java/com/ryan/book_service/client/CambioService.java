package com.ryan.book_service.client;

import com.ryan.book_service.dto.response.CambioResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "cambio-service")
public interface CambioService {
    @GetMapping("/api/cambio")
    public ResponseEntity<CambioResponseDto> getCambio(
            @RequestParam(name = "amount", defaultValue = "1.0") double amount,
            @RequestParam(name = "from", defaultValue = "USD") String from,
            @RequestParam(name = "to", defaultValue = "BRL") String to
    );
}
