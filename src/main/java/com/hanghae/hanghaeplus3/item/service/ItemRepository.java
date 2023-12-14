package com.hanghae.hanghaeplus3.item.service;

import com.hanghae.hanghaeplus3.item.service.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository {
    List<Item> findItems();
    List<Item> findPopularItems(int duration, int count);
}
