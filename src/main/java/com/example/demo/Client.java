package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"id" ,"isDeleted"})
public class Client {

    private int id;
    private String name;
    private String coffe;
    private int price;
    private boolean isDeleted;
    private int iniqid;

}
