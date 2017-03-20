package api;

import com.google.gson.reflect.TypeToken;
import model.douban.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by James Ji on 07/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
@WebServlet(name = "api.ApiForwarder")
public class ApiForwarder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        List<Book> suzumiyaHaruhi = API.queryDoubanForBooks("凉宫春日");
        Type bookListType = new TypeToken<List<Book>>() {}.getType();

        String json = GsonFactory.getGson().toJson(suzumiyaHaruhi, bookListType);
        writer.write(json);
        writer.flush();
    }
}
