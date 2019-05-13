package com.example.ngensdroid;

public class OrderTaken {
    private int id;
    private String title;
    private String description;
    private String tags;
    private String price;
    private String image;
    private int id_user;
    private String contact;
    private String status;
    private int id_technician;

    public OrderTaken(int id, String title, String description, String tags, String price, String image, int id_user,String contact,String status,int id_technician) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.price = price;
        this.image = image;
        this.id_user = id_user;
        this.contact = contact;
        this.status = status;
        this.id_technician = id_technician;
    }

    public int getId() { return id; }

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

    public String getContact() { return contact; }

    public String getStatus() { return status; }

    public int getId_technician() { return id_technician; }
}
