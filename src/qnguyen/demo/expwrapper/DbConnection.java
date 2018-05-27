package qnguyen.demo.expwrapper;

public class DbConnection {

//    public DbConnection getDbConnection(String username, String password) {
//        try {
//            return new DbProvider().getConnection(username, password);
//        } catch (DbConnectionException dce) {
//            throw new RuntimeException(dce);
//        }
//    }

    // fix
    public DbConnection getDbConnection(String username, String password) {
        return RuntimeExceptionWrapper.wrap(() -> new DbProvider().getConnection(username, password));
    }


    // helpers
    private class DbProvider {
        public DbConnection getConnection(String username, String password) throws DbConnectionException {
            return null;
        }
    }

    private class DbConnectionException extends Exception {}
}
