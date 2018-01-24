package com.demianenko.application.controller.util.filters;

import com.demianenko.application.controller.command.CommandList;
import com.demianenko.application.controller.util.SecurityConstraints;
import com.demianenko.application.controller.util.constants.SessionParameters;
import com.demianenko.application.controller.util.converters.Sha256Encoder;
import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SecurityFilterTest {

    private final static Logger LOGGER = Logger.getLogger(SecurityFilterTest.class);
    private final static String ALLOWED = "allowed";
    private final static String BLOCKED = "blocked";

    private SecurityFilter securityFilter;

    private User user;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private ServletRequest servletRequest;

    @Mock
    private ServletResponse servletResponse;

    @Mock
    private FilterChain filterChain;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        securityFilter = new SecurityFilter();
        servletRequest = mock(ServletRequest.class, withSettings().extraInterfaces(HttpServletRequest.class));
        given(servletRequest.getRequestDispatcher(any())).willReturn(requestDispatcher);
        doThrow(new RuntimeException(BLOCKED)).when(requestDispatcher).forward(any(),any());
        doThrow(new RuntimeException(ALLOWED)).when(filterChain).doFilter(any(), any());
        httpServletRequest = (HttpServletRequest)servletRequest;
        //given((HttpServletRequest)servletRequest).willReturn(httpServletRequest);
        given(httpServletRequest.getSession()).willReturn(session);
        doNothing().when(session).setAttribute(any(),any());
        doNothing().when(session).invalidate();

        user = new User();
        user.setFirstName("testName");
        user.setSecondName("testSecondName");
        user.setEmail("testEmail");
        user.setPassword(new Sha256Encoder().encode("testPassword"));
        user.setRole(Role.USER);
        user.setId(MySqlDaoFactory.getInstance().getUserDao().add(user));

    }

    @After
    public void tearDown() throws Exception {
        MySqlDaoFactory.getInstance().getUserDao().delete(user.getId());
    }

    /**
     * This test represents situation when user trying to access the admin page
     *
     */
    @Test
    public void doFilterTestIfUserExists() throws IOException, ServletException {
        LOGGER.debug("Started doFilterTestIfUserExists");

        given(session.getAttribute(SessionParameters.USER)).willReturn(user);
        given(httpServletRequest.getCookies()).willReturn(new Cookie[0]);
        given(httpServletRequest.getParameter(SessionParameters.COMMAND))
                .willReturn(CommandList.ADMIN_PAGE_INFO.name());

        SecurityConstraints constraints = SecurityConstraints.getInstance();
        constraints.addConstraint(CommandList.ADMIN_PAGE_INFO.name(), Role.ADMIN);

        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage(BLOCKED);
        securityFilter.doFilter(servletRequest, servletResponse, filterChain);
    }

    /**
     * This test represents situation when user accesses the allowed
     * page and his email and password loads from cookies
     *
     */
    @Test
    public void doFilterTestIfUserNullWithCookies() throws IOException, ServletException {
        LOGGER.debug("Started doFilterTestIfUserNullWithCookies");

        given(session.getAttribute(SessionParameters.USER)).willReturn(null);
        given(httpServletRequest.getCookies()).willReturn(new Cookie[]{new Cookie(SessionParameters.EMAIL_COOKIE,user.getEmail()),
                new Cookie(SessionParameters.PASSWORD_COOKIE,user.getPassword())});

        given(httpServletRequest.getParameter(SessionParameters.COMMAND))
                .willReturn(CommandList.PERSONAL_INFO.name());

        SecurityConstraints constraints = SecurityConstraints.getInstance();
        constraints.addConstraint(CommandList.PERSONAL_INFO.name(), Role.USER,Role.ADMIN);

        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage(ALLOWED);
        securityFilter.doFilter(servletRequest, servletResponse, filterChain);
    }
}