package ru.kpfu.itis.liia_nurullina.servlet;

import org.apache.commons.codec.digest.DigestUtils;
import ru.kpfu.itis.liia_nurullina.dao.UsersDao;
import ru.kpfu.itis.liia_nurullina.dao.impl.UsersDaoImpl;
import ru.kpfu.itis.liia_nurullina.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//изменяет данные пользователя
public class ProfileServlet extends HttpServlet {
    private static final String SALT = "QxLUF1bgIAdeQX";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("newUsername");
        String psw = req.getParameter("newPassword");
        String email = req.getParameter("newEmail");
        String hash = DigestUtils.md5Hex(psw + SALT);
        HttpSession session = req.getSession();
        String oldUsername = (String) session.getAttribute("session_uname");
        User newUser = new User();
        UsersDao impl = new UsersDaoImpl();
        //по логину находим пользователя
        User oldUser = impl.findByLogin(oldUsername);
        //добавляем к нему новые поля
        newUser.setLogin(username);
        newUser.setEmail(email);
        newUser.setPassword(hash);
        newUser.setId(oldUser.getId());
        //апдейтим
        impl.update(newUser);

        req.setAttribute("update", true);
        getServletConfig().getServletContext().getRequestDispatcher("/profile.ftl").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletConfig().getServletContext().getRequestDispatcher("/profile.ftl").forward(req, resp);
    }
}
