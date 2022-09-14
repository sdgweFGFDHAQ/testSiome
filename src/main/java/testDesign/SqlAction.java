package testDesign;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SqlAction {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionFactory cf = new MysqlConnection();
        Map<String, String> map = new HashMap<>();
        map.put("url", "jdbc:mysql://localhost:3306/rpg");
        map.put("driver", "com.mysql.cj.jdbc.Driver");
        map.put("username", "root");
        map.put("password", "root");

        Connection connection = cf.getConnection(map);
        String sql = "select * from weapon";
        PreparedStatement ps = connection.prepareStatement(sql);
        boolean execute = ps.execute();
        System.out.println(execute);
    }
}
