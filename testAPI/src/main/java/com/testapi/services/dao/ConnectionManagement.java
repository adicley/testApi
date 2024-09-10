
package com.testapi.services.dao;

import com.testapi.services.util.ApiTestConfigs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectionManagement {
    private static Connection conn = null;
    
    public static void createConnection() throws Exception{
        
        Class.forName("org.postgresql.Driver");
        
        conn = DriverManager.getConnection(ApiTestConfigs.DB_URL + ApiTestConfigs.DB_NAME,
                ApiTestConfigs.DB_USERNAME, ApiTestConfigs.DB_PASSWORD);
    }
    
    public static void closeConnection() throws Exception{
        if (conn != null)
            conn.close();
    }
    
    public static void closeStatement(PreparedStatement ps) throws Exception{
        if (ps != null)
            ps.close();
    }
    
    public static Connection getConnection(){
        if (conn != null)
            return conn;
        return null;
    }
}
