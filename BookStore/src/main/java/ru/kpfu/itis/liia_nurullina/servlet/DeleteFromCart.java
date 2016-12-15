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

public class DeleteFromCart extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        //нашли товар по айди
        ItemsDao impl = new ItemsDaoImpl();
        Item newItem = impl.findByPrimaryKey(Long.parseLong(id));
        //получаем сумму заказа и уменьшаем ее
        HttpSession session = req.getSession(true);
        int cost = (int) session.getAttribute("cost");
        cost -= newItem.getPrice();
        //получаем всю корзину из сессии и удаляем оттуда товар
        ArrayList cart = (ArrayList) session.getAttribute("cart");
        cart.remove(newItem);
        //кидаем на страницу обновленные корзину и стоимость
        req.getSession().setAttribute("cost", cost);
        req.getSession().setAttribute("cart", cart);
        getServletConfig().getServletContext().getRequestDispatcher("/jsp/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
