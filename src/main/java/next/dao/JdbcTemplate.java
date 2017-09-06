package next.dao;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunchanlee on 2017. 9. 6..
 */
public abstract class JdbcTemplate {

    public List query(String sql) throws SQLException {
        List list = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            setValues(pstmt);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                list.add(mapRow(rs));
            }
        } finally {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        }

        return list;
    }

    public Object queryForObject(String sql) throws SQLException {
        List list = query(sql);
        if(list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    public void update(String sql) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            setValues(pstmt);

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    abstract Object mapRow(ResultSet rs) throws SQLException;
    abstract void setValues(PreparedStatement pstmt) throws SQLException;
}
