/*
............/´¯/)........... (\¯'\
............/....//........... ...\\....\
.........../....//............ ....\\....\
...../´¯/..../´¯\.........../¯ '\....\¯'\
.././.../..../..../.|_......_| .\....\....\...\.\..
(.(....(....(..../.)..)..(..(. \....)....)....).)
.\................\/.../....\. ..\/................/
..\................. /........\..................../
....\..............(.......... ..)................/           


 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChucVu;
import ultility.DBContext;
import viewModel.ViewModeChucVu;

/**
 *
 * @author Admin
 */
public class ChucVuRepository {

    public List<ViewModeChucVu> getAll() {
        try {
            ArrayList<ViewModeChucVu> listChucVu = new ArrayList<>();
            Connection con = DBContext.getConnection();
            String sql = "SELECT MaCV, TenCV\n"
                    + "FROM     ChucVu WHERE TrangThai = '0'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ViewModeChucVu cv = new ViewModeChucVu();
                cv.setMaCv(rs.getString(1));
                cv.setTenCv(rs.getString(2));
                listChucVu.add(cv);
            }
            rs.close();
            ps.close();
            con.close();
            return listChucVu;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Integer them(ChucVu cv) {
        try {
            Connection con = DBContext.getConnection();
            String sql = "INSERT INTO ChucVu (MaCV, TenCV, TrangThai)\n"
                    + "VALUES (?,?,0)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,cv.getMaCv());
            ps.setString(2,cv.getTenCv());
            Integer ketQua=ps.executeUpdate();
            return ketQua;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Integer sua(ChucVu cv,String ma) {
        try {
            Connection con = DBContext.getConnection();
            String sql = "UPDATE ChucVu SET  TenCV =? where MaCV=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(2,ma);
            ps.setString(1,cv.getTenCv());
            Integer ketQua=ps.executeUpdate();
            return ketQua;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<ViewModeChucVu> getAll1(){
        try {
            ArrayList<ViewModeChucVu> listChucVu = new ArrayList<>();
            Connection con = DBContext.getConnection();
            String sql = "SELECT MaCV, TenCV\n"
                    + "FROM     ChucVu  WHERE  TrangThai = '1'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               ViewModeChucVu cv = new ViewModeChucVu();
                cv.setMaCv(rs.getString(1));
                cv.setTenCv(rs.getString(2));
                listChucVu.add(cv);
            }
            rs.close();
            ps.close();
            con.close();
            return listChucVu;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Integer delete(String ma){
          try {
            Connection con = DBContext.getConnection();
            String sql = "UPDATE ChucVu SET  TrangThai = '1' where MaCV=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,ma);
            Integer ketQua=ps.executeUpdate();
            return ketQua;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Integer delete1(String ma){
          try {
            Connection con = DBContext.getConnection();
            String sql = "UPDATE ChucVu SET  TrangThai = '0' where MaCV=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,ma);
            Integer ketQua=ps.executeUpdate();
            return ketQua;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
