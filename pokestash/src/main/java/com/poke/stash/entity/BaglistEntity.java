package com.poke.stash.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column(name="quantity")
    private int quantity = 1;

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
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    { 
        this.quantity = quantity;
    }
}
