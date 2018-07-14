package com.thoughtworks.tdd;

import java.util.UUID;

public class Receipt {
    public String id;
    public Receipt(){
        id=UUID.randomUUID().toString();
    }
}
