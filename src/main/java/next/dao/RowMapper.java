package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sunchanlee on 2017. 9. 6..
 */
public interface RowMapper {
    Object mapRow(ResultSet rs) throws SQLException;
}
