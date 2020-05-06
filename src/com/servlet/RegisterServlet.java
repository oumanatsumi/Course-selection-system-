package com.servlet;

import WebLoginSystem.UseSql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ouma Natsumi
 */
@WebServlet(name = "RegisterServlet")

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String confirmPwd =request.getParameter("confirmpwd");
        if(uname.isEmpty())
        {
            response.getWriter().write("<script>alert('用户名不能为空！');window.location='http://localhost:9999/IdeaProjects_war_exploded/Register.html';</script>");
        }
        else if(pwd.isEmpty())
        {
            response.getWriter().write("<script>alert('密码不能为空！');window.location='http://localhost:9999/IdeaProjects_war_exploded/Register.html';</script>");
        }
        else if(!pwd.equals(confirmPwd))
        {
            response.getWriter().write("<script>alert('两次输入密码不同！');window.location='http://localhost:9999/IdeaProjects_war_exploded/Register.html';</script>");
        }
        else if(UseSql.AddAccount(uname,pwd))//如果已经存在用户名
        {
            response.getWriter().write("<script>alert('用户名已被注册！');window.location='http://localhost:9999/IdeaProjects_war_exploded/Register.html';</script>");
        }
        else
        {
            response.getWriter().write("<script>alert('注册成功！');window.location='http://localhost:9999/IdeaProjects_war_exploded/Main.html';</script>");
        }

    }
}
