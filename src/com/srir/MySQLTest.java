package com.srir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;


public class MySQLTest {
 // Tworzymy po³¹czenie z baz¹ danych
	
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
 
 
 
 // Wyœwietl wszystkie dane z tabeli
 String viewTable() throws SQLException {
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

void updateZa() throws SQLException {
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
void updateBz() throws SQLException {
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

void updatePrzeciw() throws SQLException {
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