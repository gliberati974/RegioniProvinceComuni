package ESAME;

import java.awt.FlowLayout;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;



import java.sql.Connection;


public class ComboEngine {
	
	
	private Connection conn;
	
	private PreparedStatement stmtLeggiRegione;
	private PreparedStatement stmtLeggiComune;
	private PreparedStatement stmtLeggiProvincia;
	boolean varName;
	
	
	
	
	
	
	private final static String SQL_READ_REGIONE = "SELECT DISTINCT regione FROM italy_cities ORDER BY regione ASC";
	private final static String SQL_READ_PROVINCIA = "SELECT DISTINCT targa_auto FROM italy_cities WHERE regione = ? ORDER BY targa_auto ASC";
	private final static String SQL_READ_COMUNE = "SELECT comune FROM italy_cities WHERE targa_auto = ? ORDER BY comune ASC";
	
	
	//Connessione db, utilizzo la parola chiave THROWS per implementare una propagazione dell'eccezione.
	
		public ComboEngine(String serverAddr, String dbName, String username, String password) throws SQLException {
		String url = "jdbc:mysql://" + serverAddr + "/" + dbName;

		
		conn = DriverManager.getConnection(url, username, password);
		
		// Definisco tutti gli statements necessari
		stmtLeggiRegione = conn.prepareStatement(SQL_READ_REGIONE);
		stmtLeggiProvincia = conn.prepareStatement(SQL_READ_PROVINCIA);
		stmtLeggiComune = conn.prepareStatement(SQL_READ_COMUNE);
		
		
		
	}

public void close() throws SQLException, IOException {
		
		// Chiudo le risorse.
	if (stmtLeggiRegione != null)
		stmtLeggiRegione.close();
	
	if (stmtLeggiComune != null)
		stmtLeggiComune.close();
	
	if (stmtLeggiProvincia != null)
		stmtLeggiProvincia.close();
	
	if (conn != null)
		conn.close();
	}

//Metodo per andare a leggere nel database le regioni.
public ArrayList<String> leggiRegione() throws SQLException {
	ArrayList<String> aux = new ArrayList<String>();
	try(ResultSet rs = stmtLeggiRegione.executeQuery()) {
	  while (rs.next()) {
       aux.add(rs.getString("regione"));
      }
	}
	return aux;
}



//Metodo per andare a leggere nel database i comuni
public ArrayList<String> leggiProvince(String line) throws SQLException {
	ArrayList<String> aux = new ArrayList<String>();
	stmtLeggiProvincia.setString(1, line);
	try(ResultSet rs = stmtLeggiProvincia.executeQuery()) {
	  while (rs.next()) {
     aux.add(rs.getString("targa_auto"));
    }
	}
	return aux;
}

//Metodo per andare a leggere nel database i comuni
public ArrayList<String> leggiComune(String line) throws SQLException {
	ArrayList<String> aux = new ArrayList<String>();
	stmtLeggiComune.setString(1, line);
	try(ResultSet rs = stmtLeggiComune.executeQuery()) {
	  while (rs.next()) {
       aux.add(rs.getString("comune"));
      }
	}
	return aux;
}


}

