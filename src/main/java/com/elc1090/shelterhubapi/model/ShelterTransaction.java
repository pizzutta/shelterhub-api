package com.elc1090.shelterhubapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "tb_shelter_transaction")
public class ShelterTransaction {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private LocalDateTime date;
    @Column
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "giver_shelter_id")
    private Shelter giverShelter;
    @ManyToOne
    @JoinColumn(name = "receiver_shelter_id")
    private Shelter receiverShelter;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Shelter getGiverShelter() {
        return giverShelter;
    }

    public void setGiverShelter(Shelter giverShelter) {
        this.giverShelter = giverShelter;
    }

    public Shelter getReceiverShelter() {
        return receiverShelter;
    }

    public void setReceiverShelter(Shelter receiverShelter) {
        this.receiverShelter = receiverShelter;
    }
}
