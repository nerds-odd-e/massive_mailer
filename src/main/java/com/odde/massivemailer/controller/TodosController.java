package com.odde.massivemailer.controller;

import com.odde.massivemailer.model.Todo;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/todos")
public class TodosController extends AppController {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getOutputStream().print(toJson(Todo.findAll()));
    }

    private String toJson(LazyList<Model> objects) {
        String json = String.join(",", objects.stream().map(obj -> obj.toJson(true)).collect(Collectors.toList()));
        return "[" + json + "]";
    }
}