package Models;

import java.sql.Date;

public class Order {
    private int id;
    private int userId;
    private double totalPrice;
    private Date date;

    public Order(int userId, double totalPrice, Date date) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public Order(int id, int userId, double totalPrice, Date date) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
