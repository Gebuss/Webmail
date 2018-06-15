/**
 * 
 */
package com.geraldine.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextArea;

/**
 * @author 28010-34-17
 *
 */
public final class DemandeMgr {

	
	public void sauvegarder (JTextArea txtZone, File f)
	{ 
		try {
			PrintWriter WModele = new PrintWriter( new FileWriter(f));
			WModele.print(txtZone.getText());
			WModele.close();
		} catch (IOException e) {}
			
		}
	

	public void ouvrir(JTextArea txtZone, File f) 

	{
		try {
			BufferedReader RModele = new BufferedReader (new FileReader(f));
			txtZone.setText("");
			while (RModele.ready()) txtZone.append(RModele.readLine() + "\n");
			RModele.close();
		} catch (IOException e) {}
		
	}


	}

