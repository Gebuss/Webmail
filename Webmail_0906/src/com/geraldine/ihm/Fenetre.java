/**
 * 
 */
package com.geraldine.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.geraldine.application.DemandeMgr;
import com.geraldine.entites.Mailbuilder;

/**
 * @author GéraldineGB
 *
 */

public class Fenetre extends JFrame {
	
	/*
	 *  Champs privés: référence vers les contrôles et instanciation
	 */
	
	
	// MENUS
	
	// --- "JMenuItem: Nouveau"
	private JMenuItem mnuMessageNouveau = new JMenuItem ("Nouveau", 'N');
	// --- "JButton"
	private JButton cmdToolNouveau = new JButton (new ImageIcon("new.gif"));
	
	// --- "JMenuItem: Ouvrir"
	private JMenuItem mnuMessageOuvrir = new JMenuItem ("Ouvrir...", 'O');
	// --- "JButton"
	private JButton cmdToolOuvrir = new JButton(new ImageIcon("open.gif"));
	
	// --- "JMenuItem: Envoyer"
	private JMenuItem mnuMessageEnvoyer = new JMenuItem ("Envoyer", 'E');
	// --- "JButton"
	private JButton cmdToolEnvoyer = new JButton (new ImageIcon("send.gif"));
	
	// --- "JMenuItem: Quitter"
	private JMenuItem mnuMessageQuitter = new JMenuItem ("Quitter", 'Q');
	// --- "JButton"
	private JButton cmdToolQuitter = new JButton();
	
	// --- "JMenuItem: Paramètres"
	private JMenuItem mnuOptionsParametres = new JMenuItem("Paramètres", 'P');
	// --- "JButton"
	private JButton cmdToolParametres = new JButton();
	
	// --- "JMenuItem: A propos"
	private JMenuItem mnuQuestionAPropos = new JMenuItem("A Propos...", 'A');
	// --- "JButton"
	private JButton cmdToolAPropos = new JButton();
	

	// TOOLBAR
	
	// --- "JTextField: Sujet"
	private JTextField sujet = new JTextField();
	
	// --- "Création d'une icone Destinataires"
	private JLabel logoAdresses = new JLabel(new ImageIcon("destinataires.gif"));
	
	// --- Création d'une combo box dédiée aux destinataires
	private JComboBox<String> cboAdresses = new JComboBox<String>();
	
	// --- Création du bouton OK de la combo box
	private JButton cmdToolOk = new JButton("OK");
	
	// ---  Création d'une barre d'état
	private JLabel statusBar = new JLabel(" ");
	
	// --- "JLabel"
	private JLabel lblInfo = new JLabel ("Aucun message envoyé");
		
	
	// TEXT AREA
	
	private JTextArea txtZone = new JTextArea(); 
	private final JScrollBar scrollBar = new JScrollBar();
	private JButton btnEnvoyer;
	
	
	
	// DemandeMgr
		
		// --- Création de la DemandeMgr
	private DemandeMgr m_ctrl;
	
	
	/**
	  *	Cons'tor 
	 **/
	
	public Fenetre(DemandeMgr ctrl){
		// Preparation des proprietes du formulaire
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setIconImage(new ImageIcon("mail.gif").getImage());
		this.setSize(800,450);
		this.setTitle("WebMail");
//		this.getComponents(cursor);
		m_ctrl = ctrl;
		this.addWindowListener(
		
				//Confirmation de la fermeture de la fenêtre avec ouverture de la boîte de dialogue 
				new WindowAdapter() {
					public void windowClosing(WindowEvent arg0) {
						rep = JOptionPane.showConfirmDialog(mnuMessageQuitter, "End of application", "Do you want to leave the application ?", JOptionPane.YES_NO_OPTION);
						if (rep==0) {
						System.exit(0);
						}	
					}
				}//Eo class anonyme		
			); //Eo addWindowListener
		
		// Paramétrage des menus
		initMenus();
	
		// Définition de l'état initial des contrôles
		initControls();
		
		// Gestion des évènements générés par la souris
		
	
	} // Eo Shape

	private void initMenus() {
	
		JMenuBar mbOfTheFrame = new JMenuBar();
		this.setJMenuBar(mbOfTheFrame);
		
		// Menu Message

		JMenu mnuMessage = new JMenu ("Message");
		mnuMessage.setMnemonic('M');
		mbOfTheFrame.add (mnuMessage);
		
		// Menus actionListener, ChangeListener et MouseAdapter
		mnuMessageNouveau.addActionListener(new MenuActionListener());
		mnuMessageNouveau.addChangeListener(new MenuChangeListener());
		mnuMessageNouveau.addChangeListener(new menuMouseAdapter());
		mnuMessage.add(mnuMessageNouveau);

		mnuMessageOuvrir.addActionListener(new MenuActionListener());
		mnuMessageOuvrir.addChangeListener(new MenuChangeListener());
		mnuMessageOuvrir.addChangeListener(new menuMouseAdapter());

		mnuMessage.add(mnuMessageOuvrir);
		
		mnuMessage.addSeparator();
		
		mnuMessageEnvoyer.addActionListener(new MenuActionListener());
		mnuMessageEnvoyer.addChangeListener(new MenuChangeListener());
		mnuMessageEnvoyer.addChangeListener(new menuMouseAdapter());
		
		mnuMessageEnvoyer.setAccelerator(KeyStroke.getKeyStroke("F2"));
		
		mnuMessageEnvoyer.setEnabled(false);
		
		
		//createBtn.disableProperty().bind(
			//    Bindings.createBooleanBinding( () -> 
			  //      user_name.getText().trim().isEmpty(), user_name.textProperty()
			    //)
			    // If you want to check more text field, use it as by removing comments
			    //.or(  Bindings.createBooleanBinding(
			    //         first_name.getText.trim().isEmpty(), first_name.textProperty()
			    //     )
			    //  )

		//	);
		
		mnuMessage.add(mnuMessageEnvoyer);
		
		mnuMessage.addSeparator();
		
		mnuMessageQuitter.addActionListener(new MenuActionListener());
		mnuMessageQuitter.addChangeListener(new MenuChangeListener());
		mnuMessageQuitter.addChangeListener(new menuMouseAdapter());

		mnuMessage.add(mnuMessageQuitter);
		
		// Menu Options
		
		JMenu mnuOptions = new JMenu ("Options");
		mnuOptions.setMnemonic('O');
		mbOfTheFrame.add(mnuOptions);
		
		mnuOptionsParametres.addActionListener(new MenuActionListener());
		mnuOptionsParametres.addChangeListener(new MenuChangeListener());
		mnuOptionsParametres.addChangeListener(new menuMouseAdapter());
		mnuOptions.add(mnuOptionsParametres);
		
		// Menu '?'
		
		JMenu mnuQuestion = new JMenu ("?");
		mnuQuestion.setMnemonic('?');
		mbOfTheFrame.add(mnuQuestion);
		
		mnuQuestionAPropos.addActionListener(new MenuActionListener());
		mnuQuestionAPropos.addChangeListener(new MenuChangeListener());
		mnuQuestionAPropos.addChangeListener(new menuMouseAdapter());
		mnuQuestion.add(mnuQuestionAPropos);
		 
		
	} // Eo initMenus
	
	// Définition de l'état initial des contrôles
	
	private void initControls(){
        this.setBounds(300, 300, 700, 400);
		JPanel zoneClient = (JPanel) this.getContentPane();

		JToolBar tbOfTheFrame = new JToolBar ();
		tbOfTheFrame.setFloatable(false);
		
		zoneClient.add(tbOfTheFrame, BorderLayout.NORTH);

		
		cmdToolNouveau.addActionListener(new MenuActionListener());
		cmdToolNouveau.setToolTipText("Nouveau message");
		tbOfTheFrame.add(cmdToolNouveau);
		
		// Initialisation du composant Ouvrir
		cmdToolOuvrir.addActionListener(new MenuActionListener());
		cmdToolOuvrir.setToolTipText("Ouvre un message déjà envoyé");
		tbOfTheFrame.add(cmdToolOuvrir);
		
		tbOfTheFrame.addSeparator();
		
		// Initialisation du composant Envoyer
		cmdToolEnvoyer.addActionListener(new MenuActionListener());
		
		cmdToolEnvoyer.setEnabled(false);
		if (!txtZone.getText().trim().isEmpty() || !sujet.getText().trim().isEmpty()) 
		cmdToolEnvoyer.setEnabled(true);
		
//		CreateBtn.disableProperty(cmdToolEnvoyer).bind(
//				Bindings.createBooleanBinding( (
//						sujet.getText().trim().isEmpty(), sujet.textProperty()));
		
		
		
		cmdToolEnvoyer.setToolTipText("Envoyer le message");
		tbOfTheFrame.add(cmdToolEnvoyer);
		
		
		tbOfTheFrame.addSeparator();
	 
		 // Panel de droite contenant le champ de texte Sujet et la combo box pour les destinataires
        JPanel panel2Droite = new JPanel (new FlowLayout(FlowLayout.RIGHT));
        tbOfTheFrame.add(panel2Droite);
        // Définition de l'étiquette Sujet
        panel2Droite.add(new JLabel ("Sujet"), BorderLayout.WEST);
        // Définition du champ de texte Sujet
        panel2Droite.add(sujet,BorderLayout.CENTER);
        sujet.setColumns (20);
      
        
        // Définition de la liste déroulante contenant les adresses mail
        panel2Droite.add(logoAdresses, BorderLayout.EAST);
		panel2Droite.add(cboAdresses);
		panel2Droite.add(cmdToolOk);
		
		// Paramétrage de la liste déroulante
		cboAdresses.setEditable(true);
		cboAdresses.setMaximumRowCount(3);
		
		String [] adresses = {};
		for (int i = 0; i < adresses.length; i++) { 
			cboAdresses.addItem(adresses[i]);}
			
/*-------------------------------------------------------------------------------------------------------------------------------------------
 *  					PANEL CENTRAL
 ------------------------------------------------------------------------------------------------------------------------------------------*/

		JPanel panelCentre = new JPanel (new FlowLayout(FlowLayout.CENTER));
		txtZone.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.windowBorder, null));
		txtZone.setDragEnabled(true);
		txtZone.setToolTipText("Enter your text here");
		txtZone.setLineWrap(true);
		txtZone.setWrapStyleWord(true);
		txtZone.setColumns(20);
		add(txtZone);
		scrollBar.setAutoscrolls(true);
		add(scrollBar, BorderLayout.EAST);
		txtZone.addCaretListener(new CaretListener() {
			
		/* La fonction envoyer du menu menu et l'icône envoyer sont actives quand la zone de texte n'est pas vierge,
		 * le sujet n'étant pas indispensable à l'envoi d'un mail
		 */
			
			@Override
			public void caretUpdate(CaretEvent e) {
				if (!txtZone.getText().isEmpty()) {
					mnuMessageEnvoyer.setEnabled(true);
					cmdToolEnvoyer.setEnabled(true);
				} // Eo if
				} // Eo caretUpdate
			}
		); //Eo CaretLisener
		
			
			// Panel inférieur contenant un label provisoirement fixe suivi d'un autre label rouge pour les messages d'erreurs
//			JPanel panelDuBas = new JPanel (new FlowLayout(FlowLayout.LEFT));
//			tbOfTheFrame.add(panelDuBas);
//			panelDuBas.add(new JLabel(), BorderLayout.WEST);
			
//			JPanel zoneClient = (JPanel) this.getContentPane();
		
		// Création et ajout d'une barre de statut
		statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		zoneClient.add(statusBar, BorderLayout.SOUTH);
			
//			//Définition de l'erreur en HTMLet en police rouge bold italique
//			zoneClient.add(new JLabel("Erreur"), BorderLayout.SOUTH);
////			statusBar.add(new JLabel(), BorderLayout.WESTB);
//			zoneClient.add(txtZone, BorderLayout.SOUTH);	
			
		}
		

	

/**
* Gestion des evenements
*/
class MenuActionListener implements ActionListener {

public void actionPerformed(ActionEvent e){
	Object source = e.getSource();
	if (source == cmdToolNouveau) mnuMessageNouveau_click();
	if (source == cmdToolOuvrir) mnuMessageOuvrir_click();
	if (source == cmdToolEnvoyer) mnuMessageEnvoyer_click();

	if (source == mnuMessageNouveau) mnuMessageNouveau_click();
	if (source == mnuMessageOuvrir) mnuMessageOuvrir_click();
	if (source == mnuMessageEnvoyer) mnuMessageEnvoyer_click();
	if (source == mnuMessageQuitter) mnuMessageQuitter_click();	
	
	if (source == btnEnvoyer) mnuMessageEnvoyer_click();
	
	} // Eo action performed

} // Eo MenuActionListener

class MenuChangeListener implements ChangeListener {
	public void stateChanged(ChangeEvent e) {
	Object source = e.getSource();
    if (source == mnuMessageNouveau) lblInfo.setText("Create a new mail");
    if (source == mnuMessageOuvrir) lblInfo.setText("Open an existing mail");
    if (source == mnuMessageEnvoyer) lblInfo.setText("Send a mail");
    if (source == mnuMessageQuitter) lblInfo.setText("Leave the application");
	} // Eo stateChanged
} // EoMenuChangeListener

class menuMouseAdapter implements ChangeListener {		
	public void stateChanged(ChangeEvent e) {
	Object source = e.getSource();
	if (source == mnuMessageNouveau) statusBar.setText("Send a new mail");
	if (source == mnuMessageOuvrir) statusBar.setText("Open an existing mail");
	if (source == mnuMessageEnvoyer) statusBar.setText("Send a mail");
	if (source == mnuMessageQuitter) statusBar.setText("Leave the application");
	if (source == mnuOptionsParametres) statusBar.setText("Settings");
    if (source == mnuQuestionAPropos) statusBar.setText("About");
	} // Eo StateChanged
} // Eo MenuMouseAdapter
	
public void mouseExited (MouseEvent e){
//Object source = e.getSource();
//if (source != mnuMessageNouveau && source != mnuMessageOuvrir && source != mnuMessageEnvoyer && source != mnuMessageQuitter)
statusBar.setText("");
} // Eo MouseExited


//} // Eo MouseAdapter

		

//}//Eo initControls(...

			
	/**
	  * Gestion des click() 
	 **/
	
	private int rep;
	
	
	private void mnuMessageNouveau_click(){
		
		rep = JOptionPane.showConfirmDialog(this,"Do you want to save your message ?","Message not sent",
		JOptionPane.YES_NO_CANCEL_OPTION);
		
		if (rep == 0) {
				mnuMessageSauver_click();
		        txtZone.setText(null);
		        sujet.setText(null);
		   }
		else {
			txtZone.setText(null);
			sujet.setText(null);
		}
		
	} // Eo mnuMessageNouveau
			
//			JFileChooser dlg = new JFileChooser("C:/Users/28010-34-17/Documents/Afpa");
//			if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION);
//			m_ctrl.sauvegarder(txtZone, dlg.getSelectedFile());	
		

	
	private void mnuMessageOuvrir_click(){
		
		
		JFileChooser dlg = new JFileChooser("C:/Users/28010-34-17/Documents/Afpa");
		if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION);
		m_ctrl.ouvrir(txtZone, dlg.getSelectedFile());
		
			
	}// Eo mnuMessageOuvrir
	
	private void mnuMessageSauver_click(){
		
		JFileChooser dlg = new JFileChooser("C:/Users/28010-34-17/Documents/Afpa");
		//JFileChooser dlg = new JFileChooser(".");
		if (dlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION);
		m_ctrl.sauvegarder(txtZone, dlg.getSelectedFile());
		
	}//Eo menuMessage Sauver
	
	private void mnuMessageEnvoyer_click(){
		
		
//		if (txtZone.getText() == null || sujet.getText().isEmpty()) {
//			cmdToolEnvoyer.setEnabled(false);
//		}
		
	}// Eo mnuMessageEnvoyer
	
	private void mnuMessageQuitter_click(){
		
//	rep = JOptionPane.showConfirmDialog(this, "End of application", "Do you want to leave the application ?", 
//			JOptionPane.YES_NO_OPTION);
		int dialog = JOptionPane.showConfirmDialog(this, "End of application", "Do you want to leave the application ?", 
		JOptionPane.YES_NO_OPTION);
//		dialog.setAlwaysOnTop(true);
		if (dialog == JOptionPane.YES_OPTION) {
				System.exit(0);	
			}		
	}// Eo mnuMessageQuitter...

		

	
	//----------------------------------------------------------------------------------------------------------------
	// Début nouveau code
	//public void setFocusPainted(boolean b) ?
	
	//----------------------------------------------------------------------------------------------------------------
	
}// Eo class Frame