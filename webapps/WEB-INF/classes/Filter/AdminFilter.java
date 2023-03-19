package Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Log Filter Initialization");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //check if user is logged in
        var session = ((HttpServletRequest) servletRequest).getSession(false);
        if (session == null || session.getAttribute("user") == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("ADMIN")) {
            servletRequest.getRequestDispatcher("/Views/Login.jsp").forward(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Log Filter Destruction");
    }
}
