package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import WebLoginSystem.UseSql;
import javabean.CourseBean;
import javabean.StudentBean;


/**
 * @author Ouma Natsumi
 */
@WebServlet(name = "MyFirstServlet")

public class MyFirstServlet extends HttpServlet {
    public static StudentBean studentBean;
    public static CourseBean courseBean;
    public static String studentName;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应编码格式
        response.setContentType("text/html;charset=utf-8");
        String userName = request.getParameter("uname");
        studentName=userName;
        String password = request.getParameter("pwd");
        if(UseSql.compare(userName,password)==true)
        {
            //response.getWriter().write("<script>alert('登录成功！');window.location='http://localhost:9999/IdeaProjects_war_exploded/LoginSuccess.html';</script>");

            //初始化StudentBean
            studentBean=new StudentBean();
            studentBean.setStudentName(userName);
            studentBean.getStudentInfo();
            request.setAttribute("student",studentBean);

            //初始化CourseBean
            courseBean=new CourseBean();
            courseBean.getCourseInfo();
            request.setAttribute("course",courseBean);

            getServletContext().getRequestDispatcher("/SelectCourse.jsp").forward(request,response);

        }
        else {
            response.getWriter().write("<script>alert('用户名或密码错误！');window.location='http://localhost:9999/IdeaProjects_war_exploded/Main.html';</script>");
        }
    }
}
