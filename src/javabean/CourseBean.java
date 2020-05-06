package javabean;

import java.sql.*;

/**
 * @author Ouma Natsumi
 */
public class CourseBean {
    private int[] id=new int[10];
    private String[] courseName=new String[10];
    private  float[] credit=new float[10];
    private int[] period=new int[10];
    private  String[] teacherName=new String[10];

    public  CourseBean(){
    }

    public int getid(int index) {
        return id[index];
    }

    public void setID(int ID, int index) {
        this.id[index] = ID;
    }

    public String getCourseName(int index) {
        return courseName[index];
    }

    public void setCourseName(String courseName,int index) {
        this.courseName[index] = courseName;
    }

    public float getCredit(int index) {
        return credit[index];
    }

    public void setCredit(float credit,int index) {
        this.credit[index] = credit;
    }

    public int getPeriod(int index) {
        return period[index];
    }

    public void setPeriod(int period,int index) {
        this.period[index] = period;
    }

    public String getTeacherName(int index) {
        return teacherName[index];
    }

    public void setTeacherName(String teacherName,int index) {
        this.teacherName[index] = teacherName;
    }

    /**
     * 从数据库获取课程信息
     */
    public void getCourseInfo()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //加载MYSQL JDBC驱动程序
            System.out.println("Success loading Mysql Driver!");
        }
        catch (Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try {
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/my_schema","root","hyj7895123");
            //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

            System.out.println("Success connect Mysql server_by CourseBean!");

            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from course");
            System.out.println(rs);
            int resCount=0;
            while (rs.next()) {
                this.id[resCount]=rs.getInt("No");
                this.courseName[resCount]=rs.getString("CourseName");
                this.credit[resCount]=rs.getFloat("Credit");
                this.period[resCount]=rs.getInt("Period");
                this.teacherName[resCount]=rs.getString("TeacherName");
                resCount++;
            }

        }
        catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
    }

}
