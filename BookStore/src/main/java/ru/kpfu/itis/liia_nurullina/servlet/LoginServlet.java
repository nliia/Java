package ru.kpfu.itis.liia_nurullina.servlet;

import org.apache.commons.codec.digest.DigestUtils;
import ru.kpfu.itis.liia_nurullina.dao.UsersDao;
import ru.kpfu.itis.liia_nurullina.dao.impl.UsersDaoImpl;
import ru.kpfu.itis.liia_nurullina.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        String username = null;
        String remember = "false;";
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("cookuser")) {
                    username = cookies[i].getValue();
                }

                if (cookies[i].getName().equals("cookrem")) {
                    remember = cookies[i].getValue();
                }
            }
            if (remember.equals("true")) {
                req.getSession().setAttribute("session_uname", username);
                req.setAttribute("username", username);
                getServletConfig().getServletContext().getRequestDispatcher("/profile.ftl").forward(req, resp);
            }
        }

        getServletConfig().getServletContext().getRequestDispatcher("/login.ftl").forward(req, resp);


    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String psw = req.getParameter("password");
        String hash = DigestUtils.md5Hex(psw + "QxLUF1bgIAdeQX");
        boolean isUserExists = false;
        UsersDao impl = new UsersDaoImpl();
        User user = impl.findByLogin(username);

        if (user != null && user.getPassword().equals(hash)) {
            isUserExists = true;
        }
        if (isUserExists) {

            if (req.getParameter("remember") != null) {
                String remember = req.getParameter("remember");
                Cookie cUserName = new Cookie("cookuser", username);
                Cookie cRemember = new Cookie("cookrem", remember);
                cUserName.setMaxAge(60 * 60 * 24 * 15);//15 days
                cRemember.setMaxAge(60 * 60 * 24 * 15);
                resp.addCookie(cUserName);
                resp.addCookie(cRemember);
            }

            req.getSession().setAttribute("session_uname", username);
            req.setAttribute("username", username);
            resp.sendRedirect("/profile");
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/profile");
//            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("error", true);
            getServletConfig().getServletContext().getRequestDispatcher("/login.ftl").forward(req, resp);
        }


    }


}
