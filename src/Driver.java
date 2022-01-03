/**
 * Main driver class, used to run the program.
 * @author tydan
 *
 */
public class Driver {
	public static void main(String[] args) {
		String url = "jdbc:sqlserver://localhost:1433";
		String username = "sa";
		String pw = "password";
		
		SQLDatabaseConnection myConnection = 
				new SQLDatabaseConnection(url, username, pw);
		
        GUI myGui = new GUI(myConnection);
        myGui.start();	
	}
}
