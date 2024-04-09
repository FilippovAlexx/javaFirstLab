package UserException;

import java.sql.SQLException;

public class DBException extends SQLException {
    public DBException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public DBException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public DBException(String reason) {
        super(reason);
    }

    public DBException() {
    }

    public DBException(Throwable cause) {
        super(cause);
    }

    public DBException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public DBException(String reason, String sqlState, Throwable cause) {
        super(reason, sqlState, cause);
    }

    public DBException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, sqlState, vendorCode, cause);
    }
}
