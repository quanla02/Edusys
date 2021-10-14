/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NguoiHoc;
import com.edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ciuz
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    private String INSERT_SQL = "INSERT INTO NguoiHoc(MaNH,HoTen,MatKhau,NgaySinh,GioiTinh,DienThoai,Email,GhiChu,MaNV)"
            + " values(?,?,?,?,?,?,?,?,?)";
    private String UPDATE_SQL = "UPDATE NguoiHoc SET HoTen =?,MatKhau=?,NgaySinh=?,GioiTinh=?,DienThoai =?,Email =?,GhiChu =?,MaNV =? WHERE MaNH =?";
    private String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH=?";
    private String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
    private String SELECT_BY_ID_SQL = "SELECT * FROM NguoiHoc WHERE MaNH =?";

    @Override
    public void insert(NguoiHoc entity) {
        XJdbc.update(INSERT_SQL, entity.getMaNH(), entity.getHoTen(),entity.getMatKhau(),
                entity.getNgaySinh(), entity.isGioiTinh(), entity.getDienThoai(),
                entity.getEmail(), entity.getGhiChu(), entity.getMaNV());
    }

    @Override
    public void update(NguoiHoc entity) {
        XJdbc.update(UPDATE_SQL, entity.getHoTen(),entity.getMatKhau(),
                entity.getNgaySinh(), entity.isGioiTinh(), entity.getDienThoai(),
                entity.getEmail(), entity.getGhiChu(), entity.getMaNV(),  entity.getMaNH());
    }

    @Override
    public void delete(String key) {
        XJdbc.update(DELETE_SQL, key);
    }

    @Override
    public List<NguoiHoc> getAll() {
        List<NguoiHoc> lst = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(SELECT_ALL_SQL);
            while (rs.next()) {
                String maNH = rs.getString("MaNH");
                String hoTen = rs.getString("HoTen");
                String matKhau = rs.getString("matKhau");
                Date ngaySinh = rs.getDate("NgaySinh");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                String dienThoai = rs.getString("DienThoai");
                String email = rs.getString("email");
                String ghiChu = rs.getString("GhiChu");
                String maNV = rs.getString("MaNV");
                Date ngayDK = rs.getDate("NgayDK");
                NguoiHoc nh = new NguoiHoc(maNH, hoTen,matKhau, ngaySinh, gioiTinh, dienThoai, email, ghiChu, maNV, ngayDK);
                lst.add(nh);
            }

        } catch (Exception e) {
        }
        return lst;
    }

    @Override
    public NguoiHoc findById(String key) {
        List<NguoiHoc> lst = getBySql(SELECT_BY_ID_SQL, key);
        if (lst.isEmpty()) {
            return null;
        }
        return lst.get(0);
    }

    @Override
    public List<NguoiHoc> getBySql(String sql, Object... args) {
        List<NguoiHoc> lst = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql,args);
            while (rs.next()) {
                String maNH = rs.getString("MaNH");
                String hoTen = rs.getString("HoTen");
                String matKhau =rs.getString("MatKhau");
                Date ngaySinh = rs.getDate("NgaySinh");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                String dienThoai = rs.getString("DienThoai");
                String email = rs.getString("email");
                String ghiChu = rs.getString("GhiChu");
                String maNV = rs.getString("MaNV");
                Date ngayDK = rs.getDate("NgayDK");
                NguoiHoc nh = new NguoiHoc(maNH, hoTen,matKhau, ngaySinh, gioiTinh, dienThoai, email, ghiChu, maNV, ngayDK);
                lst.add(nh);
            }
        } catch (Exception e) {
        }
        return lst;
    }
    public List<NguoiHoc> selectByKeyword(String keyword){
        String sql = "Select * from NguoiHoc where HoTen like ?";
        return this.getBySql(sql, "%"+keyword+"%");
    }
    public List<NguoiHoc> selectNotInCourse(int makh,String keyword){
        String sql ="SELECT * FROM NguoiHoc WHERE HoTen like ? AND MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return this.getBySql(sql, "%"+keyword+"%",makh);
    }
}
