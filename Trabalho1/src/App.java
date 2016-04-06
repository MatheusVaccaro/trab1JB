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
import java.util.ArrayList;

public class App {
	public static void main(String args[]){
		
		//Tree masterTree;
		ArrayList<Tree> trees = new ArrayList<>();
		Map<Character, Simbol> dic = new HashMap<>();
		
		//Leitura de dados
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("arq1.txt"))))){
			int character;
			while((character = reader.read()) != -1){
				char letter = (char) character;
				if(!dic.containsKey(letter)){
					Simbol simbolo = new Simbol(letter);
					dic.put(letter, simbolo);
					trees.add(new Tree(new Node(simbolo)));
				}
				else{
					Simbol simbolTemp = dic.get(letter);
					simbolTemp.update();
					dic.put(letter, simbolTemp);
				}
			}
		}
				catch(Exception e){
			e.printStackTrace();
			System.err.println("Erro de I/O");
		}
		
		//System.out.println(dic);
		
	}
	
	private static Tree unifyTrees(Tree tree1, Tree tree2){
		Node nleft = tree1.getRoot();
		Node nright = tree2.getRoot();
		if(tree1.getRoot().getAccFreq() < tree2.getRoot().getAccFreq()){
			nleft = tree2.getRoot();
			nright = tree1.getRoot();
		}
		Node root = new Node();
		root.setLeft(nleft);
		root.setRight(nright);
		root.setAccFreq(nleft.getAccFreq() + nright.getAccFreq());
		return new Tree(root);
	}
	
	public static void unifyTrees(ArrayList<Tree> trees){
		int menorF = trees.get(0).getRoot().getAccFreq();
		int maiorF = menorF;
		Tree maiorT = trees.get(0);
		Tree menorT = trees.get(0);
		
		for(Tree tree: trees){
			int freq = tree.getRoot().getAccFreq();
			if(freq > maiorF)
		}
	}
}
