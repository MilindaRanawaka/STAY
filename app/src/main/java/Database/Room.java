package Database;

public class Room {

    private String key;
    private String roomID;
    private int capacity;
    private double price;
    private String acType;

    public Room() {
    }

    public Room(String key, String roomID, int capacity, double price, String acType) {
        this.key = key;
        this.roomID = roomID;
        this.capacity = capacity;
        this.price = price;
        this.acType = acType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }
}
