package database;

import java.sql.*;

public class Database {

    //声明Connection对象
    private static Connection con;


    public static void insert(String name, String phone) throws SQLException {
        PreparedStatement psql;

        psql = con.prepareStatement("INSERT INTO `phone_info` (name, phone) " +
                "VALUES (" + name + ", " + phone + ")");

        psql.executeUpdate();
    }

    public static void update(String name, String phone) throws SQLException {

        PreparedStatement psql;

        psql = con.prepareStatement("UPDATE `phone_info` SET phone = '" + phone + "' " +
                "WHERE name = '" + name + "'");

        psql.executeUpdate();
    }

    public static String query(String name) {

        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名
        String url = "jdbc:mysql://localhost:3306/address_book_demo";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "z295415658";
        //遍历查询结果集

        StringBuilder queryString = new StringBuilder();

        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");

            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();

            //要执行的SQL语句
            String sql = "SELECT * FROM `phone_info`";
            if (name != null) {
                sql = "SELECT * FROM  `phone_info` WHERE name = " + "'" + name + "'";
            }

            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);

            String queryName, queryPhone;
            while (rs.next()) {
                //获取stuname这列数据
                queryName = rs.getString("name");
                //获取stuid这列数据
                queryPhone = rs.getString("phone");

                //输出结果
                queryString.append(queryName).append(", ").append(queryPhone).append("\n");
//                System.out.println(queryName + ", " + queryPhone);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryString.toString();
    }

}