package com.mintech.vendingmachine.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyProductDTO {
    private int row;
    private int column;
    private Long userId;
}
