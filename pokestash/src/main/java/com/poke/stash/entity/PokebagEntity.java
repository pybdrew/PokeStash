package com.poke.stash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class PokebagEntity
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BAG_ID")
    private int bagId;

    @Column(name = "users_ID")
    private int userId;

    // ---- Getters and Setters ----
    public int getBagId()
    {
        return bagId;
    }

    public void setBagId(int bagId)
    {
        this.bagId = bagId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }
}
