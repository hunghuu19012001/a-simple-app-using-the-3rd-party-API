package com.example.CodingAssignment;

import com.example.CodingAssignment.entity.Consumer;
import com.example.CodingAssignment.entity.Merchant;
import com.example.CodingAssignment.entity.Shipping;
import com.example.CodingAssignment.entity.TotalAmount;
import lombok.Data;

@Data
public class Order {
    //private String product;
    private TotalAmount totalAmount;
    private Consumer consumer;
    private Merchant merchant;
    private Shipping shipping;

}
