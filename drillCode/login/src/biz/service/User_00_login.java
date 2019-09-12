package biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.User;
import core.db.ClearStatement;
import core.db.SQLUtil;
import core.db.SisResultSet;
import core.util.SisRequiredClass;

public class User_00_login extends SisRequiredClass implements MainAction
{
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String query = xmlParsingQuery.getElement(this, "login", null);
        ClearStatement cstmt = new ClearStatement(conn, query);
        
        cstmt.setParameter("USER_ID", request.getParameter("userId"));
        cstmt.setParameter("USER_PASSWORD", request.getParameter("userPassword"));
        
        log.debug(cstmt.getQueryString());
        
        SisResultSet srs = SQLUtil.getResultSetWithClose(cstmt);
        
        if (!srs.next())
        {
            return null;
        }
        
        return "login.jsp";
    }
    
}
