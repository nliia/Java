package ru.kpfu.itis.liia_nurullina.servlet;

import ru.kpfu.itis.liia_nurullina.dao.ItemsDao;
import ru.kpfu.itis.liia_nurullina.dao.impl.ItemsDaoImpl;
import ru.kpfu.itis.liia_nurullina.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Cookie[] cookies = req.getCookies();
        String username = (String) session.getAttribute("session_uname");
        if (username == null && cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("cookuser"))
                    session.setAttribute("session_uname", cookies[i].getValue());
            }
        int page = 1;
        int recordsPerPage = 3;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        ItemsDao impl = new ItemsDaoImpl();
        List<Item> products = impl.findAll();
        List<Item> productsSub;
        productsSub = impl.viewAllItems((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = products.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("products", productsSub);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
