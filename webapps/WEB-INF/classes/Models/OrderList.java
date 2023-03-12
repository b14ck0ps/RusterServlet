package Models;

public class OrderList extends CartProduct {
    private int orderId;
    private String orderDate;


    public OrderList() {
    }

    public OrderList(int id, String productName, int quantity, double price, String image, int orderId, String orderDate) {
        super(id, productName, quantity, price, image);
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

}
