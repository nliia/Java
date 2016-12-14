package ru.kpfu.itis.liia_nurullina.servlet;

import org.json.JSONException;
import org.json.JSONObject;
import ru.kpfu.itis.liia_nurullina.dao.ItemsDao;
import ru.kpfu.itis.liia_nurullina.dao.impl.ItemsDaoImpl;
import ru.kpfu.itis.liia_nurullina.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sagit Khaliullin
 * group 11-501
 * 20161213
 */
public class AjaxSearch extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String genre = request.getParameter("genre");
        ItemsDao itemsDao = new ItemsDaoImpl();
        if (genre != null) {
            try {
                List<Item> itemList = itemsDao.viewItemsByGenre(0, 100, genre);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", itemList);
                response.setContentType("application/json");
                response.getWriter().println(jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
