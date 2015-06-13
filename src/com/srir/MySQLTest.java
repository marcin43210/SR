package com.srir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

/// Klasa impementuj¹ca funkcjonalnoœæ bazy danych MySQL
	/** W klasie tej znajduj¹ siê implementacje funkcji 
	 * bazy danych (pobieranie wartoœci, aktualizowanie g³osów) */

public class MySQLTest {
 
	/// Funkcja po³¹czenia z baz¹ danych
		/** Funkcja ta zapewnia ³¹cznoœæ pomiêdzy serwerem a baz¹ danych MySQL. Podawane s¹ tutaj dane dostêpowe do bazy oraz jej adres. */
	
 public Connection getConnection() throws SQLException { 
     Connection conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + databaseName + 
             "?useUnicode=true" +
             "&characterEncoding=utf-8" +
             "&user=" + userName + 
             "&password=" + password);
     System.out.println("Po³¹czono do bazy " + databaseName);
     return conn; 
 }
  
 Connection databaseConnection;
 String userName = "root", 
        password = "pass",
        serverName = "localhost",
        portNumber = "3306",
        databaseName = "srir";
 
 
/// Funkcja wyœwietlania treœci uchwa³y
	/**Funkcja ta odpowiada za pobranie treœci uchwa³y z bazy danych i zwrócenie jej w postaci Stringa */
public String viewTable() throws SQLException {
  String query = "select tresc from sr where id=1";
  String tresc = null;
 
  Statement stmt = null;
  try {
   stmt = (Statement) databaseConnection.createStatement();
   ResultSet rs = stmt.executeQuery(query);
   // Dopóki zbiór wyników posiada jakieœ dane to wypisuj
  while(rs.next())
             tresc = rs.getString("tresc");
  			
             
    
  } catch (SQLException e) 
  {
   e.printStackTrace();
  } finally {
      // Zamknij obiekt Statement, aby natychmiast zwolniæ jego pamiêæ
    if (stmt != null) { stmt.close(); }
  }
  return tresc;
 }
/// Funkcja wyœwietlania liczby g³osów
	/**Funkcja ta odpowiada za pobranie iloœci poszczególnych g³osów z bazy danych i zwrócenie ich w postaci Stringa */
 	public String viewVotes() throws SQLException {
	  String query = "select za, przeciw, wstrz from sr where id=1";
	  int za=0;
	  int przeciw=0;
	  String wstrz =null;
	 
	  Statement stmt = null;
	  try {
	   stmt = (Statement) databaseConnection.createStatement();
	   ResultSet rs = stmt.executeQuery(query);
	   // Dopóki zbiór wyników posiada jakieœ dane to wypisuj
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
	      // Zamknij obiekt Statement, aby natychmiast zwolniæ jego pamiêæ
	    if (stmt != null) { stmt.close(); }
	  }
	  return ("WYNIKI: Glosow za: " +za + " Glosow przeciw: " + przeciw + " Wstrzymalo sie od glosow: " + wstrz);
	 }
 
 	/// Funkcja g³osowania za uchwa³¹
 		/**Funkcja ta odpowiada za zaktualizowanie komórki "za" w bazie danych (odnosz¹ce siê do uchwa³y, nad któr¹ trwa g³osowanie) */
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
     // Zamknij obiekt Statement, aby natychmiast zwolniæ jego pamiêæ
   if (stmt != null) { stmt.close(); }
 }
}
/// Funkcja wstrzymania siê od g³osu
	/**Funkcja ta odpowiada za zaktualizowanie komórki "Brak zdania" w bazie danych (odnosz¹ce siê do uchwa³y, nad któr¹ trwa g³osowanie) */
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
	     // Zamknij obiekt Statement, aby natychmiast zwolniæ jego pamiêæ
	   if (stmt != null) { stmt.close(); }
	 }
	}
/// Funkcja g³osowania przeciw uchwale
	/**Funkcja ta odpowiada za zaktualizowanie komórki "przeciw" w bazie danych (odnosz¹ce siê do uchwa³y, nad któr¹ trwa g³osowanie) */
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
	     // Zamknij obiekt Statement, aby natychmiast zwolniæ jego pamiêæ
	   if (stmt != null) { stmt.close(); }
	 }
	}

}