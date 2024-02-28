package org.example.l11_sd_intro.services;

import org.example.l11_sd_intro.models.Account;
import org.example.l11_sd_intro.models.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AccountService {
    Account createAccount(BigDecimal initialBalance, User user);

    void withdraw(Long accountId, BigDecimal amount) throws Exception;
    void deposit(Long accountId, BigDecimal amount) throws Exception;
    void transfer(Long accountIdFrom, Long accountIdTo, BigDecimal amount) throws Exception;
}
