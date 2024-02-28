package org.example.l11_sd_intro.services;

import org.example.l11_sd_intro.models.Account;
import org.example.l11_sd_intro.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.l11_sd_intro.repositories.AccountRepository;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Account createAccount(BigDecimal initialBalance, User user) {
        Account account = new Account();
        account.setBalance(initialBalance);
        account.setUser(user);
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void withdraw(Long accountId, BigDecimal amount) throws Exception {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new Exception("Account not found."));
        BigDecimal newBalance = account.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Insufficient balance.");
        }

        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deposit(Long accountId, BigDecimal amount) throws Exception {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new Exception("Account not found."));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void transfer(Long accountIdFrom, Long accountIdTo, BigDecimal amount) throws Exception {
        this.withdraw(accountIdFrom, amount);
        this.deposit(accountIdTo, amount);
    }
}
