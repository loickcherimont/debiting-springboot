package com.github.loickcherimont.debiting_springboot.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.loickcherimont.debiting_springboot.models.Account;
import com.github.loickcherimont.debiting_springboot.models.Amount;
import com.github.loickcherimont.debiting_springboot.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void shouldReturnDebitedAccount() throws Exception {

        // GIVEN
        Account sampleAccount = new Account(UUID.randomUUID(), "CCP", "Jean DUPONT", new Amount(UUID.randomUUID(), new BigInteger("1000"), "EUR"));
        Account expectedAccount = new Account(UUID.randomUUID(), "CCP", "Jean DUPONT", new Amount(UUID.randomUUID(), new BigInteger("900"), "EUR"));
        Amount debitAmount = new Amount(UUID.randomUUID(), new BigInteger("100"), "EUR");


        // WHEN
        when(accountRepository.findById(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3"))).thenReturn(Optional.of(any(Account.class)));

        // ASSERT
        Account actualAccount = accountService.debitAccountById(sampleAccount.getId(), debitAmount);

        assertThat(actualAccount.getAmount().getValue()).isEqualTo(expectedAccount.getAmount().getValue());
    }
}
