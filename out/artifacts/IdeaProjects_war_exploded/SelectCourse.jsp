<%@ page import="javax.swing.*" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: Ouma Natsumi
  Date: 2020/4/26
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>选课界面</title>
</head>
<body background="BG/48149888_p0.jpg"
      style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">

    <h1 align="center"> 圆方教务管理系统</h1>
    <jsp:useBean id="student" scope="request" type="javabean.StudentBean" class="javabean.StudentBean"/>
    <jsp:useBean id="course" scope="request" type="javabean.CourseBean" class="javabean.CourseBean"/>

    <h3 align="center"> 你好，<%=student.getStudentName()%>，该选课了！！！</h3>
    <%! Boolean[] selectedCourse=new Boolean[10];%>
    <%! int[] courseId=new int[10];%>
    <%! String[] courseName=new String[10];%>
    <%! float[] credit=new float[10];%>
    <%! int[] period=new int[10];%>
    <%! String[] teacherName=new String[10];%>
    <%! float totalCredit;%>
    <%! int totalPeriod;%>
    <%
    for(int i=0;i<10;i++)
            {
                selectedCourse[i]=student.isSelectCourse(i);
                courseId[i]=course.getid(i);
                courseName[i]=course.getCourseName(i);
                credit[i]=course.getCredit(i);
                period[i]=course.getPeriod(i);
                teacherName[i]=course.getTeacherName(i);
            }
        totalCredit=student.getTotalCredit();
        totalPeriod=student.getTotalPeriod();
    %>



    <table border="1" cellspacing="0" align="center">
        <tr align="center">
            <td>序号</td>
            <td>课程名称</td>
            <td>学分</td>
            <td>学时</td>
            <td>任课老师</td>
            <td>选课</td>
        </tr>

        <% for(int i=0;i<10;i++)
            {
                if(!student.isSelectCourse(i))
                {
        %>
        <tr align="center">
            <td ><%=courseId[i]%></td>
            <td ><%=courseName[i]%></td>
            <td ><%=credit[i]%></td>
            <td ><%=period[i]%></td>
            <td ><%=teacherName[i]%></td>
            <td >
                <form action="SelectCourseServlet" method="get">
                <input name="id" type="hidden" value=<%=courseId[i]%>>
                    <input type="submit" value="选课">
            </form>
            </td>
        </tr>
    <%
                }
            }%>

        <caption style="margin-bottom: 20px">
            未选课程
        </caption>

        <table border="1" cellspacing="0" align="center">
            <tr align="center">
                <td>序号</td>
                <td>课程名称</td>
                <td>学分</td>
                <td>学时</td>
                <td>任课老师</td>
                <td>选课</td>
            </tr>

            <% for(int i=0;i<10;i++)
            {
                if(student.isSelectCourse(i))
                {
            %>
            <tr align="center">
                <td ><%=courseId[i]%></td>
                <td ><%=courseName[i]%></td>
                <td ><%=credit[i]%></td>
                <td ><%=period[i]%></td>
                <td ><%=teacherName[i]%></td>
                <td >
                    <form action="SelectCourseServlet" method="get">
                        <input name="id" type="hidden" value=<%=courseId[i]%>>
                        <input type="submit" value="退选">
                    </form>
                </td>
            </tr>
            <%
                    }
                }%>
            <caption style="margin-bottom: 20px;margin-top: 10px">
                已选课程
            </caption>


            <table border="1" cellspacing="0" align="center">
                <tr align="center">
                    <td>用户姓名</td>
                    <td>总学分</td>
                    <td>总学时</td>
                </tr>

                <tr align="center">
                    <td><%=student.getStudentName()%></td>
                    <td><%= totalCredit %></td>
                    <td><%= totalPeriod %></td>
                </tr>

                <caption style="margin-bottom: 20px;margin-top: 10px">
                    统计
                </caption>

        <%--        <%--%>
<%--            for(int i=0;i<10;i++)--%>
<%--            {--%>
<%--        %>--%>
<%--        <tr align="center">--%>
<%--            <td ><%=courseId[i]%></td>--%>
<%--            <td ><%=courseName[i]%></td>--%>
<%--            <td ><%=credit[i]%></td>--%>
<%--            <td ><%=period[i]%></td>--%>
<%--            <td ><%=teacherName[i]%></td>--%>
<%--            <td > <button type="button" id=<%=i%> onclick="--%>
<%--                <% System.out.println(courseId[i]);--%>
<%--                    System.out.println(teacherName[i]);--%>
<%--                 %> window.location.reload()">选课</button>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--            <%--%>
<%--                }--%>
<%--            %>--%>

</table>
</body>
</html>
