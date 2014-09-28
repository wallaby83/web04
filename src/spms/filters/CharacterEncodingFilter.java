package spms.filters;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;


@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class CharacterEncodingFilter implements Filter
{
    FilterConfig config;


    @Override
    public void init(FilterConfig config) throws ServletException
    {
        this.config = config;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("###################################");
        System.out.println("필터 실행 전");
        System.out.println(req.getRequestURI());
        System.out.println("###################################");

        request.setCharacterEncoding(config.getInitParameter("encoding"));
        nextFilter.doFilter(request, response);

        System.out.println("###################################");
        System.out.println("필터 실행 후");
        System.out.println(request.getServerName());
        System.out.println("###################################");
    }


    @Override
    public void destroy()
    {
    }
}
