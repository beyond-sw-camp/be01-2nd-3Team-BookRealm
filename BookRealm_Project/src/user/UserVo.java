package user;

public class UserVo {

    public final String ClassName = "User";
    private int userId;
    private String username;
    private String passwd;
    private String address;
    private String phone;
    private String suType;
    private int adminyn;


    public UserVo() {
    }

    public UserVo(int userId, String username, String passwd, String address, String phone, String suType, int adminyn) {
        this.userId = userId;
        this.username = username;
        this.passwd = passwd;
        this.address = address;
        this.phone = phone;
        this.suType = suType;
        this.adminyn = adminyn;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSuType() {
        return suType;
    }

    public void setSuType(String suType) {
        this.suType = suType;
    }

    public int getAdminyn() {
        return adminyn;
    }

    public void setAdminyn(int adminyn) {
        this.adminyn = adminyn;
    }
}
