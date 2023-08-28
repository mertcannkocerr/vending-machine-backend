package com.mintech.vendingmachine.services.interfaces;

import com.mintech.vendingmachine.models.VendingMachine;
import com.mintech.vendingmachine.models.VendingMachineCell;

import java.util.List;
import java.util.Optional;

public interface IVendingMachineService {
    List<VendingMachine> getAllVendingMachines();

    List<VendingMachineCell> getVendingMachineCellsByVendingMachineId(Long machineId);

    Optional<VendingMachine> getVendingMachineById(Long machineId);

    List<VendingMachineCell> buyProduct(Long machineId, Long userId, int row, int column);
}
