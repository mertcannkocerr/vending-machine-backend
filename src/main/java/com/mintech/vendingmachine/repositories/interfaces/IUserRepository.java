package com.mintech.vendingmachine.repositories.interfaces;

import com.mintech.vendingmachine.models.User;
import com.mintech.vendingmachine.models.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository  extends JpaRepository<User, Long> {

}
