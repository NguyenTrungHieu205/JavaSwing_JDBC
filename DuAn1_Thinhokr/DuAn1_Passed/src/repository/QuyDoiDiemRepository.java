/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import ultility.DBContext;
import viewModel.QuyDoiDiemViewModel;

/**
 *
 * @author Dell
 */
public class QuyDoiDiemRepository {
    
    public ArrayList<QuyDoiDiemViewModel> getAll() throws SQLException{
        ArrayList<QuyDoiDiemViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select TienTichDiem, TienTieuDiem from QuyDoi";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            QuyDoiDiemViewModel quyDoi = new QuyDoiDiemViewModel();
            quyDoi.setTienTichDiem(rs.getBigDecimal(1));
            quyDoi.setTienTieuDiem(rs.getBigDecimal(2));
            
            list.add(quyDoi);
        }
        
        return list;
    }
    
    public Integer updateQuyDoi(BigDecimal tienTichDiem, BigDecimal tienTieuDiem) throws SQLException{
        Connection con = DBContext.getConnection();
        String sql = "update QuyDoi set TienTichDiem = ?, TienTieuDiem = ? where TrangThai = 0";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setBigDecimal(1, tienTichDiem);
        ps.setBigDecimal(2, tienTieuDiem);
        
        int affectedRows = ps.executeUpdate();
        
        ps.close();
        con.close();
        
        return affectedRows;
    }
}
