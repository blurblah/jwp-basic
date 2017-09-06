package next.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by sunchanlee on 2017. 9. 6..
 */
public interface PreparedStatementSetter {
    void setValues(PreparedStatement pstmt) throws SQLException;
}
