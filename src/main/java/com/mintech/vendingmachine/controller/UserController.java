package com.mintech.vendingmachine.controller;

import com.mintech.vendingmachine.DTOs.LoadMoneyDTO;
import com.mintech.vendingmachine.DTOs.VendingMachineSpecificationDTO;
import com.mintech.vendingmachine.models.User;
import com.mintech.vendingmachine.models.VendingMachine;
import com.mintech.vendingmachine.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/{userId}")
    private ResponseEntity<User> getUserById(@PathVariable long userId) {
        Optional<User> user = userService.getUserById(userId);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping("/{userId}/load-money")
    private ResponseEntity<User> loadMoneyToBalance(@PathVariable long userId, @RequestBody LoadMoneyDTO loadMoneyDTO) {
        Optional<User> userWrapper = Optional.ofNullable(userService.loadMoneyToBalance(userId, loadMoneyDTO.getAmount()));

        return userWrapper.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
