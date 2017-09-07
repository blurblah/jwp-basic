package next.dao;

import core.jdbc.ConnectionManager;
import next.model.DataAccessException;

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
    public <T> List<T> query(String sql, PreparedStatementSetter pstmtSetter,
                      RowMapper<T> rowMapper) throws DataAccessException {
        List<T> list = new ArrayList<T>();
        ResultSet rs = null;
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            if(pstmtSetter != null) {
                pstmtSetter.setValues(pstmt);
            }
            rs = pstmt.executeQuery();

            while(rs.next()) {
                list.add(rowMapper.mapRow(rs));
            }
        } catch(SQLException e) {
            throw new DataAccessException(e.getMessage(), e.getCause());
        } finally {
            try {
                if(rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException(e.getMessage(), e.getCause());
            }
        }

        return list;
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper,
                             Object... values) throws DataAccessException {
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                for(int i = 0; i < values.length; i++) {
                    pstmt.setObject(i+1, values[i]);
                }
            }
        };
        return query(sql, pss, rowMapper);
    }

    @SuppressWarnings("rawtypes")
    public <T> T queryForObject(String sql, PreparedStatementSetter pstmtSetter,
                                 RowMapper<T> rowMapper) throws DataAccessException {
        List<T> list = query(sql, pstmtSetter, rowMapper);
        if(list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper,
                                Object... values) throws DataAccessException {
        List<T> list = query(sql, rowMapper, values);
        if(list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    public void update(String sql, PreparedStatementSetter pstmtSetter) {
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmtSetter.setValues(pstmt);

            pstmt.executeUpdate();
        } catch(SQLException e) {
            throw new DataAccessException(e.getMessage(), e.getCause());
        }
    }
}
