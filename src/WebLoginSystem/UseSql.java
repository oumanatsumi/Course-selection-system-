package WebLoginSystem;
import java.sql.*;
/**
 * @author Ouma Natsumi
 */
public class UseSql {
    public static boolean  compare(String account,String password)
    {
        boolean ifCorrect=false;
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

            System.out.println("Success connect Mysql server!");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from webuser");
            //user 为你表的名称
            while (rs.next()) {
//                System.out.println(rs.getString("user_name"));
//                System.out.println(rs.getString("password"));
                if(account.equals(rs.getString("user_name"))&&password.equals(rs.getString("password"))) {
                    ifCorrect = true;
                }
            }
            System.out.println(ifCorrect);
        }
        catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
        return ifCorrect;
    }

    public static boolean AddAccount(String account,String password)
            //向数据库添加记录，返回值为是否已经有记录
    {
        boolean isAccountExist=false;
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

            System.out.println("Success connect Mysql server!");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select user_name from webuser");
            while (rs.next()) {
                System.out.println(rs.getString("user_name"));
                if(account.equals(rs.getString("user_name"))) {
                    isAccountExist =true;
                }
            }
            if(!isAccountExist)
            {
                //ResultSet add = stmt.executeQuery();
                Statement statement = connect.prepareStatement("insert into webuser values (?,?)");
                ((PreparedStatement) statement).setString(1,account);
                ((PreparedStatement) statement).setString(2,password);
                ((PreparedStatement) statement).executeUpdate();
            }
        }
        catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
        System.out.println(isAccountExist);
        return isAccountExist;
    }


}
