/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ultility;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamtuyetnga
 */
public class JDBCHeper {

    public static ResultSet excuteQuery(String sql, Object... args) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = DBContext.getConnection();
            ps = con.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
        } catch (SQLException ex) {
        }
        return resultSet;
    }

    public static Integer excuteUpate(String sql, Object... args) {
        Connection con = null;
        PreparedStatement ps = null;
        Integer row = 0;
        try {
            con = DBContext.getConnection();
            ps = con.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            row = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return row;
    }
}
