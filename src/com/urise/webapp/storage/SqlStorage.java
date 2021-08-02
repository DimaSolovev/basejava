package com.urise.webapp.storage;

import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.SqlHelper;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {

    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void update(Resume resume) {
        String resumeUuid = resume.getUuid();
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resumeUuid);
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(resumeUuid);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE  FROM contact WHERE resume_uuid=?")) {
                ps.setString(1, resumeUuid);
                ps.execute();
            }
            insertContacts(resume, conn);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    insertContacts(r, conn);
                    insertSections(r, conn);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {

        return sqlHelper.transactionalExecute(conn -> {
            Resume resume = null;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r WHERE r.uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    resume = new Resume(rs.getString("uuid"), rs.getString("full_name"));
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact c WHERE c.resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, resume);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM sections s WHERE s.resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public List<Resume> getAllSorted() throws IOException {

        Map<String, Resume> resumes = new LinkedHashMap<>();
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = resumes.get(rs.getString("resume_uuid"));
                    addContact(rs, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM sections")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = resumes.get(rs.getString("resume_uuid"));
                    addSection(rs, resume);
                }
            }
            return null;
        });
        return new ArrayList<>(resumes.values());
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private static void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void addSection(ResultSet rs, Resume r) throws SQLException {
        SectionType type = SectionType.valueOf(rs.getString("type"));
        String content = rs.getString("content");
        if (content != null) {
            Section section = null;
            if (type == SectionType.PERSONAL || type == SectionType.OBJECTIVE) {
                section = new TextSection(rs.getString("content"));
            } else {
                List<String> sectionList = new ArrayList<>();
                String[] arr = content.split(" ");
                sectionList.addAll(Arrays.asList(arr));
                section = new ListSection(sectionList);
            }
            r.addSection(type, section);
        }
    }

    private void insertContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSections(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO sections (resume_uuid, type, content) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {

                SectionType sectionType = e.getKey();

                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
                    TextSection textSection = (TextSection) e.getValue();
                    ps.setString(3, textSection.getContent());
                } else {
                    ListSection listSection = (ListSection) e.getValue();
                    String con = new String("");
                    for (String s : listSection.getItems()) {
                        //  con = con + "\\n";
                        con = con + " ";
                    }
                    ps.setString(3, con);
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
