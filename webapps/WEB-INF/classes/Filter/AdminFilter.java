package Filter;

import Controllers.Admin;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.logging.Logger;

public class AdminFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AdminFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Admin Filter Initialization");
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
        logger.info("Admin Filter Destruction");
    }
}
