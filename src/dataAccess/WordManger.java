package dataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Word;
import utils.clsUtil;
public class WordManger {
	

	
	public boolean IsWordExist(String Word)
	{
		Connection connection=DatabaseConnection.GetConnection();
		if (connection==null)
			return false;
		String query="Select count(*) From Dictionary where word=?";
		try(connection;PreparedStatement preparestatement=connection.prepareStatement(query))
		{
			preparestatement.setString(1,Word);
			ResultSet resultset=preparestatement.executeQuery();
			if(resultset.next() && resultset.getInt(1)>0)
			{
				return true;
			}
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	public boolean addWord(Word word)
	{
		Connection connection=DatabaseConnection.GetConnection();
		if(connection==null)
			return false;
		if(!IsWordExist(word.getName()))
		{
		String Query="INSERT INTO dictionary (word, difficulty) VALUES (?, ?);";
		try (connection;PreparedStatement ps=connection.prepareStatement(Query))
		{
			ps.setString(1,word.getName());
			ps.setString(2, word.getDifficulty().toString());
			ps.executeUpdate();
			return true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		}
		return false;
	}
	public List<String> getRandomWords(int Number)
	{
		Connection connection=DatabaseConnection.GetConnection();
		if (connection==null)
			return null;
		List<String> RandomWords=new ArrayList<>();
		String Query="SELECT word FROM dictionary ORDER BY RAND() LIMIT ?";
		try(connection;PreparedStatement ps=connection.prepareStatement(Query))
		{
			ps.setInt(1, Number);
			ResultSet resutlset=ps.executeQuery();
			while(resutlset.next())
			{
				RandomWords.add(resutlset.getString("word"));
			}
			return RandomWords;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public void InsertWordsFromFile(String FilePath) {
	    Connection connection = DatabaseConnection.GetConnection();
	    if (connection == null) {
	        return; // Exit if there is no connection
	    }

	    // Get the list of words from the file
	    List<String> Words = clsUtil.getWordsFromFile(FilePath);

	    try {
	        // Prepare the SQL queries
	        String checkQuery = "SELECT count(*) FROM Dictionary WHERE word = ?";
	        String insertQuery = "INSERT INTO dictionary (word, difficulty) VALUES (?, ?)";
	        
	        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
	             PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {

	            // Loop through each word and insert if it doesn't already exist in the database
	            for (String word : Words) {
	                checkStmt.setString(1, word);
	                ResultSet resultSet = checkStmt.executeQuery();
	                if (resultSet.next() && resultSet.getInt(1) == 0) {
	                    insertStmt.setString(1, word);
	                    insertStmt.setString(2, clsUtil.getDifficultyByLength(word).toString());
	                    insertStmt.addBatch(); // Add to batch  to imporve performance and reduce the number of database connection.
	               
	                }
	            }
	            // Execute the batch
	            insertStmt.executeBatch();
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        try {
	            if (connection != null && !connection.isClosed()) {
	                connection.close(); // Close the connection after all inserts are done
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
}
