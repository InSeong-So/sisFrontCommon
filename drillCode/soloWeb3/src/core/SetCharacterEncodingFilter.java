package core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class SetCharacterEncodingFilter implements Filter
{
    private Logger log = Logger.getRootLogger();
    
    //인코딩을 수행할 인코딩 캐릭터 셋 지정
    String encoding;
    
    //필터 설정 관리자
    FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        log.debug("SetCharacterEncodingFilter >>>>>>>>>>>>>>>>>>>>>>>>> init");
        //초기화
        //getInitParameter() : web.xml에 초기화해서 지정한 파라미터 값을 불러오는 메소드 
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        log.debug("SetCharacterEncodingFilter >>>>>>>>>>>>>>>>>>>>>>>>> doFilter");
        
        if (request.getCharacterEncoding() == null)
        {
            if (encoding != null)
                request.setCharacterEncoding(encoding);
        }
        
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy()
    {
        log.debug("SetCharacterEncodingFilter >>>>>>>>>>>>>>>>>>>>>>>>> destroy");
        
        this.encoding = null;
        this.filterConfig = null;
    }
}
