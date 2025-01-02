package tb130.relational.bibtex.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class handles all the database access
 */
public class DBAccess {

    private static Logger log = LogManager.getLogger(DBAccess.class);
    private final PreparedStatement addRecord;
    private final PreparedStatement getQuery;
    private final PreparedStatement remQuery;
    private Connection conn;

    /**
     * Constructor to innitiate the DB connection. Settings are to be saved in a DBconfig.json
     *
     * @param jdbcurl  database url
     * @param username database users username
     * @param password database users password
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DBAccess(final String jdbcurl, final String username, final String password) throws ClassNotFoundException, SQLException {
        String insertQueryStatement = "INSERT INTO bibtex_db VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON CONFLICT DO NOTHING";
        String getQueryStatement = "SELECT * FROM bibtex_db WHERE cite_key = (?)";
        String remQueryStatement = "DELETE FROM bibtex_db WHERE cite_key = (?)";
        ;
        Class.forName("org.postgresql.Driver");
        log.debug("PostgreSQL JDBC Driver Registered!");

        conn = DriverManager.getConnection(jdbcurl, username, password);
        if (conn != null) {
            log.debug("Connection Successful");
        } else {
            log.error("Failed to make connection!");
        }
        addRecord = conn.prepareStatement(insertQueryStatement);
        getQuery = conn.prepareStatement(getQueryStatement);
        remQuery = conn.prepareStatement(remQueryStatement);
    }

    /**
     * This method prepares a statement for adding Records to the Database
     *
     * @param record
     * @throws SQLException
     */
    private void prepareStatement(String[] record) throws SQLException {
        for (int i = 0; i < record.length; i++) {
            addRecord.setString(i + 1, record[i]);
        }
    }

    /**
     * Inserts a Record into the Database
     *
     * @param record the values of a Record that is to be added
     * @throws SQLException
     */
    protected void addRecordToDB(String[] record) throws SQLException {
        prepareStatement(record);
        addRecord.executeUpdate();
        log.info("one line added successfully");
    }

    /**
     * Batch Inserts Records into the Database. Intended use is for reading BibTeX-Files, then batch uploading them
     *
     * @param batch List of the String arrays containing the batch to be added
     * @throws SQLException
     */
    protected void addBatchToDB(List<String[]> batch) throws SQLException {

        conn.setAutoCommit(false);
        for (String[] query : batch) {
            prepareStatement(query);
            addRecord.addBatch();
        }

        int[] result = addRecord.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);
        log.info(result.length + "lines added successfully");
    }

    /**
     * This method sends a query to the database with the key, then removes it
     *
     * @param key key of the Record that is to be deleted
     * @return ResultSet of the query
     * @throws SQLException
     */
    protected void remRecordFromDB(final String key) throws SQLException {
        remQuery.setString(1, key);
        remQuery.executeUpdate();
    }

    /**
     * This method sends a query to the database with the key, then returns the ResultSet for further
     * handling in the write functions
     *
     * @param key key of the requested Record
     * @return ResultSet of the query
     * @throws SQLException
     */
    protected Set<String[]> getRecordsFromDB(final Set<String> key) throws SQLException {
        log.info("sending Queries");
        Set<String[]> resSet = new HashSet<>();
        for(String str: key){
            getQuery.setString(1, str);
            ResultSet rs = getQuery.executeQuery();
            while (rs.next()) {
                String[] result = new String[23];
                for (int i = 0; i < result.length; i++) {
                    String temp = rs.getString(i + 1);
                    result[i] = temp;
                }
                log.info("DB Query Result : \n" + String.join(", ", result[0]));
                resSet.add(result);
            }
        }
        return resSet;
    }
}
