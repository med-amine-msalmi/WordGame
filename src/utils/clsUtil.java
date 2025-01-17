package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Word;

public class clsUtil {

	public static List<String> getWordsFromFile(String FilePath)
	{
		List<String> Words=new ArrayList<>();
		try {
			BufferedReader Reader=new BufferedReader(new FileReader(FilePath));
			String word="";
			while((word=Reader.readLine()) != null)
			{
				if(word.length()>1 && !(word.contains("(")))
				{
					Words.add(word.trim());
				}
			}
			return Words;
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	 public static Word.Difficulty getDifficultyByLength(String word) {
	        if (word.length() <= 4)
	            return Word.Difficulty.Easy;
	        if (word.length() <= 8)
	            return Word.Difficulty.Medium;
	        return Word.Difficulty.Hard;
	    }
	 
}
