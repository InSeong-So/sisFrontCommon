package biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public interface MainAction
{
    public static Logger log = Logger.getRootLogger();
    
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
