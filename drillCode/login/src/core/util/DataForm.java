package core.util;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class DataForm
{
    Map<String, String[]> dataMap;
    
    MultipartRequest multiForm;
    
    HttpServletRequest request;
    
    HttpServletResponse response;
    
    public DataForm(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        this.dataMap = request.getParameterMap();
    }
    
    public Map<String, String[]> getDataMap()
    {
        return dataMap;
    }
    
    public void setDataMap(Map<String, String[]> dataMap)
    {
        this.dataMap = dataMap;
    }
    
    public MultipartRequest getMultiForm()
    {
        return multiForm;
    }
    
    public void setMultiForm(MultipartRequest multiForm)
    {
        this.multiForm = multiForm;
    }
    
    public Enumeration getParameterNames()
    {
        return this.multiForm != null ? this.multiForm.getParameterNames() : this.request.getParameterNames();
    }
    
    public String[] getParameterValues(String arg0)
    {
        return this.multiForm != null ? this.multiForm.getParameterValues(arg0) : this.request.getParameterValues(arg0);
    }
    
    public HttpServletRequest getRequest()
    {
        return this.request;
    }
    
    public HttpServletResponse getResponse()
    {
        return this.response;
    }
    
    public String getValue(String s)
    {
        return this.multiForm != null ? this.multiForm.getParameter(s) : this.request.getParameter(s);
    }
    
    public String[] getValues(String s)
    {
        return this.multiForm != null ? this.multiForm.getParameterValues(s) : (String[]) this.dataMap.get(s);
    }
    
    public String getParameter(String s)
    {
        return this.multiForm != null ? this.multiForm.getParameter(s) : this.request.getParameter(s);
    }
    
    public String getParameter(String s, String d)
    {
        return StringUtil.nvl(getParameter(s), d);
    }
}
