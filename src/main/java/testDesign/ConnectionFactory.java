package testDesign;

import java.sql.Connection;
import java.util.Map;

public interface ConnectionFactory {
    Connection getConnection(Map<String, String> map) throws ClassNotFoundException;
}
