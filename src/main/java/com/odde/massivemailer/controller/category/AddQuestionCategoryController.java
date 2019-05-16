package com.odde.massivemailer.controller.category;

import com.odde.massivemailer.controller.AppController;
import com.odde.massivemailer.model.onlinetest.OnlineTest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/category/addCategory")
public class AddQuestionCategoryController extends AppController {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO db登録
        resp.sendRedirect("/onlinetest/add_question.jsp");
    }
}