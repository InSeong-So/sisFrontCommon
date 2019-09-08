package biz.domain;

import java.sql.Timestamp;

public class User
{
    private int seqNo;
    
    private String userId;
    
    private String userPassword;
    
    private String userName;
    
    private String userPhoneNumber;
    
    private String userEmail;
    
    private String zipNo;
    
    private String address;
    
    private String dtlAddress;
    
    private String userBirthday;
    
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
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getUserPhoneNumber()
    {
        return userPhoneNumber;
    }
    
    public void setUserPhoneNumber(String userPhoneNumber)
    {
        this.userPhoneNumber = userPhoneNumber;
    }
    
    public String getUserEmail()
    {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
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
    
    public String getUserBirthday()
    {
        return userBirthday;
    }
    
    public void setUserBirthday(String userBirthday)
    {
        this.userBirthday = userBirthday;
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
    
    @Override
    public String toString()
    {
        return "User [seqNo=" + seqNo + ", userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName + ", userPhoneNumber=" + userPhoneNumber + ", userEmail=" + userEmail + ", zipNo=" + zipNo + ", address=" + address + ", dtlAddress=" + dtlAddress + ", userBirthday="
                + userBirthday + ", regDate=" + regDate + ", loginFailCount=" + loginFailCount + "]";
    }
    
}
