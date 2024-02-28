package org.example.l11_sd_intro;

import org.example.l11_sd_intro.models.Account;
import org.example.l11_sd_intro.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.example.l11_sd_intro.services.AccountService;
import org.example.l11_sd_intro.services.UserService;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;


    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = userService.registerUser("john_doe", 30);
        Account account = accountService.createAccount(new BigDecimal("1000"), user);

        accountService.deposit(account.getId(), new BigDecimal("500"));
        accountService.withdraw(account.getId(), new BigDecimal("200"));

        System.out.println("User: " + user.getUsername() + ", Age: " + user.getAge());
        System.out.println("Account Balance: " + account.getBalance());
    }
}
