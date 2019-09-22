package Database;

public class Admin {

    private String key;
    private String name;
    private String dob;
    private String address;
    private String nic;
    private String gender;
    private Long phNo;
    private String email;
    private String password;

    public Admin() {
    }

    public Admin(String key, String name, String dob, String address, String nic, String gender, Long phNo, String email, String password) {
        this.key = key;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.nic = nic;
        this.gender = gender;
        this.phNo = phNo;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPhNo() {
        return phNo;
    }

    public void setPhNo(Long phNo) {
        this.phNo = phNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
