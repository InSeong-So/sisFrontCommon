package biz.domain;

import java.sql.Timestamp;

public class User
{
    private int seqNo;
    
    private String userId;
    
    private String userPassword;
    
    private String phoneNumber;
    
    private String email;
    
    private String zipNo;
    
    private String address;
    
    private String dtlAddress;
    
    private Timestamp birthday;
    
    private Timestamp regDate;
    
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
    
    public Timestamp getBirthday()
    {
        return birthday;
    }
    
    public void setBirthday(Timestamp birthday)
    {
        this.birthday = birthday;
    }
    
    public Timestamp getRegDate()
    {
        return regDate;
    }
    
    public void setRegDate(Timestamp regDate)
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
