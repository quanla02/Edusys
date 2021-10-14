/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ciuz
 */
public class XJdbc {
    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException{
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement stm;
        if(sql.trim().startsWith("{")){
            stm = conn.prepareCall(sql);            
        }else{
            stm= conn.prepareStatement(sql);
        }
        for(int i =0;i<args.length;i++){
            stm.setObject(i+1, args[i]);
        }
        return stm;
    }
    public static int update(String sql,Object... args){
        try {
            PreparedStatement stm = XJdbc.getStmt(sql, args);
            return stm.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet query(String sql, Object ... args) throws SQLException{
        PreparedStatement stm = XJdbc.getStmt(sql, args);
        return stm.executeQuery();
    }
    public static Object value(String sql, Object... args){
        try {
            ResultSet rs =XJdbc.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
        rs.getStatement().getConnection().close();
        return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
