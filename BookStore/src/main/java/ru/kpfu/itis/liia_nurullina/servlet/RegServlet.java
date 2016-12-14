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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegServlet extends HttpServlet {

    private static boolean checkEmail(String email) {
        Pattern p = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    private static boolean checkPassword(String psw) {
        Pattern p = Pattern.compile("^[a-z0-9_-]{6,18}$");
        Matcher m = p.matcher(psw);
        return m.matches();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletConfig().getServletContext().getRequestDispatcher("/reg.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String psw = req.getParameter("password");
        String email = req.getParameter("email");
        boolean errorLogin = false;
        boolean errorEmail = false;
        boolean emailFormat = false;
        boolean pswFormat = false;
        if (checkEmail(email) && checkPassword(psw)) {
            UsersDao impl = new UsersDaoImpl();
            ArrayList<User> userList = (ArrayList<User>) impl.findAll();

            if (!userList.isEmpty()) {
                for (User user : userList) {
                    if (user.getLogin().equals(login)) {
                        errorLogin = true;
                    }
                    if (user.getEmail().equals(email)) {
                        errorEmail = true;
                    }
                }
            }

            if (!errorEmail && !errorLogin) {
                User newUser = new User();
                newUser.setPassword(DigestUtils.md5Hex(psw + "QxLUF1bgIAdeQX"));
                newUser.setLogin(login);
                newUser.setEmail(email);
                impl.add(newUser);
                req.setAttribute("reg", "Вы успешно зарегистрированы!");
                req.setAttribute("username", login);
                Cookie cUserName = new Cookie("cookuser", login);
                Cookie cRemember = new Cookie("cookrem", "true");
                cUserName.setMaxAge(60 * 60 * 24 * 15);//15 days
                cRemember.setMaxAge(60 * 60 * 24 * 15);
                resp.addCookie(cUserName);
                resp.addCookie(cRemember);
                req.getSession().setAttribute("session_uname", login);
                req.setAttribute("username", login);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/profile.ftl");
                requestDispatcher.forward(req, resp);
            } else {
                req.setAttribute("errorEmail", errorEmail);
                req.setAttribute("errorLogin", errorLogin);
                getServletConfig().getServletContext().getRequestDispatcher("/reg.ftl").forward(req, resp);
            }
        } else {
            if (!checkEmail(email))
                emailFormat = true;
            if (!checkPassword(psw))
                pswFormat = true;
            req.setAttribute("emailFormat", emailFormat);
            req.setAttribute("pswFormat", pswFormat);
            getServletConfig().getServletContext().getRequestDispatcher("/reg.ftl").forward(req, resp);
        }
    }
}
