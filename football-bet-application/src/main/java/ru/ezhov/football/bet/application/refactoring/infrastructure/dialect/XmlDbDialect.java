package ru.ezhov.football.bet.application.refactoring.infrastructure.dialect;

class XmlDbDialect implements DbDialect {

    private String pathToXmlDileDialect;

    public XmlDbDialect(String pathToXmlDileDialect) throws XmlDialectException {
        this.pathToXmlDileDialect = pathToXmlDileDialect;
        load();
    }

    private void load() throws XmlDialectException {

    }

    @Override
    public ConnectionInfo getConnectionInfo() {
        return new DefaultConnectionInfo();
    }

    @Override
    public QueriesInfo getQueriesInfo() {
        return new DefaultQueriesInfo();
    }
}
