package com.poke.stash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="baglist")
public class BaglistEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LIST_ID")
    private int listId;

    @Column(name="pokebags_BAG_ID")
    private int pokebagId;

    @Column(name="pokeitems_ITEM_ID")
    private int pokeitemId;

    // ---- Getters and Setters ----
    public int getListId()
    { 
        return listId;
    }
    public void setListId(int listId)
    { 
        this.listId = listId;
    }

    public int getPokebagId()
    { 
        return pokebagId;
    }
    public void setPokebagId(int pokebagId)
    { 
        this.pokebagId = pokebagId;
    }

    public int getPokeitemId()
    { 
        return pokeitemId;
    }
    public void setPokeitemId(int pokeitemId)
    { 
        this.pokeitemId = pokeitemId;
    }
}
