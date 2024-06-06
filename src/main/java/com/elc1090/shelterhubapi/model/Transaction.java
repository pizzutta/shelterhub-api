package com.elc1090.shelterhubapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "tb_transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    @Column
    private ActionsEnum action;
    @Column
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "item_shelter_id")
    private ItemShelter itemShelter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ActionsEnum getAction() {
        return action;
    }

    public void setAction(ActionsEnum action) {
        this.action = action;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ItemShelter getItemShelter() {
        return itemShelter;
    }

    public void setItemShelter(ItemShelter itemShelter) {
        this.itemShelter = itemShelter;
    }
}
