package javabean;

import com.servlet.MyFirstServlet;

import java.sql.*;

/**
 * @author Ouma Natsumi
 * 用之前必须先调用setStudentName方法，然后查询数据库中的选课情况
 */
public class StudentBean {
    private String studentName="";
    private  Boolean[] selectCourse;
    public StudentBean()
    {}
    ///Setter & Getter
    public Boolean isSelectCourse(int index) {
        return selectCourse[index];
    }

    public void SelectCourse(int index) {
        if(studentName.equals(""))
        {
            System.out.println("Please enter student's name.");
        }
        else
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

                System.out.println("Success connect Mysql server_by StudentBean!");
                String sql="update webuser set isChoose1=?,isChoose2=?, isChoose3=?, isChoose4=?, isChoose5=?, " +
                        "isChoose6=?, isChoose7=?, isChoose8=?, isChoose9=?, isChoose10=? where user_name=?";
                Statement statement = connect.prepareStatement(sql);
                for(int i=0; i<10 ;i++)
                {
                    if(i+1 !=index)
                    {
                        int temp=selectCourse[i]  ? 1 : 0;
                        ((PreparedStatement) statement).setInt(i+1,temp);
                    }
                    else
                    {
                        //将该选课状态置为1（已选）
                        ((PreparedStatement) statement).setInt(i+1,1);
                    }
                }
                ((PreparedStatement) statement).setString(11,this.studentName);
               ((PreparedStatement) statement).executeUpdate();
                statement.close();
               // rs.close();
                connect.close();
            }
            catch (Exception e) {
                System.out.print("get data error!");
                e.printStackTrace();
            }
        }
}

    public void UnSelectCourse(int index)
    {
        if(studentName.equals(""))
        {
            System.out.println("Please enter student's name.");
        }
        else
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

                System.out.println("Success connect Mysql server_by StudentBean!");
                String sql="update webuser set isChoose1=?,isChoose2=?, isChoose3=?, isChoose4=?, isChoose5=?, " +
                        "isChoose6=?, isChoose7=?, isChoose8=?, isChoose9=?, isChoose10=? where user_name=?";
                Statement statement = connect.prepareStatement(sql);
                for(int i=0; i<10 ;i++)
                {
                    if(i+1 !=index)
                    {
                        int temp=selectCourse[i]  ? 1 : 0;
                        ((PreparedStatement) statement).setInt(i+1,temp);
                    }
                    else
                    {
                        //将该选课状态置为0（未选）
                        ((PreparedStatement) statement).setInt(i+1,0);
                    }
                }
                ((PreparedStatement) statement).setString(11,this.studentName);
                ((PreparedStatement) statement).executeUpdate();
                statement.close();
                // rs.close();
                connect.close();
            }
            catch (Exception e) {
                System.out.print("get data error!");
                e.printStackTrace();
            }
        }
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
        获取学生的选课信息
     */
    public void getStudentInfo()
    {
        if(studentName.equals(""))
        {
            System.out.println("Please enter student's name.");
        }
        else
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

                System.out.println("Success connect Mysql server_by StudentBean!");

                Statement statement = connect.prepareStatement("select * from webuser ");
                ResultSet rs=((PreparedStatement) statement).executeQuery();
                while (rs.next()) {
                    if(rs.getString(1).equals(this.studentName))
                    {
                        String name=rs.getString("user_name");
                        String pw=rs.getString("password");
                        selectCourse=new Boolean[10];
                        for(int i=0;i<10;i++)
                        {
                            if(rs.getInt(i+3)==1) {
                                selectCourse[i]=true;
                            }
                            else {
                                selectCourse[i]=false;
                            }
                        }
                    }
                }
                statement.close();
                rs.close();
                connect.close();
            }
            catch (Exception e) {
                System.out.print("get data error!");
                e.printStackTrace();
            }
        }
    }

    public float getTotalCredit()
    {
        float totalCredit=0.0f;
        for(int i=0;i<selectCourse.length;i++)
        {
            if(selectCourse[i]==true)
            {
                totalCredit+= MyFirstServlet.courseBean.getCredit(i);
            }
        }
        return totalCredit;
    }

    public int getTotalPeriod()
    {
        int totalPeriod=0;
        for(int i=0;i<selectCourse.length;i++)
        {
            if(selectCourse[i]==true)
            {
                totalPeriod+=MyFirstServlet.courseBean.getPeriod(i);
            }
        }
        return totalPeriod;
    }

}
