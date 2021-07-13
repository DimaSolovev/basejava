package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Ex<T> {
    T execute(PreparedStatement preparedStatement) throws SQLException;
}
