package com.hanghae.hanghaeplus3.item.repository;

import com.hanghae.hanghaeplus3.item.service.ItemRepository;
import com.hanghae.hanghaeplus3.item.service.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDataSourceRepository implements ItemRepository {
    @Override
    public List<Item> findItems() {
        return null;
    }

    @Override
    public List<Item> findPopularItems(int duration, int count) {
        return null;
    }
}
