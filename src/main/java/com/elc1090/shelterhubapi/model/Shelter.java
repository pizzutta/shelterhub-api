package com.elc1090.shelterhubapi.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_shelter")
public class Shelter {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @OneToOne
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shelter_id")
    private List<ItemShelter> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ItemShelter> getItems() {
        return items;
    }

    public void setItems(List<ItemShelter> items) {
        this.items = items;
    }
}
