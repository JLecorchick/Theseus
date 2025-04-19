package projfxmldemo.models;

public class User {
    private int userId;
    private String username;
    private String phoneNum;
    private String aptName;
    private String aptNum;
    private String inviteCode;

    // GETTERS & SETTERS

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

    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAptName() {
        return aptName;
    }
    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    public String getAptNum() {
        return aptNum;
    }
    public void setAptNum(String aptNum) {
        this.aptNum = aptNum;
    }

    public String getInviteCode() {
        return inviteCode;
    }
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
