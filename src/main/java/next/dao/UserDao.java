package next.dao;

import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return null;
            }

            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };
        template.update(sql);
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET password=?, name=?, email=? WHERE userId=?";
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return null;
            }

            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        };
        template.update(sql);
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT userId, password, name, email FROM users";
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"),
                        rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            @Override
            void setValues(PreparedStatement pstmt) {

            }
        };
        return template.query(sql);
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"),
                        rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };
        return (User)template.queryForObject(sql);
    }
}
