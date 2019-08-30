package core.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class EncodingFilter implements Filter
{
    //인코딩을 수행할 인코딩 캐릭터 셋 지정
    static final String encoding = "UTF-8";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        if (request.getCharacterEncoding() == null)
            request.setCharacterEncoding(encoding);
        
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy()
    {
    }
}
