package ru.kpfu.itis.liia_nurullina.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.kpfu.itis.liia_nurullina.dao.ItemsDao;
import ru.kpfu.itis.liia_nurullina.dao.impl.ItemsDaoImpl;
import ru.kpfu.itis.liia_nurullina.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class EditItem extends HttpServlet {
    private static ItemsDao itemsDao;
    private Item newItem = new Item();
    private Random random = new Random();
    private String filePath = "";

    //получили путь для загрузки из web.xml
    public void init() {
        filePath = getServletContext().getInitParameter("file-upload");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, java.io.IOException {

        itemsDao = new ItemsDaoImpl();
        //чекаем, запрос мультипартовый или нет
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);
        //вот тут по идее дожна быть временная директория
        File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024 * 10);
        //парсим реквест!
        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                //если обычное поле формы
                if (item.isFormField()) {
                    processFormField(item);

                } else {//если файл
                    processUploadedFile(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        //создали объект, здесь собственно и обновляем через дао
        itemsDao.update(newItem);
        request.setAttribute("Message", "Товар изменен!");
        getServletConfig().getServletContext().getRequestDispatcher("/editItem.ftl").forward(request, response);
    }

    private void processUploadedFile(FileItem item) throws Exception {
        //ну получили файл, обрабатываем, ссылку пихаем в поле объекта товара
        File uploadedFile;
        int randName = random.nextInt();
        do {
            String path = filePath + randName + item.getName();
            newItem.setPicture(randName + item.getName());
            uploadedFile = new File(path);
        } while (uploadedFile.exists());

        uploadedFile.createNewFile();
        item.write(uploadedFile);

    }

    private void processFormField(FileItem item) {
        //парсим все поля, добавляем в новый объект(айди получаем в запросе уже существующего,
        // поэтому потом просто обновляем объект через ДАО)
        if (item.getFieldName().equals("id")) {
            Long id = Long.parseLong(item.getString());

            newItem = itemsDao.findByPrimaryKey(id);
        }
        if (item.getFieldName().equals("name")) {
            newItem.setName(item.getString());
        }

        if (item.getFieldName().equals("description")) {
            newItem.setDescription(item.getString());
        }
        if (item.getFieldName().equals("genre")) {
            newItem.setGenre(item.getString());
        }
        if (item.getFieldName().equals("price")) {
            float price = Float.parseFloat(item.getString());

            newItem.setPrice(price);
        }
        System.out.println(item.getFieldName() + "=" + item.getString());
    }

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, java.io.IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("id", id);
        getServletConfig().getServletContext().getRequestDispatcher("/editItem.ftl").forward(req, resp);
    }
}
