package com.hanghae.hanghaeplus3.item.controller;

import com.hanghae.hanghaeplus3.item.service.ItemService;
import com.hanghae.hanghaeplus3.item.service.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemRestController.class)
class ItemRestControllerTest {

    @Autowired MockMvc mvc;

    @MockBean ItemService itemService;

    @Test
    @DisplayName("상품 목록 조회")
    public void findItems() throws Exception {
        // given
        given(itemService.findItems()).willReturn(getMockItemList());

        // when
        mvc.perform(get("/items"))
                .andExpect(status().isOk());

        // then
    }

    @Test
    @DisplayName("인기 상품 목록 조회")
    public void findPopularItems() throws Exception {
        // given
        given(itemService.findPopularItems(anyInt(), anyInt())).willReturn(getMockItemList());

        // when
        mvc.perform(get("/items/popular-item"))
                .andExpect(status().isOk());

        // then
    }

    private List<Item> getMockItemList() {
        return List.of(
                Item.builder().id(1L).name("itemA").price(1000).quantity(10).build(),
                Item.builder().id(2L).name("itemB").price(1500).quantity(5).build(),
                Item.builder().id(3L).name("itemC").price(2000).quantity(13).build()
        );
    }

}