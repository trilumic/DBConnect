import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

/**
 * Die Klasse DBConnect stellt eine Datenbankanbindung zur Verfügung. Sie besitzt Methoden zum Einfügen und Auslesen von Daten sowie Getter und Setter
 * für wichtige Parameter und Konstanten.
 * @author emictr
 *
 */
public class DBConnect {
	
	private String USER = "root";
	private final String URL = "jdbc:mysql://localhost";
	private String DB = "/dbconnect_test";
	private String PWD = "1234";
	
	private String QUERY;
	
	private Vector<String> queryResult;
	
	private Vector<String> columnName;
	
	
	private static DBConnect instance = new DBConnect();
	
	protected Connection connection;
	protected Statement statement;
	protected ResultSet result;
	
	
	private DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		this.columnName = new Vector<String>();
		
		try{
			connection = DriverManager.getConnection(URL + DB, USER, PWD); //DB-Parameter
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public Vector<String> showResult(){
		try{
			statement = connection.createStatement();
			ResultSetMetaData meta = result.getMetaData();
			if(result != null){
				while(result.next()){
//					for(int i = 0; i < meta.getColumnCount(); i++){
						System.out.println(result.getInt(columnName.elementAt(0)));
						System.out.println(result.getString(columnName.elementAt(1)));
						
//						queryResult.add(result.getString(columnName.elementAt(i)));
//						
//						}
//					}
//				for(String str: queryResult){
//					System.out.println(str);
//				}
				}
				
			} 
		}
		
			catch(SQLException e){
			e.printStackTrace();
		}
		return queryResult;
	}
	
	public ResultSet select(String selectQuery){
		try{
			statement = connection.createStatement();
			result = statement.executeQuery(selectQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void executeQuery(){
		try{
			statement = connection.createStatement();
			statement.executeUpdate(QUERY);
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void DBClose(){
		try {
			this.connection.close();
			this.result.close();
			this.statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static DBConnect getInstance(){
		return instance;
	}
	
	public void setColumnName(int position, String name){
		System.out.println(position);
		columnName.insertElementAt(name, position);

//		columnName.removeElementAt(position);
		
	}
	
	public void setQuery(String query){
		this.QUERY = query;
	}
	
	public void setDatabase(String database){
		this.DB = database;
	}
	
	public String getDatabaseName(){
		return this.DB;
	}
	
	public void setUser(String user){
		this.USER = user;
	}
	
	public void setPassword(String password){
		this.PWD = password;
	}
	
	public ResultSet getResultSet(){
		return this.result;
	}
	
	
}
