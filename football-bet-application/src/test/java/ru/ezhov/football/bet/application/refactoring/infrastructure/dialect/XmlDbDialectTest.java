package ru.ezhov.football.bet.application.refactoring.infrastructure.dialect;

import org.junit.Test;

public class XmlDbDialectTest {
    @Test
    public void getConnectionInfo() throws Exception {
        XmlDbDialect xmlDbDialect = new XmlDbDialect("mssql.db.dialect.xml");
        ConnectionInfo connectionInfo = xmlDbDialect.getConnectionInfo();
    }

    @Test
    public void getQueriesInfo() throws Exception {
    }

}