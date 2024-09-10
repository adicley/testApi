
package com.testapi.services.dao;

import com.testapi.model.UserModel;
import com.testapi.services.util.ApiTestConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class ApiDbManagement {
    private Connection conn;
    
    public int create(UserModel model) throws ParseException{
        if ((conn = ConnectionManagement.getConnection()) == null)
            return 0;
        
        String queryCreate = String.format("INSERT INTO %s (cpf, nome, data_nascimento) VALUES (?,?,?)", 
                ApiTestConfigs.USER_TABLE);
        
        int dbResp = 0;
        
        try {
            PreparedStatement ps = conn.prepareStatement(queryCreate);
            
            ps.setInt(1, model.getCpf());
            ps.setString(2, model.getNome());
            ps.setDate(3, model.getDataNascimento());
            
            dbResp = ps.executeUpdate();
            ConnectionManagement.closeStatement(ps);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return dbResp;
    }
    
    public UserModel read(UserModel model){
        if ((conn = ConnectionManagement.getConnection()) == null)
            return null;
        
        String querySelect = String.format("SELECT cpf, nome, data_nascimento FROM %s WHERE cpf = ?",
                ApiTestConfigs.USER_TABLE);
        
        try {
            PreparedStatement ps = conn.prepareStatement(querySelect);
            
            ps.setInt(1, model.getCpf());
            
            ResultSet rs = ps.executeQuery();
            
            UserModel user = null;
            
            while (rs.next()){
                user = new UserModel();
                user.setCpf(rs.getInt(1));
                user.setNome(rs.getString(2));
                user.setDataNascimento(rs.getDate(3));
            }
            
            rs.close();
            ConnectionManagement.closeStatement(ps);
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
