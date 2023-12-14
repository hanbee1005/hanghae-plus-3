package com.hanghae.hanghaeplus3.item.service;

import com.hanghae.hanghaeplus3.item.service.domain.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> findItems();
    List<Item> findPopularItems(int duration, int count);
}
