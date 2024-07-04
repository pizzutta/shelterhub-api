package com.elc1090.shelterhubapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "tb_transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    @Column
    private ActionsEnum action;
    @Column
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "item_shelter_id")
    private ItemShelter itemShelter;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
