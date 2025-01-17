package GamUI;

import javax.swing.SwingUtilities;

import dataAccess.WordManger;

public class StartGame{
	   public static void main(String[] args) {
		   /*
		    * populate the database with words from the file "wordlist". After the database is filled, comment
		    *  this line again to avoid inserting duplicate entries or reloading the data.
		    *  
		    *  
		    * WordManger wordmanager=new WordManger();
		    * wordmanager.InsertWordsFromFile("wordlist");
		   */
	        SwingUtilities.invokeLater(() -> {
	            WordGameFrontend game = new WordGameFrontend();
	            game.setVisible(true);
	        });
	    }
}
