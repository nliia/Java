package onlineStoreTests;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import ru.kpfu.itis.liia_nurullina.servlet.LoginServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Liia on 19.10.2016.
 */
public class AuthTest extends TestCase {

    @Test
    public void test() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin1");
        when(request.getSession()).thenReturn(session);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/profile.ftl")).thenReturn(dispatcher);
        when(request.getRequestDispatcher("/login.ftl")).thenReturn(dispatcher);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        new LoginServlet().doPost(request, response);
        verify(session).setAttribute("session_uname", "admin");

        String result = sw.getBuffer().toString().trim();

        System.out.println("Result: " + result);

       // assertEquals("Вы успешно авторизованы!", result);
    }
}