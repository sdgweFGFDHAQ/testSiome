package testDesign;

import testDesign.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class MysqlConnection implements ConnectionFactory {

    @Override
    public Connection getConnection(Map<String, String> map) throws ClassNotFoundException {
        String driver = map.get("driver");
        String url = map.get("url");
        String username = map.get("username");
        String password = map.get("password");

        Connection connection = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
