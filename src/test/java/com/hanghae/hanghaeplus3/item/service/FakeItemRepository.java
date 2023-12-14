package com.hanghae.hanghaeplus3.item.service;

import com.hanghae.hanghaeplus3.item.service.domain.Item;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FakeItemRepository implements ItemRepository {
    private final Map<Long, Item> store = new ConcurrentHashMap<>();

    FakeItemRepository() {
        store.put(1L, Item.builder().id(1L).name("itemA").price(1000).quantity(10).build());
        store.put(2L, Item.builder().id(2L).name("itemB").price(1500).quantity(5).build());
        store.put(3L, Item.builder().id(3L).name("itemC").price(2000).quantity(13).build());
    }

    @Override
    public List<Item> findItems() {
        return store.values().stream().toList();
    }

    @Override
    public List<Item> findPopularItems(int duration, int count) {
        return null;
    }
}
