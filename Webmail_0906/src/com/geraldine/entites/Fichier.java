/**
 * 
 */
package com.geraldine.entites;

/**
 * @author Géraldine-GB
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class Fichier {
	/*
	 * Attribut: nom du fichier a lire.
	 */
	private String nomDuFichier;
	/*
	 * Constructeur
	 */
	public Fichier(File nomDuFichier) {

		this.nomDuFichier = nomDuFichier.getAbsolutePath();
	}
	public Fichier(String nomDuFichier){
		this.nomDuFichier = nomDuFichier;
	}
	/*
	 * definition d'un chemin relatif: exemple (.../Msg/M20031024-0945.msg)
     * Attention ---> le repertoire (/Msg) doit exister
	 */
	public Fichier(){
		GregorianCalendar now = new GregorianCalendar ();
		SimpleDateFormat format =
	                  new SimpleDateFormat ("'./msg/M'yyyyMMdd-HHmmss'.msg'");
		this.nomDuFichier = format.format(now.getTime());
	}// Eo Fichier

	/**
	 * Retourne le contenu du fichier texte 
     * sous forme d'une ArrayList, une case de l' ArrayList <===> une ligne
	 * @return ArrayList
	 **/
	
	public ArrayList getLignes(){
		ArrayList local = new ArrayList();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(this.nomDuFichier));
			for(;;)
	        {
			String lg = bf.readLine();
			if (lg==null) break;
			if (lg.length() > 0) local.add(lg);
		}
			bf.close();
		}
		catch (IOException e){
		}
		finally {
			// on trie pour avoir une liste oordonnee dans le ComboBox
			Collections.sort(local)	;
		}
		return local;
	}// Eo getLignes
	/**
	 * Creation fichier texte avec le contenu en parametre
	 * @param ligne Contenu du fichier
	 */
	public void setContenu(String ligne){
		try {
			PrintWriter f = new PrintWriter(new FileWriter(this.nomDuFichier, true));
			f.println(ligne);
			f.close();
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null, 
		                "Sauvegarde du message impossible",
		                "Erreur sur " + this.nomDuFichier, JOptionPane.OK_OPTION);
		}
	}// Eo setContenu
	/**
	 * obtention du contenu d'un fichier texte
 	 * @return contenu du fichier
	 */
	public String getContenu(){
		StringBuilder local = new StringBuilder();
		try {
            BufferedReader f = new BufferedReader(new FileReader(this.nomDuFichier));
	        for(;;){
				String lg = f.readLine();
				if (lg==null) break;
				local.append(lg).append("\n");
            }
            f.close();
         }
		 catch (IOException e)
	     {
	     }
         return local.toString();
	}// Eo getContenu
	
}// Eo class Fichier
