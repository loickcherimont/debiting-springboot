package com.github.loickcherimont.debiting_springboot.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.loickcherimont.debiting_springboot.dto.DebitAmountDto;
import com.github.loickcherimont.debiting_springboot.models.Account;
import com.github.loickcherimont.debiting_springboot.models.AmountEntity;
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
        Account sampleAccount = new Account(UUID.fromString("a63cc6b1-d141-471f-bb11-26847f8825ec"), "CCP",
                "Jean DUPONT", new AmountEntity(new BigInteger("1000"), "EUR"));
        Account expectedAccount = new Account(UUID.fromString("a63cc6b1-d141-471f-bb11-26847f8825ec"), "CCP",
                "Jean DUPONT", new AmountEntity(new BigInteger("900"), "EUR"));
        DebitAmountDto debitAmountDto = new DebitAmountDto(100);

        // WHEN
        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.of(sampleAccount));

        // ASSERT
        Account actualAccount = accountService.debitAccountById(UUID.randomUUID(), debitAmountDto);

        assertThat(actualAccount.getAmountEntity().getAmount()).isEqualTo(expectedAccount.getAmountEntity().getAmount());
    }
}
