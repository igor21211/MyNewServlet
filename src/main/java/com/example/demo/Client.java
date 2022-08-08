package com.example.demo;

public class Client {

    private int id;
    private String name;
    private String coffe;
    private int price;

    private int iniqid;
    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCoffe(String coffe) {
        this.coffe = coffe;
    }


    public String getCoffe() {
        return coffe;
    }

    public int getIniqid() {
        return iniqid;
    }

    public void setIniqid(int iniqid) {
        this.iniqid = iniqid;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", coffe='" + coffe + '\'' +
                ", price=" + price +
                '}';
    }
}
