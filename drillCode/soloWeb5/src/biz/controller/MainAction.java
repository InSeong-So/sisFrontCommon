package biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import core.db.SisResultSet;
import core.db.XMLParsingQuery;
import core.util.CommonProperties;

public interface MainAction
{
    CommonProperties prop = CommonProperties.getInstance();
    
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable;
    
}
