package biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MainAction
{
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable;
    
}
