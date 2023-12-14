package com.hanghae.hanghaeplus3.item.controller;

import com.hanghae.hanghaeplus3.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemRestController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<?> findItems() {
        return ResponseEntity.ok(itemService.findItems());
    }

    @GetMapping("/popular-item")
    public ResponseEntity<?> findPopularItems(@RequestParam(defaultValue = "3") Integer duration,
                                              @RequestParam(defaultValue = "5") Integer count) {
        return ResponseEntity.ok(itemService.findPopularItems(duration, count));
    }
}
