import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
	public static void main(String args[]){
		
		Map<Character, Integer> dic = new HashMap<>();
		
		Path path = Paths.get("test.txt");
		
		
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("arq1.txt"))))){
			int character;
			while((character = reader.read()) != -1){
				char letter = (char) character;
				if(!dic.containsKey(letter)){
					dic.put(letter, 1);
				}
				else{
					int amount = dic.get(letter);
					dic.put(letter, amount+1);
				}
			}
		}
				catch(Exception e){
			e.printStackTrace();
			System.err.println("Erro de I/O");
		}
		
		System.out.println(dic);
	}
}
