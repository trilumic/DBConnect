import java.sql.DatabaseMetaData;
import java.sql.ResultSet;


public class Test {
	
	
	
	public static void main(String[] args) {
		
		//Singleton-Instanz von DBConnect
		DBConnect database = DBConnect.getInstance();
		
		//Setzen der Werte, die ausgegeben werden sollen
		database.setColumnName(0, "ID_Spieler");
		database.setColumnName(1, "Spielername");
		
		
		//Query festlegen und ausführen
		database.setQuery("Insert into spieler(Spielername) values ('Moritz'), ('Stan');");
		database.executeQuery();
		
		//Select-Query
		database.result = database.select("Select * from spieler");
		
		//Anzeigen
		database.showResult();
		
	}
}
