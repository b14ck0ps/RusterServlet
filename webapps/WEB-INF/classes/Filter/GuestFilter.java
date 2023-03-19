package Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class GuestFilter implements Filter {
    private static final Logger logger = Logger.getLogger(GuestFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Guest Filter Initialization");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //check if user is a guest (i.e. not logged in)
        var session = ((HttpServletRequest) servletRequest).getSession(false);
        if (session == null || session.getAttribute("user") != null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            assert session != null;
            if (session.getAttribute("userType") == "ADMIN") {
                response.sendRedirect(servletRequest.getServletContext().getContextPath() + "/Admin");
            } else {
                response.sendRedirect(servletRequest.getServletContext().getContextPath() + "/Home");
            }
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("Guest Filter Destruction");
    }
}
