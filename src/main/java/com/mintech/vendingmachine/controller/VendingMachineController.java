package com.mintech.vendingmachine.controller;

import com.mintech.vendingmachine.DTOs.BuyProductDTO;
import com.mintech.vendingmachine.DTOs.VendingMachineSpecificationDTO;
import com.mintech.vendingmachine.models.VendingMachine;
import com.mintech.vendingmachine.models.VendingMachineCell;
import com.mintech.vendingmachine.services.interfaces.IVendingMachineService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vending-machine")
@RequiredArgsConstructor
public class VendingMachineController {

    private final IVendingMachineService vendingMachineService;

    @GetMapping("/spec")
    private ResponseEntity<List<VendingMachineSpecificationDTO>> getAllVendingMachineSpecifications() {
        return ResponseEntity.ok(
                vendingMachineService.getAllVendingMachines()
                        .stream()
                        .map(VendingMachineSpecificationDTO::convertVendingMachineToVendingMachineSpecificationDTO)
                        .toList()
        );
    }

    @GetMapping("/{machineId}/spec")
    public ResponseEntity<VendingMachineSpecificationDTO> getVendingMachineSpecificationsById(@PathVariable long machineId) {
        Optional<VendingMachine> vendingMachineOptional = vendingMachineService.getVendingMachineById(machineId);

        if (vendingMachineOptional.isPresent()) {
            VendingMachineSpecificationDTO specification = VendingMachineSpecificationDTO.convertVendingMachineToVendingMachineSpecificationDTO(vendingMachineOptional.get());
            return ResponseEntity.ok(specification);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{machineId}")
    public ResponseEntity<VendingMachine> getVendingMachineById(@PathVariable Long machineId) {
        Optional<VendingMachine> vendingMachineOptional = vendingMachineService.getVendingMachineById(machineId);

        return vendingMachineOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{machineId}/cells")
    public ResponseEntity<List<VendingMachineCell>> getVendingMachineCellsByVendingMachineId(@PathVariable Long machineId) {
        List<VendingMachineCell> vendingMachineCells = vendingMachineService.getVendingMachineCellsByVendingMachineId(machineId);
        return ResponseEntity.ok(vendingMachineCells);
    }

    @PostMapping("/{machineId}/buy")
    public ResponseEntity<List<VendingMachineCell>> buyProduct(@PathVariable Long machineId, @RequestBody BuyProductDTO buyProductDTO) {
        List<VendingMachineCell> vendingMachineCells = vendingMachineService.buyProduct(machineId, buyProductDTO.getUserId(), buyProductDTO.getRow(), buyProductDTO.getColumn());
        return ResponseEntity.ok(vendingMachineCells);
    }

}


