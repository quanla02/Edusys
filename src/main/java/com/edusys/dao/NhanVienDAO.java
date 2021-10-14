/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NhanVien;
import com.edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ciuz
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String> {

    private String INSERT_SQL = "INSERT INTO NhanVien(MaNV,MatKhau,HoTen,VaiTro)"
            + "values(?,?,?,?)";
    private String UPDATE_SQL = "UPDATE NhanVien SET MatKhau =?,HoTen=?,VaiTro=? WHERE MaNV =?";
    private String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    private String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    private String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MANV =?";

    @Override
    public void insert(NhanVien entity) {
        XJdbc.update(INSERT_SQL, entity.getMaNV(), entity.getMatKhau(), entity.getHoTen(), entity.isVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.update(UPDATE_SQL, entity.getMatKhau(), entity.getHoTen(), entity.isVaiTro(), entity.getMaNV());
    }

    @Override
    public void delete(String key) {
        XJdbc.update(DELETE_SQL, key);
    }

    @Override
    public List<NhanVien> getAll() {
        List<NhanVien> lst = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(SELECT_ALL_SQL);
            while (rs.next()) {
                String maNV = rs.getString("MaNV");
                String matKhau = rs.getString("MatKhau");
                String hoTen = rs.getString("HoTen");
                boolean vaiTro = rs.getBoolean("VaiTro");                
                lst.add(new NhanVien(maNV, matKhau, hoTen, vaiTro));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public NhanVien findById(String key) {
        List<NhanVien> lst = this.getBySql(SELECT_BY_ID_SQL, key);
        if (lst.isEmpty()) {
            return null;
        }
        return lst.get(0);
    }

    @Override
    public List<NhanVien> getBySql(String sql, Object... args) {
        List<NhanVien> lst = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql,args);
            while (rs.next()) {
                String maNV = rs.getString("MaNV");
                String matKhau = rs.getString("MatKhau");
                String hoTen = rs.getString("HoTen");
                boolean vaiTro = rs.getBoolean("VaiTro");                
                lst.add(new NhanVien(maNV, matKhau, hoTen, vaiTro));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

}
