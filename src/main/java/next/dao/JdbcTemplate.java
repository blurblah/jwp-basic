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
public class JdbcTemplate {

    @SuppressWarnings("rawtypes")
    public List query(String sql, PreparedStatementSetter pstmtSetter,
                      RowMapper rowMapper) throws SQLException {
        List<Object> list = new ArrayList<Object>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            if(pstmtSetter != null) {
                pstmtSetter.setValues(pstmt);
            }
            rs = pstmt.executeQuery();

            while(rs.next()) {
                list.add(rowMapper.mapRow(rs));
            }
        } finally {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        }

        return list;
    }

    @SuppressWarnings("rawtypes")
    public Object queryForObject(String sql, PreparedStatementSetter pstmtSetter,
                                 RowMapper rowMapper) throws SQLException {
        List list = query(sql, pstmtSetter, rowMapper);
        if(list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmtSetter.setValues(pstmt);

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
}
