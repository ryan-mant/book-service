package com.ryan.book_service.dto.response;

import java.math.BigDecimal;

public record CambioResponseDto(
    Long id,
    String from,
    String to,
    double conversionFactor,
    double convertedValue,
    String environment
) {
}
