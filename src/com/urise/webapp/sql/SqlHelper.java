package com.urise.webapp.sql;

public class SqlHelper {

    public ConnectionFactory connectionFactory;

    public void execute(String sql){

    }

}
//try (Connection conn = connectionFactory.getConnection();
//        PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
//        ps.setString(1, r.getUuid());
//        ps.setString(2, r.getFullName());
//        ps.execute();
//        } catch (SQLException e) {
//        throw new StorageException(e);
//        }