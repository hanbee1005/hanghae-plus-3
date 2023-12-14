package com.hanghae.hanghaeplus3.item.service;

import com.hanghae.hanghaeplus3.item.service.domain.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findItems() {
        return itemRepository.findItems();
    }

    public List<Item> findPopularItems(int duration, int count) {
        return itemRepository.findPopularItems(duration, count);
    }
}
