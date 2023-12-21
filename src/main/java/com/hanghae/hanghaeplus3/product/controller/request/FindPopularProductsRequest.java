package com.hanghae.hanghaeplus3.product.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

public record FindPopularProductsRequest(
        @Positive
        Integer duration,
        @Min(1)
        Integer count
) {
    @Builder
    public FindPopularProductsRequest {
        if (ObjectUtils.isEmpty(duration)) {
            duration = 3;
        }

        if (ObjectUtils.isEmpty(count)) {
            count = 5;
        }
    }

    public LocalDate getSearchDate() {
        return LocalDate. now();
    }
}
