package Database;

public class Payments {
    private String Key;
    private String UserID;
    private String Name;
    private int PhoneNo;
    private String RoomType;
    private double PayAmount;
    private String Bank;
    private String PayDate;
    private String Status;

    public Payments() {
    }

    public Payments(String key, String userID, String name, int phoneNo, String roomType, double payAmount, String bank, String payDate,String status) {
        Key = key;
        UserID = userID;
        Name = name;
        PhoneNo = phoneNo;
        RoomType = roomType;
        PayAmount = payAmount;
        Bank = bank;
        PayDate = payDate;
        Status =status;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public double getPayAmount() {
        return PayAmount;
    }

    public void setPayAmount(double payAmount) {
        PayAmount = payAmount;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getPayDate() {
        return PayDate;
    }

    public void setPayDate(String payDate) {
        PayDate = payDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
