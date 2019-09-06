package biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.user.UserDAO;

public class UserService implements MainAction
{
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        int loginYn = UserDAO.getInstance().login();
        request.setAttribute("loginYn", loginYn);
        return "main.jsp";
    }
}
