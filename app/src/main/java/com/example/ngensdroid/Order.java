package com.example.ngensdroid;

public class Order {
    private int id;
    private String title;
    private String description;
    private String tags;
    private String price;
    private String image;
    private int id_user;
    private String contact;

    public Order(int id, String title, String description, String tags, String price, String image, int id_user,String contact) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.price = price;
        this.image = image;
        this.id_user = id_user;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTags() {
        return tags;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public int getId_user() {
        return id_user;
    }

    public String getContact() {
        return contact;
    }
}
