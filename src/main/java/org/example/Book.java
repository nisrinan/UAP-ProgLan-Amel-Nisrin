package org.example;

class Book {
    private String id;
    private String title;
    private String author;
    private double price;
    private int stock;

    public Book(String id, String title, String author, double price, int stock) {
        this.id = id;
        this.title = title;
        this.author = author; // Memperbaiki inisialisasi author
        this.price = price;
        this.stock = stock;
    }

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}