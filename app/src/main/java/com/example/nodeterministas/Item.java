package com.example.nodeterministas;


public class Item {
    String id;
    String description;

    //public Item(String valueOf, String s) {
    //}

    public Item(String pID, String pDesc) {
        id = pID;
        description = pDesc;
    }

    public long getID() {
        return Integer.valueOf(id);
    }

    public String getDescription() {
        return description;
    }



}
