package ru.kpfu.itis.liia_nurullina;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import ru.kpfu.itis.liia_nurullina.servlet.LoginServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthTest {

    private static HttpServletResponse response;
    private static HttpServletRequest request;
    private static RequestDispatcher dispatcher;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin1";
    private static final String REMEMBER = "true";
    private static HttpSession session;
    private static LoginServlet loginServlet;
    private static Cookie cUserName;
    private static Cookie cRemember;

    @BeforeClass
    public static void setUp() throws ServletException, IOException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        loginServlet = new LoginServlet();
        cUserName = new Cookie("cookuser", USERNAME);
        cRemember = new Cookie("cookrem", REMEMBER);
        cUserName.setMaxAge(60 * 60 * 24 * 15);//15 days
        cRemember.setMaxAge(60 * 60 * 24 * 15);
        when(request.getRequestDispatcher("/login.ftl")).thenReturn(dispatcher);
        when(request.getRequestDispatcher("/profile.ftl")).thenReturn(dispatcher);
        when(request.getParameter("username")).thenReturn(USERNAME);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(request.getParameter("remember")).thenReturn(REMEMBER);
        when(request.getSession()).thenReturn(session);
        when(request.getCookies()).thenReturn(null);

    }

    @Test
    public void doGetShouldWorkCorrectly() throws ServletException, IOException {
        loginServlet.doGet(request, response);
        verify(request).getRequestDispatcher("/login.ftl");
    }

    @Test
    public void doPostShouldWorkCorrectlyForExistingUser() throws ServletException, IOException {
        loginServlet.doPost(request, response);
        verify(request).getParameter("username");
        verify(request).getParameter("password");
        verify(request).setAttribute("username", USERNAME);
        verify(session).setAttribute("session_uname", USERNAME);
        verify(response).sendRedirect("/profile");
    }
}