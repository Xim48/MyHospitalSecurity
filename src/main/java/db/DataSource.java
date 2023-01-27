package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setDriverClassName("org.postgresql.Driver");
        //config.setDriverClassName("JDBC_DRIVER");
        config.setJdbcUrl(System.getenv("JDBC_URL"));
        config.setUsername(System.getenv("JDBC_USER"));
        config.setPassword(System.getenv("JDBC_PASSWORD"));
        config.addDataSourceProperty("validationTimeout", 5000);
        config.setMaximumPoolSize(20);
        ds = new HikariDataSource(config);
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        //System.out.println("-----------"+ds.getConnection());
        return ds.getConnection();
    }

    public static void close(){
        ds.close();
    }
}
