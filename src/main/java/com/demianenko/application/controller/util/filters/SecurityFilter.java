package com.demianenko.application.controller.util.filters;

import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.SecurityConstraints;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.controller.util.constants.SessionParameters;
import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class SecurityFilter implements Filter{

    private final static Logger LOGGER = Logger.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Initialising security filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        User user = validateUser(httpServletRequest);
        String commandName = httpServletRequest.getParameter(SessionParameters.COMMAND);
        Role role = user != null?user.getRole():null;
        if(commandName != null){
            if(!SecurityConstraints.getInstance().isAllowed(commandName, role)){
                if(user == null){
                    httpServletRequest.getRequestDispatcher(Pages.ACCESS_DENIED)
                            .forward(servletRequest,servletResponse);
                    return;
                } else {
                    httpServletRequest.getRequestDispatcher(Pages.ACCESS_DENIED)
                            .forward(servletRequest,servletResponse);
                    return;
                }

            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.debug("Destroying security filter");
    }

    private User validateUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionParameters.USER);
        if(user == null){
            Cookie[] cookies = request.getCookies();
            if(cookies.length >= 2) {
                LOGGER.debug("Cookies " + cookies[0].getValue() + " " + cookies[1].getValue());
                user = ServiceFactory.getInstance().getAuthenticationService()
                        .validate(cookies[0].getValue(), cookies[1].getValue());
            }
        } else {
            user = ServiceFactory.getInstance().getAuthenticationService()
                    .validate(user.getEmail(),user.getPassword());
            if(user == null){
                LOGGER.debug("User validation failed");
                ServiceFactory.getInstance().getAuthenticationService().logout(session);
            }
        }
        if(user != null){
            session.setAttribute(SessionParameters.USER, user);
            LOGGER.debug("User validation completed");
        }
        return user;
    }
}
