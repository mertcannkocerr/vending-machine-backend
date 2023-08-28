package com.mintech.vendingmachine.repositories.interfaces;

import com.mintech.vendingmachine.models.VendingMachine;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IVendingMachineRepository extends JpaRepository<VendingMachine, Long> {
}
