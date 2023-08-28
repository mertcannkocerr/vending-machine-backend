package com.mintech.vendingmachine.services.impls;

import com.mintech.vendingmachine.models.User;
import com.mintech.vendingmachine.models.VendingMachine;
import com.mintech.vendingmachine.models.VendingMachineCell;
import com.mintech.vendingmachine.repositories.interfaces.IUserRepository;
import com.mintech.vendingmachine.repositories.interfaces.IVendingMachineRepository;
import com.mintech.vendingmachine.services.interfaces.IVendingMachineService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendingMachineServiceImpl implements IVendingMachineService {

    private final IUserRepository userRepository;
    private final IVendingMachineRepository vendingMachineRepository;

    @Override
    public List<VendingMachine> getAllVendingMachines() {
        return vendingMachineRepository.findAll();
    }

    @Override
    public Optional<VendingMachine> getVendingMachineById(Long id) {
        return vendingMachineRepository.findById(id);
    }

    @Override
    public List<VendingMachineCell> buyProduct(Long machineId, Long userId, int row, int column) {
        Optional<User> userWrapper = userRepository.findById(userId);
        Optional<VendingMachine> vendingMachineWrapper = vendingMachineRepository.findById(machineId);

        if (userWrapper.isEmpty()) {
            return null;
        }

        if (vendingMachineWrapper.isEmpty()) {
            return null;
        }

        User user = userWrapper.get();
        VendingMachine vendingMachine = vendingMachineWrapper.get();
        List<VendingMachineCell> vendingMachineCells = vendingMachine.getVendingMachineCells();

        VendingMachineCell selectedVendingMachineCell = vendingMachineCells.stream().filter(c -> c.getColumnIndice() == column && c.getRowIndice() == row).findFirst().orElseThrow(EntityNotFoundException::new);

        double currentBalance = userWrapper.get().getBalance();
        int requestedProductCount = selectedVendingMachineCell.getProductCount();
        double requestedProductPrice = selectedVendingMachineCell.getProduct().getPrice();

        if (requestedProductPrice > currentBalance){
            throw new Error("You don't have enough money.");
        }

        if (requestedProductCount < 1){
            throw new Error("Unfortunately run out of product.");
        }

        selectedVendingMachineCell.setProductCount(selectedVendingMachineCell.getProductCount() - 1);
        vendingMachine.setVendingMachineCells(vendingMachineCells);
        user.setBalance(user.getBalance() - requestedProductPrice);
        vendingMachineRepository.save(vendingMachine);
        userRepository.save(user);

        return vendingMachineCells;
    }

    @Override
    public List<VendingMachineCell> getVendingMachineCellsByVendingMachineId(Long machineId) {
        Optional<VendingMachine> vendingMachineWrapper = vendingMachineRepository.findById(machineId);

        if (vendingMachineWrapper.isEmpty()) {
            return null;
        }

        VendingMachine vendingMachine = vendingMachineWrapper.get();
        return vendingMachine.getVendingMachineCells();
    }

}
