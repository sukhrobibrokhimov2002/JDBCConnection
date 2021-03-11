package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    public static final String USER="postgres";
    public static final String PASSWORD="05798510";
    static String url;
    static String host="localhost";
    static String dbname="Region";
    static String port="5432";

    //Ulanish uchun metod
    public static  Connection getConnection(){
    Connection connection=null;
    url="jdbc:postgresql://"+host+":"+port+"/"+dbname;
        try {
            connection= DriverManager.getConnection(url,USER,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;

    }


}
