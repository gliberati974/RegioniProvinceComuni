package ESAME;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;





public class ComboGui extends JPanel{

	ComboEngine aux;
	private static ArrayList<String> elencoRegioni = new ArrayList<String>();
	private static ArrayList<String> elencoComuni = new ArrayList<String>();
	private static ArrayList<String> elencoProvince = new ArrayList<String>();
	private JLabel lblRegione;
	private JLabel lblComune;
	private JLabel lblProvincia;
	private JLabel lblTitolo;
	private static JComboBox<String> comboRegioni;
	private static JComboBox<String> comboComuni;
	private static JComboBox<String> comboProvince;
	String prodotto;
	String nome = "";

	
	public ComboGui() {
		
		// Utlizzo il LayoutManager GridLayout all'interno del contenitore JPanel, per 
		// organizzare lo spazio dei componenti.
		super(new GridLayout(4,1));
		
		// Aggiungo i metodi
		add(titolo());
		add(regione());
		add(provincia());
		add(comune());
	
		
		//Inserisco un bordo.
		this.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
		
		
		//comboProdLine.addActionListener(new SelectListener());
		//comboProdName.addActionListener(new TestListener());
	}
	
	//Implemento l'interfaccia ActionListener per fare l'override del metodo actionPerformed,
	// fondamentale per il collegamento al componente.
	private class SelectListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)  {
			
			ArrayList<String> name;
			if (comboRegioni.getSelectedItem().toString().length()>0) {	
				
				comboProvince.removeAllItems();
				try {
					 aux = new ComboEngine(ComboTest.DB_URL, ComboTest.DB_NAME, ComboTest.DB_USER, ComboTest.DB_PASS);
					name = aux.leggiProvince(comboRegioni.getSelectedItem().toString());
					for (String item : name) {
						comboProvince.addItem(item);
					}
					
				} catch (SQLException eSQL) {
					eSQL.printStackTrace();
				}
			} else {
				comboProvince.removeAllItems();
			}
			
			
	}
	}
	
	//Implemento l'interfaccia ActionListener per fare l'override del metodo actionPerformed,
	// fondamentale per il collegamento al componente.
	private class TestListener implements ActionListener{
		
			
			/*
			if (comboComuni.hasFocus()) {				
				System.out.println("Event fired !!!!");	
			}
			*/
			
			@Override
			public void actionPerformed(ActionEvent e)  {
				
				ArrayList<String> name;
				if (comboProvince.getSelectedItem().toString().length()>0) {	
					
					comboComuni.removeAllItems();
					try {
						 aux = new ComboEngine(ComboTest.DB_URL, ComboTest.DB_NAME, ComboTest.DB_USER, ComboTest.DB_PASS);
						name = aux.leggiComune(comboProvince.getSelectedItem().toString());
						for (String item : name) {
							comboComuni.addItem(item);
						}
						
					} catch (SQLException eSQL) {
						eSQL.printStackTrace();
					}
				} else {
					comboComuni.removeAllItems();
				}
				
				
		}
			
		
	
	}
	
	private class TestListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)  {
		
		
		if (comboComuni.hasFocus()) {				
			System.out.println("Event fired !!!!");	
		}
		
			
		}
	}
	
	public JPanel titolo() {
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lblTitolo = new JLabel("Regioni/Comuni");
		panel.add(lblTitolo);
		
		return panel;
	}
	
	//Costruisco il primo ComboBox.
	public JPanel regione() {
		
		JPanel panel = new JPanel(new GridLayout(1,1));
		
		lblRegione = new JLabel("Regione");
		panel.add(lblRegione); 
		comboRegioni = new JComboBox<String>();
		comboRegioni.addItem("");
		elencoRegioni = getRegione();
		comboRegioni.addActionListener(new SelectListener());
		panel.add(comboRegioni);
				
		for(String item: elencoRegioni) {
			comboRegioni.addItem(item);
			
		}
				
		return panel;
	}
	

public JPanel provincia() {
	
	JPanel panel = new JPanel(new GridLayout(1,1));
	
	lblProvincia = new JLabel("Provincia");	
	panel.add(lblProvincia);
	comboProvince = new JComboBox<String>();
	comboProvince.addItem("");
	elencoProvince = getRegione();
	comboProvince.addActionListener(new TestListener2());
	panel.add(comboProvince);
	
	for(String item: elencoProvince) {
		comboProvince.addItem(item);
		
	}
	
	return panel;
}


//Costruisco il secondo ComboBox.
public JPanel comune() {
		String s = "";
		JPanel panel = new JPanel(new GridLayout(1,1));
		
		lblComune = new JLabel("Comune");	
		panel.add(lblComune);
		comboComuni = new JComboBox<String>();
		comboComuni.addItem("");
		elencoComuni = getProvince(s);
		comboComuni.addActionListener(new TestListener());
		panel.add(comboComuni);
		
		
		
		return panel;
	}






//Connessione al database per caricare i dati della regione.
private static ArrayList<String>getRegione(){
	
	ArrayList<String>line = null;
	try {
		ComboEngine aux = new ComboEngine(ComboTest.DB_URL, ComboTest.DB_NAME, ComboTest.DB_USER, ComboTest.DB_PASS);
		line = aux.leggiRegione();
		
	}catch(SQLException eSQL) {
		eSQL.printStackTrace();
	}
	return line;
}

private static ArrayList<String>getProvince(String s){
	
	ArrayList<String>line = null;
	try {
		ComboEngine aux = new ComboEngine(ComboTest.DB_URL, ComboTest.DB_NAME, ComboTest.DB_USER, ComboTest.DB_PASS);
		line = aux.leggiProvince(s);
		
	}catch(SQLException eSQL) {
		eSQL.printStackTrace();
	}
	return line;
}
	}






