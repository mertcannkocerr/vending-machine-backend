package com.mintech.vendingmachine.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vending_machine_cell")
@Getter
@Setter
public class VendingMachineCell {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "row_indice")
    private int rowIndice;

    @Column(name = "column_indice")
    private int columnIndice;

    @ManyToOne
    @JoinColumn(name = "vending_machine_id")
    @JsonBackReference
    private VendingMachine vendingMachine;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Column(name = "product_count")
    private int productCount;

}
