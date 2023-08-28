package com.mintech.vendingmachine.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vending_machine")
@Getter
@Setter
public class VendingMachine {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "provider")
    private String provider;

    @Column(name = "row_count")
    private int rowCount;

    @Column(name = "column_count")
    private int columnCount;

    @OneToMany(mappedBy = "vendingMachine", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<VendingMachineCell> vendingMachineCells;


}

