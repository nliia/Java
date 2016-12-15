package ru.kpfu.itis.liia_nurullina.servlet;

import ru.kpfu.itis.liia_nurullina.dao.CommentsDao;
import ru.kpfu.itis.liia_nurullina.dao.ItemsDao;
import ru.kpfu.itis.liia_nurullina.dao.UsersDao;
import ru.kpfu.itis.liia_nurullina.dao.impl.CommentsDaoImpl;
import ru.kpfu.itis.liia_nurullina.dao.impl.ItemsDaoImpl;
import ru.kpfu.itis.liia_nurullina.dao.impl.UsersDaoImpl;
import ru.kpfu.itis.liia_nurullina.model.Comment;
import ru.kpfu.itis.liia_nurullina.model.Item;
import ru.kpfu.itis.liia_nurullina.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //получаем текст коммента, логин и айди товара
        String comment = req.getParameter("comment");
        String login = req.getParameter("username");
        Long item_id = Long.valueOf(req.getParameter("item_id"));
        //находим пользователя, который оставил коммент, по логину
        UsersDao implUser = new UsersDaoImpl();
        User user = implUser.findByLogin(login);
        //создаем новый объект коммент
        Comment newComment = new Comment();
        newComment.setText(comment);
        newComment.setLogin(login);
        newComment.setItem_id(item_id);
        newComment.setUser_id(user.getId());
        //находим товар, которому был добавлен коммент, по айди
        ItemsDao implItems = new ItemsDaoImpl();
        Item item = implItems.findByPrimaryKey(item_id);
        //добавляем коммент в бд
        CommentsDao implComm = new CommentsDaoImpl();
        implComm.add(newComment);
        //находим все комменты данного товара
        ArrayList comments = (ArrayList) implComm.findByItemId(item_id);
        //кидаем на страницу товар и все его комменты
        req.setAttribute("item", item);
        req.setAttribute("comments", comments);
        getServletConfig().getServletContext().getRequestDispatcher("/jsp/item.jsp").forward(req, resp);
    }
}
