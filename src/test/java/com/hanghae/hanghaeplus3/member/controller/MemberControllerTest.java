package com.hanghae.hanghaeplus3.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.hanghaeplus3.member.controller.request.ChargeBalanceRequest;
import com.hanghae.hanghaeplus3.member.service.AccountService;
import com.hanghae.hanghaeplus3.member.service.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MemberController.class)
class MemberControllerTest {
    @Autowired MockMvc mvc;
    @MockBean
    AccountService accountService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회원 계좌 잔고 조회")
    public void findBalanceOf() throws Exception {
        // given
        given(accountService.findBalanceOf(any())).willReturn(any());
        Long memberId = 1L;

        // when
        mvc.perform(get("/members/{id}/balance", memberId))
                .andExpect(status().isOk());

        // then
    }

    @Test
    @DisplayName("회원 계좌 잔고 충전")
    public void chargeBalance() throws Exception {
        // given
        given(accountService.chargeBalance(anyLong(), anyLong(), anyLong())).willReturn(getAccount());

        Long memberId = 1L;
        ChargeBalanceRequest request = ChargeBalanceRequest.builder().accountId(1L).chargeBalance(1000L).build();

        // when
        mvc.perform(put("/members/{id}/balance/charge", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());

        // then
    }

    private <T> String toJson(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private static Account getAccount() {
        return Account.builder().id(1L).memberId(1L).balance(1000L).build();
    }

}