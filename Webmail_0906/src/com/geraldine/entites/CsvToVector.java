package com.geraldine.entites;
/**
 * 
 */
import java.io.*;
import java.util.*;
/**
 * @author Géraldine-GB
 *
 */


public class CsvToVector {
	
	String emailAdresses = "C:\\Users\\28010-34-17\\Desktop\\adresses.csv";

    BufferedReader reader = null;

    Vector emailAdressesVec = new Vector(5);

    //EO class variables

    public CsvToVector(){

                  this.emailAdresses=emailAdresses;

                  this.reader=reader;

                  this.emailAdressesVec=emailAdressesVec;

    }

    //EO constructor section

    public void readCsv() throws IOException {

                  String[] email = new String[3];

                  String[] tmp = new String[email.length];

                  try {

                  reader = new BufferedReader(new FileReader(emailAdresses));

                  for(int i=0; i<(email.length); i++) {

                               email[i]=reader.readLine();

                               if(email[i] == null) {

                                            email[i] = "empty slot";

                               }else if((email[i] == email[email.length-1])) {

                                                                       tmp = new String[email.length + 1];

                                                                       for(int j=0; j < email.length; j++) {                       

                                                                                    tmp[j] = email[j];                

                                                                       }//EO if/else                             

                               email = tmp;                

                  }//EO for loop

                               this.emailAdressesVec.insertElementAt(email[i], i);

                  }//EO try

                  }catch(FileNotFoundException fnfe){

                               System.out.println("not ok");

                  }//EO try/catch

                  reader.close();

    			  }//EO of readCsv()

    

}// Eo class CsvToVector
