package biz.domain.user;

public class User
{
    private int seqNo;
    
    private String userId;
    
    private String userName;
    
    private String userPassword;
    
    private String phoneNumber;
    
    private String email;
    
    private String zipNo;
    
    private String address;
    
    private String dtlAddress;
    
    private String birthday;
    
    private String regDate;
    
    private int loginFailCount;
    
    public int getSeqNo()
    {
        return seqNo;
    }
    
    public void setSeqNo(int seqNo)
    {
        this.seqNo = seqNo;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getUserPassword()
    {
        return userPassword;
    }
    
    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getZipNo()
    {
        return zipNo;
    }
    
    public void setZipNo(String zipNo)
    {
        this.zipNo = zipNo;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getDtlAddress()
    {
        return dtlAddress;
    }
    
    public void setDtlAddress(String dtlAddress)
    {
        this.dtlAddress = dtlAddress;
    }
    
    public String getBirthday()
    {
        return birthday;
    }
    
    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }
    
    public String getRegDate()
    {
        return regDate;
    }
    
    public void setRegDate(String regDate)
    {
        this.regDate = regDate;
    }
    
    public int getLoginFailCount()
    {
        return loginFailCount;
    }
    
    public void setLoginFailCount(int loginFailCount)
    {
        this.loginFailCount = loginFailCount;
    }
    
}
