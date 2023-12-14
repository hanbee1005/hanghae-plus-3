package com.hanghae.hanghaeplus3.item.controller.response;

import lombok.Builder;

import java.util.List;

public record FindItemsResponse(
        List<ItemResponse> items
) {
    @Builder
    public FindItemsResponse(List<ItemResponse> items) {
        this.items = items;
    }
}
