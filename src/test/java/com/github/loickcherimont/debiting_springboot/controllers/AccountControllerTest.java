package com.github.loickcherimont.debiting_springboot.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.github.loickcherimont.debiting_springboot.dto.DebitAmountDto;
import com.github.loickcherimont.debiting_springboot.models.Account;
import com.github.loickcherimont.debiting_springboot.models.AmountEntity;
import com.github.loickcherimont.debiting_springboot.services.AccountService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AccountService accountService;

    @Test
    void shouldReturnDebitedAccount() throws Exception {

        // GIVEN
        Account expectedAccount = new Account(UUID.fromString("a63cc6b1-d141-471f-bb11-26847f8825ec"), "CCP", "Jean DUPONT", new AmountEntity(new BigInteger("900"), "EUR"));
        DebitAmountDto debitAmountDto = new DebitAmountDto(100);

        // WHEN
        when(accountService.debitAccountById(any(UUID.class), any(DebitAmountDto.class))).thenReturn(expectedAccount);

        // MOCK
        this.mockMvc
                .perform(patch("/api/accounts/a63cc6b1-d141-471f-bb11-26847f8825ec")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debitAmountDto)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value("a63cc6b1-d141-471f-bb11-26847f8825ec"))
                .andExpect(jsonPath("$.name").value(expectedAccount.getName()))
                .andExpect(jsonPath("$.owner").value(expectedAccount.getOwner()))
                .andExpect(jsonPath("$.amountEntity.amount").value(expectedAccount.getAmountEntity().getAmount()))
                .andExpect(jsonPath("$.amountEntity.currency").value(expectedAccount.getAmountEntity().getCurrency()));
    }

}
