package com.odde.snowball.controller;

import com.odde.snowball.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@WebServlet("/initialPassword")
public class InitializePasswordController extends AppController {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String token = req.getParameter("token");
        if (User.fetchUserByToken(token) == null) {
            resp.sendRedirect("initialize_password_token_error.jsp");
            return;
        }
        resp.sendRedirect("initialize_password.jsp?token=" + token);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("password_confirm");
        String token = req.getParameter("token");
        if (!password.equals(passwordConfirm) || !User.validatePassword(password)) {
            resp.sendRedirect("initialize_password.jsp?error=error&token=" + token);
            return;
        }

        User user = User.fetchUserByToken(token);
        if (user == null) {
            resp.sendRedirect("initialize_password_token_error.jsp");
            return;
        }
        if (!isEmpty(user.getHashdPassword())) {
            resp.sendRedirect("initialize_password.jsp?error=error");
            return;
        }
        user.setupPassword(req.getParameter("password"));
        user.save();
        resp.sendRedirect("initialize_password_success.jsp");
    }
}
