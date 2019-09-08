package biz.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.User;
import core.util.SisRequiredClass;

public class User_01_resistration extends SisRequiredClass implements MainAction
{
    public User_01_resistration(Connection conn, HttpServletRequest request, HttpServletResponse response)
    {
        super(conn, request, response);
    }
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");
        String userName = request.getParameter("userName");
        String userPhoneNumber = request.getParameter("userPhoneNumber");
        String userEmail = request.getParameter("userEmail");
        String userBirthday = request.getParameter("userBirthday");
        
        User user = new User();
        
        user.setUserId(userId);
        user.setUserPassword(userPassword);
        user.setUserName(userName);
        user.setUserPhoneNumber(userPhoneNumber);
        user.setUserEmail(userEmail);
        user.setUserBirthday(userBirthday);
        
        log.debug("User Information : " + user.toString());
        
        return null;
    }
    
}
