package dal;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private final SQLServerDataSource dataSource;

    /**
     * Constructs a new ConnectionManager with the necessary configurations for connecting to the database.
     *
     * This constructor creates a new ConnectionManager instance and initializes the underlying SQLServerDataSource
     * with the server name, database name, user credentials, port number, and trust server certificate configuration.
     *
     * This class is designed to manage database connections using SQLServerDataSource.
     */
    public ConnectionManager() {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("MyTunesNT");
        dataSource.setUser("CSe2023b_e_21");
        dataSource.setPassword("CSe2023bE21#23");
        dataSource.setPortNumber(1433);
        dataSource.setTrustServerCertificate(true);
    }


    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    /**
     * The main method for testing database connection setup.
     *
     * This method demonstrates the setup of a database connection using the ConnectionManager class.
     * It initializes a ConnectionManager, obtains a connection, prints a success message, and then closes the connection.
     *
     * @param args The command-line arguments passed to the program (not used in this example).
     *
     * @throws SQLException If an SQL exception occurs during database connection or closure.
     *
     * This method is for testing purposes and can be modified based on specific application requirements.
     */
    public static void main(String[] args) throws SQLException {
    /*ConnectionManager databaseConnection = new ConnectionManager();
    Connection connection = databaseConnection.getConnection();
    System.out.println("Yaaay!");
    connection.close();*/
    }

}