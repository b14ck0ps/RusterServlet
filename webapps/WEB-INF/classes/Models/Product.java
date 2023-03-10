package Models;

public class Product {
    private int id;
    private String name;
    private int categoryId;
    private int quantity;
    private double price;
    private String image;

    public Product() {
    }

    public Product(String name, int categoryId, int quantity, double price, String image) {
        this.name = name;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public Product(int id, String name, int categoryId, int quantity, double price, String image) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
