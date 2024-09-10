
package com.testapi.services.util;

public class ApiTestConfigs {
    //Acesso local;
    //public static final String DB_URL = "jdbc:postgresql://localhost:5432";
    //public static final String DB_USERNAME = "postgres";
    //public static final String DB_PASSWORD = "postgres";
    
    //Acesso Remoto;
    public static final String DB_URL = "jdbc:postgresql://mypostgresql.cxlxdd9xytdm.us-east-1.rds.amazonaws.com:5432";
    public static final String DB_USERNAME = "professor";
    public static final String DB_PASSWORD = "professor";
    
    //Dados do banco;
    public static final String DB_NAME = "/dbApiTest";
    public static final String USER_TABLE = "api.usuario";
}
