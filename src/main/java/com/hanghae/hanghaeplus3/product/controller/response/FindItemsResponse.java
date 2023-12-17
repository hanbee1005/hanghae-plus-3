package com.hanghae.hanghaeplus3.product.controller.response;

import lombok.Builder;

import java.util.List;

public record FindItemsResponse(
        List<ItemResponse> items
) {
    @Builder
    public FindItemsResponse {}
}
