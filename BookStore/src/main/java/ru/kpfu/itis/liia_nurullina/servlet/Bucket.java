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

//сервлет добавляет в корзину, меняет стоимость заказа
public class Bucket extends HttpServlet {
    private int newCost;
    private Object cost;
    private List cart;
    private Item item;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        newCost = 0;
        HttpSession session = req.getSession(true);
        if (session.getAttribute("cost") != null) {
            newCost = (int) session.getAttribute("cost");
        }
        req.getSession().setAttribute("cost", newCost);
        getServletConfig().getServletContext().getRequestDispatcher("/jsp/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        newCost = 0;
        cost = session.getAttribute("cost");
        String id = req.getParameter("id");
        //по айди находим товар в бд
        ItemsDao impl = new ItemsDaoImpl();
        item = impl.findByPrimaryKey(Long.parseLong(id));
        //если какая то стоимость уже существвует то берем ее, если нет
        if (cost != null) {
            newCost = (int) cost;
        }
        //берем корзину из сессии и добавляем ее(если нет, то новую создаем)
        cart = (List) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList();
            cart.add(item);
        } else {
            cart.add(item);
        }
        //меняем стоимость
        newCost += item.getPrice();
        //добавляем все в сессию
        req.getSession().setAttribute("cost", newCost);
        req.getSession().setAttribute("cart", cart);
        resp.sendRedirect("/");
    }
}
