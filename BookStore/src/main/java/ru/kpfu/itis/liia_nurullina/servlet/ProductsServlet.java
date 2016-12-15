package ru.kpfu.itis.liia_nurullina.servlet;

import ru.kpfu.itis.liia_nurullina.dao.ItemsDao;
import ru.kpfu.itis.liia_nurullina.dao.impl.ItemsDaoImpl;
import ru.kpfu.itis.liia_nurullina.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

//это homepage
public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        Cookie[] cookies = req.getCookies();
        //если куки есть, а сессии нет, то авторизуем пользователя(авторизация при первом заходе на главную стр)
        String username = (String) session.getAttribute("session_uname");
        if (username == null && cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("cookuser"))
                    session.setAttribute("session_uname", cookies[i].getValue());
            }
        //по дефолту ставим первую страницу(пагинация)
        int page = 1;
        int recordsPerPage = 3;
        //но если в запросе пришла какая то страница, то берем ее
        String pageFromRequest = req.getParameter("page");
//        String genre = req.getParameter("genre");
        if (pageFromRequest != null)
            page = Integer.parseInt(pageFromRequest);

        ItemsDao itemsDao = new ItemsDaoImpl();
        List<Item> productsSub;
        //получаем саблист товаров с количествой recordsPerPage и начиная с позиции (page - 1) * recordsPerPage
//        if (genre.equals(null)) {
        productsSub = itemsDao.viewAllItems((page - 1) * recordsPerPage, recordsPerPage);
//        } else {
//            productsSub = itemsDao.viewItemsByGenre((page - 1) * recordsPerPage, recordsPerPage, genre);
//        }
        //получаем общее кол-во товаров
        int noOfRecords = itemsDao.size();
        //выясняем сколько всего страниц будет
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
