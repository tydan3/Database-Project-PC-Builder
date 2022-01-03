import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseConnection {
	// Connection info
	private String url;
	private String user;
	private String pw;
	// Database name
	private static final String db = "ty_daniel_db";

	/**
	 * Constructor.
	 * @param theUrl connection url
	 * @param theUsername connection username
	 * @param thePassword connection password
	 */
    public SQLDatabaseConnection(String theUrl, String theUsername,
    		String thePassword) {
    	this.url = theUrl;
    	this.user = theUsername;
    	this.pw = thePassword;
  
    }
    
    public String executeForm(String theForm) {
        StringBuilder result = new StringBuilder();
        
        // Connect
        try (Connection connection = DriverManager.getConnection(url,user,pw);
                Statement statement = connection.createStatement();) {

               // USE database defined by db field.
               String useDb = "USE " + this.db;
               statement.addBatch(useDb);
               
               // add form statements to batch for execution
               statement.addBatch(theForm);
               statement.executeBatch();
               if(!theForm.isEmpty())
            	   result.append("Successful change form request");
           }

           // Handle any errors that may have occurred.
           catch (SQLException e) {
               e.printStackTrace();
               result.setLength(0);
               result.append("invalid change form request");
           }
        return result.toString();
    }
    
    public String executeReport(String theReport) {
        StringBuilder result = new StringBuilder();
    	ResultSet resultSet = null;
    	
        // Connect
        try (Connection connection = DriverManager.getConnection(url,user,pw);
                Statement statement = connection.createStatement();) {

               // USE database defined by db field.
               String useDb = "USE " + this.db;
               statement.addBatch(useDb);
               statement.executeBatch();
               
               // execute SELECT statement
               resultSet = statement.executeQuery(theReport);
               
               // meta data for column count
               ResultSetMetaData metaData = resultSet.getMetaData();
               int colCount = metaData.getColumnCount();             

               // iterate over each row in result set
               while (resultSet.next()) {
            	   // loop through columns
            	   for (int i = 1; i <= colCount; i++) {
            		   if (i == 1) {
            			   result.append(metaData.getColumnName(1) + ": ");
            			   result.append(resultSet.getString(1));
            		   } else {
            			   result.append("\n" + metaData.getColumnName(i) + ": ");
                           result.append(resultSet.getString(i));
            		   }
            	   }
                   result.append("\n\n");
               }
           }

           // Handle any errors that may have occurred.
           catch (SQLException e) {
               e.printStackTrace();
               result.setLength(0);
               result.append("invalid view report request");
           }
        return result.toString();

    }
    
}