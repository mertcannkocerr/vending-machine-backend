package com.mintech.vendingmachine.DTOs;

import com.mintech.vendingmachine.models.VendingMachine;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VendingMachineSpecificationDTO {
    private long id;
    private String provider;
    private int rowCount;
    private int columnCount;

    public static VendingMachineSpecificationDTO convertVendingMachineToVendingMachineSpecificationDTO(VendingMachine vendingMachine) {
        return VendingMachineSpecificationDTO
                .builder()
                .id(vendingMachine.getId())
                .columnCount(vendingMachine.getColumnCount())
                .rowCount(vendingMachine.getRowCount())
                .provider(vendingMachine.getProvider())
                .build();
    }
}
