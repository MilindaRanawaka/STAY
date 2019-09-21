package Database;

public class Order {

    private String orderID;
    private String Name;
    private String RNo;
    private String bgenre;
    private String ApprovalState;
    private String userKey;

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Order() {
    }

    public Order(String orderID, String name, String RNo, String bgenre, String approvalState, String userKey) {
        this.orderID = orderID;
        Name = name;
        this.RNo = RNo;
        this.bgenre = bgenre;
        ApprovalState = approvalState;
        this.userKey = userKey;
    }

    public String getApprovalState() {
        return ApprovalState;
    }

    public void setApprovalState(String approvalState) {
        ApprovalState = approvalState;
    }

    //Getters

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

//Setters

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