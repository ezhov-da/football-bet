package ru.ezhov.football.bet.application.refactoring.infrastructure.dialect;

public class DialectContext {

    private static DbDialect dbDialect;

    private DialectContext() {
    }

    public static void load(String pathToXmlFileDialect) throws LoadingDialectException {
        synchronized (dbDialect) {
            if (dbDialect == null) {
                try {
                    dbDialect = new XmlDbDialect(pathToXmlFileDialect);
                } catch (Exception ex) {
                    throw new LoadingDialectException("Не удалось загрузить диалект: [" + pathToXmlFileDialect + "]", ex);
                }
            }
        }
    }

    public static DbDialect getDbDialect() throws LoadingDialectException {
        synchronized (dbDialect) {
            if (dbDialect == null) {
                throw new LoadingDialectException("Диалект не загружен, воспользуйтесь методом [load] для загрузки диалекта");
            } else {
                return dbDialect;
            }
        }
    }
}
