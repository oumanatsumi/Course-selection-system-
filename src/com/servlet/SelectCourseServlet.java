package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ouma Natsumi
 */

@WebServlet("/SelectCourseServlet")
public class SelectCourseServlet extends HttpServlet {
        @Override
        public void init() throws ServletException{

        }
        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
               String id= request.getParameter("id");
               System.out.println(id);
               if(MyFirstServlet.studentBean.isSelectCourse(Integer.parseInt(id)-1)==true)
               {
                   MyFirstServlet.studentBean.UnSelectCourse(Integer.parseInt(id));
               }
               else
               {
                   MyFirstServlet.studentBean.SelectCourse(Integer.parseInt(id));
               }
               MyFirstServlet.studentBean.setStudentName(MyFirstServlet.studentName);
               MyFirstServlet.studentBean.getStudentInfo();
                request.setAttribute("student",MyFirstServlet.studentBean);

                MyFirstServlet.courseBean.getCourseInfo();
                request.setAttribute("course",MyFirstServlet.courseBean);

               getServletContext().getRequestDispatcher("/SelectCourse.jsp").forward(request,response);
        }
    }

