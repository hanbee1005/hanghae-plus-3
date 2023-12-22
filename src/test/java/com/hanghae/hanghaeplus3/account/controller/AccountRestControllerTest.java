package com.hanghae.hanghaeplus3.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.hanghaeplus3.account.controller.request.BalanceChargeRequest;
import com.hanghae.hanghaeplus3.account.service.AccountService;
import com.hanghae.hanghaeplus3.account.service.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountRestController.class)
class AccountRestControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    AccountService accountService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("계좌 잔고 조회")
    public void findBalancesOf() throws Exception {
        // given
        given(accountService.findBalanceOf(anyLong())).willReturn(mockAccounts());

        // when
        mvc.perform(get("/balance")
                        .header("Authorization", "1"))
                .andExpect(status().isOk());

        // then
    }

    @Test
    @DisplayName("계좌 잔고 충전")
    public void chargeBalanceOf() throws Exception {
        // given
        Long memberId = 1L;
        Long accountId = 1L;
        Long amount = 500L;

        BalanceChargeRequest balanceChargeRequest = new BalanceChargeRequest(memberId, accountId, amount);
        given(accountService.chargeBalance(anyLong(), anyLong(), anyLong())).willReturn(accountId);

        // when
        mvc.perform(put("/balance/charge")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(balanceChargeRequest)))
                .andExpect(status().isOk());

        // then
    }

    @Test
    @DisplayName("계좌 잔고 충전 실패 - memberId 는 null 일 수 없음")
    public void chargeBalanceFailByMemberId() throws Exception {
        // given
        Long memberId = null;
        Long accountId = 1L;
        Long amount = 500L;

        BalanceChargeRequest balanceChargeRequest = new BalanceChargeRequest(memberId, accountId, amount);

        // when
        mvc.perform(put("/balance/charge")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(balanceChargeRequest)))
                .andExpect(status().is4xxClientError());

        // then
    }

    @Test
    @DisplayName("계좌 잔고 충전 실패 - accountId 는 null 일 수 없음")
    public void chargeBalanceFailByAccountId() throws Exception {
        // given
        Long memberId = 1L;
        Long accountId = null;
        Long amount = 500L;

        BalanceChargeRequest balanceChargeRequest = new BalanceChargeRequest(memberId, accountId, amount);

        // when
        mvc.perform(put("/balance/charge")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(balanceChargeRequest)))
                .andExpect(status().is4xxClientError());

        // then
    }

    @Test
    @DisplayName("계좌 잔고 충전 실패 - 충전 금액은 양수이어야 함")
    public void chargeBalanceFailByAmount() throws Exception {
        // given
        Long memberId = 1L;
        Long accountId = 1L;
        Long amount = -1L;

        BalanceChargeRequest balanceChargeRequest = new BalanceChargeRequest(memberId, accountId, amount);

        // when
        mvc.perform(put("/balance/charge")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(balanceChargeRequest)))
                .andExpect(status().is4xxClientError());

        // then
    }

    private List<Account> mockAccounts() {
        return List.of(
                Account.builder()
                        .id(1L)
                        .memberId(1L)
                        .balance(10000L)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }
}