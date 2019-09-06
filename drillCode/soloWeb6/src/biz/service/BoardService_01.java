package biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;

public class BoardService_01 implements MainAction
{
    
    public void mappingUrl(HttpServletRequest request, HttpServletResponse response)
    {
        
    }
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String path = "";
        
        return sisAction(request, response, path);
    }
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response, String path) throws Throwable
    {
        return null;
    }
    
}
