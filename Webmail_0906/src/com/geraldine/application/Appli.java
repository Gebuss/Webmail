package com.geraldine.application;

import javax.swing.UIManager;
import com.geraldine.entites.*;
import com.geraldine.ihm.Fenetre;

public class Appli 
{	
	/**
	 * @param args
	 */
	
	public Appli() 
	{
		DemandeMgr ctrl = new DemandeMgr ();
		Fenetre window = new Fenetre(ctrl);
		window.setVisible(true);
	}

	public static void main(String[] args) {
			
		try {
			
			UIManager.setLookAndFeel(
				    UIManager.getCrossPlatformLookAndFeelClassName());
//			Fenetre.setDefaultLookAndFeelDecorated(true);
		} catch ( Exception e) {
			// TODO: handle exception
			}
		
		Appli appli = new Appli();
		
	} // Eo "Appli"

} // Eo class
