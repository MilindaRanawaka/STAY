package Database;

public class Order {
    String orderID;
    String Name;
    String RNo;
    String bgenre;

    public Order() {
    }

    public Order(String orderID, String name, String RNo, String bgenre) {
        this.orderID = orderID;
        this.Name = name;
        this.RNo = RNo;
        this.bgenre = bgenre;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getName() {
        return Name;
    }

    public String getRNo() {
        return RNo;
    }

    public String getBgenre() {
        return bgenre;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setRNo(String RNo) {
        this.RNo = RNo;
    }

    public void setBgenre(String bgenre) {
        this.bgenre = bgenre;
    }
}