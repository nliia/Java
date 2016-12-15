package ru.kpfu.itis.liia_nurullina.servlet;

import ru.kpfu.itis.liia_nurullina.dao.CommentsDao;
import ru.kpfu.itis.liia_nurullina.dao.ItemsDao;
import ru.kpfu.itis.liia_nurullina.dao.impl.CommentsDaoImpl;
import ru.kpfu.itis.liia_nurullina.dao.impl.ItemsDaoImpl;
import ru.kpfu.itis.liia_nurullina.model.Comment;
import ru.kpfu.itis.liia_nurullina.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
//страница товара
public class ShowItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //получаем айди, находим товар и его комменты
        Long id = Long.parseLong(req.getParameter("id"));
        ItemsDao itemsDao = new ItemsDaoImpl();
        Item item = itemsDao.findByPrimaryKey(id);
        CommentsDao implComm = new CommentsDaoImpl();
        ArrayList<Comment> comments = (ArrayList) implComm.findByItemId(id);
        req.setAttribute("item", item);
        req.setAttribute("comments", comments);
        getServletConfig().getServletContext().getRequestDispatcher("/jsp/item.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }
}
