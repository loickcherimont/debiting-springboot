package com.github.loickcherimont.debiting_springboot.services;

import java.math.BigInteger;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.loickcherimont.debiting_springboot.dto.DebitAmountDto;
import com.github.loickcherimont.debiting_springboot.models.Account;
import com.github.loickcherimont.debiting_springboot.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(UUID accountId) {
        return  accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("ID de compte inexistant. Essayez un autre."));
    }

    public Account debitAccountById(UUID accountId, DebitAmountDto debitAmountDto) {
        Account accountToDebit = getAccountById(accountId);
        BigInteger sanitizedDebitAmount = BigInteger.valueOf(debitAmountDto.value());

        BigInteger newValue = (accountToDebit.getAmountEntity().getAmount()).subtract(sanitizedDebitAmount);

        accountToDebit.getAmountEntity().setValue(newValue);

        accountToDebit.setAmountEntity(accountToDebit.getAmountEntity());
        return accountToDebit;
    }

}
