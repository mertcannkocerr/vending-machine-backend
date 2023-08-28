package com.mintech.vendingmachine.services.interfaces;

import com.mintech.vendingmachine.models.User;

import java.util.Optional;

public interface IUserService {
    User loadMoneyToBalance(long userId, double amount);

    Optional<User> getUserById(long userId);
}
