package com.shop.controller;

import com.shop.bean.BeanFactory;
import com.shop.entity.User;
import com.shop.model.request.user.AuthRequest;
import com.shop.service.UserService;
import com.shop.utils.ReflectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private UserService userService;

    public LoginController(){
        this.userService = (UserService) BeanFactory.beans.get("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("/views/login.jsp");
        rs.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AuthRequest authRequest = ReflectionUtils.mapToModel(AuthRequest.class, req);
        Optional<User> user = userService.findByUserNameAndPassword(authRequest);
        if (user.isPresent()){
            resp.sendRedirect("/admin");
        }
        else {
            resp.sendRedirect("/login");
        }
    }
}
