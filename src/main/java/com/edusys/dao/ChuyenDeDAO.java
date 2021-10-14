/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import com.edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ciuz
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    private String INSERT_SQL = "INSERT INTO ChuyenDe(MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa) "
            + "values(?,?,?,?,?,?)";
    private String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD =?,HocPhi=?,ThoiLuong=?,Hinh =?,MoTa =? WHERE MaCD =?";
    private String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD=?";
    private String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
    private String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe WHERE MaCD =?";

    @Override
    public void insert(ChuyenDe entity) {
        XJdbc.update(INSERT_SQL, entity.getMaCD(), entity.getTenCD(),
                entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        XJdbc.update(UPDATE_SQL, entity.getTenCD(),
                entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(),
                entity.getMoTa(), entity.getMaCD());
    }

    @Override
    public void delete(String key) {
        XJdbc.update(DELETE_SQL, key);
    }

    @Override
    public List<ChuyenDe> getAll() {
        List<ChuyenDe> lst = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(SELECT_ALL_SQL);
            while(rs.next()){
                String maCD = rs.getString("MaCD");
                String tenCD =rs.getString("tenCD");
                float hocPhi = rs.getFloat("HocPhi");
                int thoiLuong =rs.getInt("ThoiLuong");
                String hinh =rs.getString("Hinh");
                String moTa =rs.getString("MoTa");
                ChuyenDe cd = new ChuyenDe(maCD, tenCD, hocPhi, thoiLuong, hinh, moTa);
                lst.add(cd);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    @Override
    public ChuyenDe findById(String key) {
        List<ChuyenDe> lst = this.getBySql(SELECT_BY_ID_SQL, key);
        if(lst.isEmpty()){
            return null;
        }
        return lst.get(0);
    }

    @Override
    public List<ChuyenDe> getBySql(String sql, Object... args) {
        List<ChuyenDe> lst = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                String maCD = rs.getString("MaCD");
                String tenCD =rs.getString("tenCD");
                float hocPhi = rs.getFloat("HocPhi");
                int thoiLuong =rs.getInt("ThoiLuong");
                String hinh =rs.getString("Hinh");
                String moTa =rs.getString("MoTa");
                ChuyenDe cd = new ChuyenDe(maCD, tenCD, hocPhi, thoiLuong, hinh, moTa);
                lst.add(cd);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

}
