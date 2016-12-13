package ru.kpfu.itis.liia_nurullina.servlet;

import ru.kpfu.itis.liia_nurullina.dao.CommentsDao;
import ru.kpfu.itis.liia_nurullina.dao.ItemsDao;
import ru.kpfu.itis.liia_nurullina.dao.impl.CommentsDaoImpl;
import ru.kpfu.itis.liia_nurullina.dao.impl.ItemsDaoImpl;
import ru.kpfu.itis.liia_nurullina.model.Item;
import ru.kpfu.itis.liia_nurullina.model.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ShowItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ItemsDao impl = new ItemsDaoImpl();
        Item item = impl.findByPrimaryKey(id);
        CommentsDao implComm = new CommentsDaoImpl();
        ArrayList<Comment> comments = (ArrayList) implComm.findByItemId(id);
        if (comments != null)
            Collections.sort(comments, (o1, o2) -> {
                if (o1.getDate().after(o2.getDate()))
                    return -1;
                if (o1.getDate().before(o2.getDate()))
                    return 1;
                return 0;
            });
        req.setAttribute("item", item);
        req.setAttribute("comments", comments);
        getServletConfig().getServletContext().getRequestDispatcher("/jsp/item.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }
}
