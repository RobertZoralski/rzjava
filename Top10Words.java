package rzjava;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
/ The class gets top ten words out of the book
*/

public class Top10Words {

	public static void main(String[] args) throws IOException {

		HashMap<String, Integer> hm = new HashMap<String, Integer>();		

		//open file for streaming
		Path path = Paths.get("src/rzjava/book.txt");
		BufferedReader br = Files.newBufferedReader(path);
		
		//read line by line
		String line;
		while( (line = br.readLine()) != null ) {
			
			if(0==line.length()) continue;
			
			//strip double spaces and some interpunction chars
			line = line.replaceAll("[,.:]","").replaceAll("\\s+", " ").trim();
			
			//tokenize words
			String[] words = line.split(" ");
			
			for(String word: words) {
				
				//fill hashmap
				if( hm.containsKey(word) ) {
					int wordCount = hm.get(word);
					hm.put(word, ++wordCount);
				} else {
					hm.put(word, 1);
				}
			}
		}

		br.close();		
		
		//get entries out of map as ArrayList
		List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>> (hm.entrySet());

		//sort it using comparator
		Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
			
			@Override
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				return b.getValue().compareTo(a.getValue());	//descending
			}
			
		});

		//print top 10
		for(int i=0; i<10; i++) {
			System.out.println(entries.get(i));
		}	
		
	}

}
