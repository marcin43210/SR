package com.srir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

/// Klasa impementuj�ca funkcjonalno�� bazy danych MySQL
	/** W klasie tej znajduj� si� implementacje funkcji 
	 * bazy danych (pobieranie warto�ci, aktualizowanie g�os�w) */

public class MySQLTest {
 
	/// Funkcja po��czenia z baz� danych
		/** Funkcja ta zapewnia ��czno�� pomi�dzy serwerem a baz� danych MySQL. Podawane s� tutaj dane dost�powe do bazy oraz jej adres. */
	
 public Connection getConnection() throws SQLException { 
     Connection conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + databaseName + 
             "?useUnicode=true" +
             "&characterEncoding=utf-8" +
             "&user=" + userName + 
             "&password=" + password);
     System.out.println("Po��czono do bazy " + databaseName);
     return conn; 
 }
  
 Connection databaseConnection;
 String userName = "root", 
        password = "pass",
        serverName = "localhost",
        portNumber = "3306",
        databaseName = "srir";
 
 
/// Funkcja wy�wietlania tre�ci uchwa�y
	/**Funkcja ta odpowiada za pobranie tre�ci uchwa�y z bazy danych i zwr�cenie jej w postaci Stringa */
public String viewTable() throws SQLException {
  String query = "select tresc from sr where id=1";
  String tresc = null;
 
  Statement stmt = null;
  try {
   stmt = (Statement) databaseConnection.createStatement();
   ResultSet rs = stmt.executeQuery(query);
   // Dop�ki zbi�r wynik�w posiada jakie� dane to wypisuj
  while(rs.next())
             tresc = rs.getString("tresc");
  			
             
    
  } catch (SQLException e) 
  {
   e.printStackTrace();
  } finally {
      // Zamknij obiekt Statement, aby natychmiast zwolni� jego pami��
    if (stmt != null) { stmt.close(); }
  }
  return tresc;
 }
/// Funkcja wy�wietlania liczby g�os�w
	/**Funkcja ta odpowiada za pobranie ilo�ci poszczeg�lnych g�os�w z bazy danych i zwr�cenie ich w postaci Stringa */
 	public String viewVotes() throws SQLException {
	  String query = "select za, przeciw, wstrz from sr where id=1";
	  int za=0;
	  int przeciw=0;
	  String wstrz =null;
	 
	  Statement stmt = null;
	  try {
	   stmt = (Statement) databaseConnection.createStatement();
	   ResultSet rs = stmt.executeQuery(query);
	   // Dop�ki zbi�r wynik�w posiada jakie� dane to wypisuj
	  while(rs.next()){
	             za =rs.getInt("za");
	  przeciw =rs.getInt("przeciw");
	  wstrz =rs.getString("wstrz");
	  }
	  System.out.println(za +"   "+ przeciw+ "   " + wstrz);
	  			
	             
	    
	  } catch (SQLException e) 
	  {
	   e.printStackTrace();
	  } finally {
	      // Zamknij obiekt Statement, aby natychmiast zwolni� jego pami��
	    if (stmt != null) { stmt.close(); }
	  }
	  return ("WYNIKI: Glosow za: " +za + " Glosow przeciw: " + przeciw + " Wstrzymalo sie od glosow: " + wstrz);
	 }
 
 	/// Funkcja g�osowania za uchwa��
 		/**Funkcja ta odpowiada za zaktualizowanie kom�rki "za" w bazie danych (odnosz�ce si� do uchwa�y, nad kt�r� trwa g�osowanie) */
public void updateZa() throws SQLException {
 String query = "UPDATE sr SET za=za+1";
 Statement stmt = null;
 try {
  stmt = (Statement) databaseConnection.createStatement(); 
     int rows = stmt.executeUpdate(query);
     
 } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 } finally {
     // Zamknij obiekt Statement, aby natychmiast zwolni� jego pami��
   if (stmt != null) { stmt.close(); }
 }
}
/// Funkcja wstrzymania si� od g�osu
	/**Funkcja ta odpowiada za zaktualizowanie kom�rki "Brak zdania" w bazie danych (odnosz�ce si� do uchwa�y, nad kt�r� trwa g�osowanie) */
public void updateBz() throws SQLException {
	 String query = "UPDATE sr SET wstrz=wstrz+1";
	 Statement stmt = null;
	 try {
	  stmt = (Statement) databaseConnection.createStatement(); 
	     int rows = stmt.executeUpdate(query);
	    
	 } catch (SQLException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 } finally {
	     // Zamknij obiekt Statement, aby natychmiast zwolni� jego pami��
	   if (stmt != null) { stmt.close(); }
	 }
	}
/// Funkcja g�osowania przeciw uchwale
	/**Funkcja ta odpowiada za zaktualizowanie kom�rki "przeciw" w bazie danych (odnosz�ce si� do uchwa�y, nad kt�r� trwa g�osowanie) */
public void updatePrzeciw() throws SQLException {
	 String query = "UPDATE sr SET przeciw=przeciw+1";
	 Statement stmt = null;
	 try {
	  stmt = (Statement) databaseConnection.createStatement(); 
	     int rows = stmt.executeUpdate(query);
	     
	 } catch (SQLException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 } finally {
	     // Zamknij obiekt Statement, aby natychmiast zwolni� jego pami��
	   if (stmt != null) { stmt.close(); }
	 }
	}

}