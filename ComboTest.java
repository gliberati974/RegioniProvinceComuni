
/*
 * 
 * Autore: Giuseppe Liberati. PER MOTIVI DI TEMPO HO IMPLEMENTATO SOLO REGIONE/COMUNI. LA 
 * PROCEDURA PER IMPLEMENTARE ANCHE LE PROVINCE E' ANALOGA NEL PASSAGGIO DEI DATI DALLA SECONDA
 * COMBO ALLA TERZA.
 */

package ESAME;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;



public class ComboTest {
	/*
	 * -------------------------------------------------------------------------------------------------
	 * Recupero i parametri di connessione al DB da un file esterno al progetto (resources/db.properties).
	 * -------------------------------------------------------------------------------------------------
	 */
		
		private static Properties getConnectionData() {

	     Properties props = new Properties();

	     String fileName = "resources/db.properties";

	     try (FileInputStream in = new FileInputStream(fileName)) {
	         props.load(in);
	     } catch (IOException ex) {
	         Logger lgr = Logger.getLogger("Regione/Comuni");
	         lgr.log(Level.SEVERE, ex.getMessage(), ex);
	     }

	     return props;
	 }
//		----------------------------------------------------------
		public static Properties props = getConnectionData();

		public static String DB_URL = props.getProperty("db.url");
		public static String DB_NAME = props.getProperty("db.name");
		public static String DB_USER = props.getProperty("db.user");
		public static String DB_PASS = props.getProperty("db.passwd");
//		----------------------------------------------------------
	
		public static void main(String[] args) {
			
			JFrame jf = new JFrame("ComboBox");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setSize(520, 230);
			jf.setLocationRelativeTo(null);
			jf.add(new ComboGui());
			//jf.pack();
			jf.setVisible(true);
		}
}

