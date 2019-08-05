package biz.user.domain;

public class User
{
    private String userId;
    private String userPassword;
    private String userName;
    private String userGender;
    private String userEmail;
    private String userBirthday;
    private String registerYmd;
    private String lastLoginYmd;
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getUserPassword()
    {
        return userPassword;
    }
    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getUserGender()
    {
        return userGender;
    }
    public void setUserGender(String userGender)
    {
        this.userGender = userGender;
    }
    public String getUserEmail()
    {
        return userEmail;
    }
    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }
    public String getUserBirthday()
    {
        return userBirthday;
    }
    public void setUserBirthday(String userBirthday)
    {
        this.userBirthday = userBirthday;
    }
    public String getRegisterYmd()
    {
        return registerYmd;
    }
    public void setRegisterYmd(String registerYmd)
    {
        this.registerYmd = registerYmd;
    }
    public String getLastLoginYmd()
    {
        return lastLoginYmd;
    }
    public void setLastLoginYmd(String lastLoginYmd)
    {
        this.lastLoginYmd = lastLoginYmd;
    }

    
}
