package JdbcConnect;

import java.sql.*;

public class jdbcUtils {

    static Connection connection=null;
    static Statement statement=null;
    static ResultSet resultSet=null;
    static String username=null;
    static String password=null;
    static String url=null;
    static String driverClass=null;
    static PreparedStatement preparedStatement=null;

    //加载数据
    static {
        username=StaticData.username;
        password=StaticData.password;
        url=StaticData.url;
        driverClass=StaticData.driverClass;
    }

    //连接数据库
    public static Connection getConn(){
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //关闭
    public static void closeAll(Connection connection,Statement statement,ResultSet resultSet){
        closeConn(connection);
        closeSt(statement);
        closeRe(resultSet);
    }

    //关闭connection,statement,result
    public static void closeConn(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connection=null;
            }
        }
    }

    public static void closeConn(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static void closeSt(Statement statement){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                statement=null;
            }
        }
    }

    public static void closeRe(ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                resultSet=null;
            }
        }
    }

    //更新
    public static void jdbcExecuteUpdate(String s) throws SQLException{
        connection=getConn();
        statement=connection.createStatement();
        statement.executeUpdate(s);
    }

    //查询
    public static void jdbcExecuteQuery(String s) throws SQLException{
        connection=getConn();
        preparedStatement=connection.prepareStatement(s);
        resultSet=preparedStatement.executeQuery();
    }

    public static void jdbcDelete(String sql,Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        connection=getConn();
        try {
            preparedStatement=connection.prepareStatement(sql);
           //preparedStatement.setString(1,Wno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
