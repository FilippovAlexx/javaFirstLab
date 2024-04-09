package Constants;

public class ConstantsJDBC {
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/java_first_lab";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PASSWORD = "0000";
    public static final String SELECT_ALL_PERSONS = "SELECT * FROM person WHERE login = ?";
    public static final String INSERT_NEW_PERSON = "INSERT INTO person (id, login, password) VALUES (?, ?, ?)";
    public static final String SELECT_ALL_DATA = "SELECT id, login, password FROM person";
    public static final String DELETE_PERSON = "DELETE FROM person WHERE id = ?";
    public static final String CHANGE_PERSON = "UPDATE person SET login=?, password=? WHERE id=?";
    public static final String FIND_MAX_ID = "SELECT MAX(id) as max_id FROM person";
    public static final String GET_BY_ID = "SELECT login, password FROM person WHERE id = ?";
    public static final String SELECT_BY_ID = "SELECT * FROM person WHERE id = ?";
}
