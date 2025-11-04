package com.poke.stash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class ItemEntity
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private int itemId;
    
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private int price;

    // ---- Getters and Setters ----
    public int getItemId()
    {
        return itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }    
}
