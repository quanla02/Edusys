/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.HocVien;
import com.edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ciuz
 */
public class HocVienDAO extends EduSysDAO<HocVien, String> {

    private String INSERT_SQL = "INSERT INTO HocVien(MaKH,MaNH,Diem) "
            + "values(?,?,?)";
    private String UPDATE_SQL = "UPDATE HocVien SET MaKH =?,MaNH=?,Diem=? WHERE MaHV =?";
    private String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV=?";
    private String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    private String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE MaHV =?";

    @Override
    public void insert(HocVien entity) {
        XJdbc.update(INSERT_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        XJdbc.update(UPDATE_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiem(), entity.getMaHV());
    }

    @Override
    public void delete(String key) {
        XJdbc.update(DELETE_SQL, key);
    }

    @Override
    public List<HocVien> getAll() {
        List<HocVien> lst = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(SELECT_ALL_SQL);
            while(rs.next()){
                int maHV = rs.getInt("MaHV");
               String maNH = rs.getString("MaNH");
               int maKH = rs.getInt("MaKH");
               float diem =rs.getFloat("Diem");
               HocVien hv = new HocVien(maHV, maKH, maNH, diem);
               lst.add(hv);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    @Override
    public HocVien findById(String key) {
        List<HocVien> lst = getBySql(SELECT_BY_ID_SQL, key);
        if(lst.isEmpty()){
            return null;
        }
        return lst.get(0);
    }

    @Override
    public List<HocVien> getBySql(String sql, Object... args) {
        List<HocVien> lst = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql,args);
            while(rs.next()){
                int maHV = rs.getInt("MaHV");
               String maNH = rs.getString("MaNH");
               int maKH = rs.getInt("MaKH");
               float diem =rs.getFloat("Diem");
               HocVien hv = new HocVien(maHV, maKH, maNH, diem);
               lst.add(hv);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lst;
    }
    public List<HocVien> selectByKhoaHoc(int maKH){
        String sql = "SELECT * FROM HocVien WHERE MaKH=?";
        return this.getBySql(sql, maKH);
    }
            
}
