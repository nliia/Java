package ru.kpfu.itis.liia_nurullina.servlet;

import ru.kpfu.itis.liia_nurullina.dao.ItemsDao;
import ru.kpfu.itis.liia_nurullina.dao.impl.ItemsDaoImpl;
import ru.kpfu.itis.liia_nurullina.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bucket extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cost = 0;
        HttpSession session = req.getSession(true);
        if (session.getAttribute("cost") != null) {
            cost = (int) session.getAttribute("cost");
        }
        req.getSession().setAttribute("cost", cost);
        getServletConfig().getServletContext().getRequestDispatcher("/jsp/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cost = 0;
        String id = req.getParameter("id");
        ItemsDao impl = new ItemsDaoImpl();
        Item item = impl.findByPrimaryKey(Long.parseLong(id));
        HttpSession session = req.getSession(true);
        if (session.getAttribute("cost") != null) {
            cost = (int) session.getAttribute("cost");
        }
        List cart = (List) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList();
            cart.add(item);
        } else {
            cart.add(item);
        }

        cost += item.getPrice();
        req.getSession().setAttribute("cost", cost);
        req.getSession().setAttribute("cart", cart);
        resp.sendRedirect("/");
    }
}
