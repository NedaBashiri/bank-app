package controller.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

public final class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        Cookie[] cookies = req.getCookies();
    /*    if (session != null && cookies.length == 4) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/Login.jsp");
        }*/

        if (cookies != null && Arrays.stream(cookies)
                .anyMatch(cookie -> "isUserLogin".equals(cookie.getName()) && "true".equals(cookie.getValue()))) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/Login.jsp");
        }


    }

}

