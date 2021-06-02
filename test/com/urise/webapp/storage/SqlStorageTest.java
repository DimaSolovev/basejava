package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class SqlStorageTest extends AbstractStorageTest{

    public SqlStorageTest() {
        super(new SqlStorage("jdbc:postgresql://localhost:5432/","postgres","postgres"));
    }
}