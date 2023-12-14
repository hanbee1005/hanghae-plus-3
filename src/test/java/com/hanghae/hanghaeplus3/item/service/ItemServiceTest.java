package com.hanghae.hanghaeplus3.item.service;

import com.hanghae.hanghaeplus3.item.service.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    private final ItemService service;

    private ItemServiceTest() {
        service = new ItemService(new FakeItemRepository());
    }

    @Test
    @DisplayName("상품 목록 조회")
    public void getItems() {
        // given

        // when
        List<Item> items = service.findItems();

        // then
        assertThat(items).isNotEmpty();
    }

    @Test
    @DisplayName("인기 상품 목록 조회")
    public void getPopularItems() {
        // given


        // when

        // then
    }

}