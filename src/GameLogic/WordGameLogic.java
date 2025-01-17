package GameLogic;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import dataAccess.WordManger;

public class WordGameLogic {
	

	private int NumberOfWord;
	private  List<String> Words;
	
	
	WordManger wordmanager=new WordManger();
	
	public WordGameLogic(int NumberOfWord)
	{
		this.NumberOfWord=NumberOfWord;
		Words=wordmanager.getRandomWords(NumberOfWord);
	}
	
	public List<String> getWords()
	{
		return this.Words;
	}
	public void SetRandomWords()
	{
		Words=wordmanager.getRandomWords(NumberOfWord);
	}
	public LinkedList<Character> GenereteLetters()
	{
		LinkedList<Character> Letters=_getAllLetters(Words);
		_ShuffleLetters(Letters);
		return Letters;

	}
	public  boolean ValidateWord(String Word)
	{
		for(String currentword:Words)
		{
			if(currentword.equals(Word))
			{
				Words.remove(Word);
				return true;
			}
		}
		return false;
	}
	
	private LinkedList<Character> _getAllLetters(List<String> list)
	{
		LinkedList<Character> Letters=new LinkedList<>();
		for(String word:list)
		{
			char[] characters=word.toCharArray();
			for(char c:characters)
			{
				Letters.add(c);
			}
		}
		return Letters;
	}
	private void _ShuffleLetters(LinkedList<Character> Letters)
	{
		int length=Letters.size();
		Random random=new Random();
		 for (int i =0 ;i < length; i++) {
	            int RandomPosition = random.nextInt(length-i-1,length);
	            // Swap elements
	            char temp=Letters.get(i);
	            Letters.set(i,Letters.get(RandomPosition));
	            Letters.set(RandomPosition, temp);
		 }
	}
}
