package JdbcConnect;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcText {

    Connection connection=null;
    Statement statement=null;
    ResultSet resultSet=null;

    @Test
    public void textQuery(){
        connection=jdbcUtils.getConn();
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery("SELECT * FROM Student");
            while (resultSet.next()){
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                System.out.println(id+"   "+name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.closeAll(connection,statement,resultSet);
        }
    }
}
