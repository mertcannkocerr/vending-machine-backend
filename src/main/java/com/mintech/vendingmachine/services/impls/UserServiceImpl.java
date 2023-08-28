package com.mintech.vendingmachine.services.impls;

import com.mintech.vendingmachine.models.User;
import com.mintech.vendingmachine.repositories.interfaces.IUserRepository;
import com.mintech.vendingmachine.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public User loadMoneyToBalance(long userId, double amount) {
        Optional<User> userWrapper = userRepository.findById(userId);

        if (userWrapper.isEmpty()){
            return null;
        }

        User user = userWrapper.get();

        double currentBalance = user.getBalance();
        double expectedBalance = currentBalance + amount;

        user.setBalance(expectedBalance);
        userRepository.save(user);

        return user;
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return userRepository.findById(userId);
    }
}
