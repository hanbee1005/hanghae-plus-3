package com.hanghae.hanghaeplus3.common.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Component
public class TransactionHandler {
    @Transactional
    public <T> T runOnWriteTransaction(Supplier<T> supplier) {
        return supplier.get();
    }
}
