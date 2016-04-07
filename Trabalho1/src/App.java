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
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("test.txt"))))){
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
		
		for(Tree tree: trees){
			Node treeNode = tree.getRoot();
			Simbol dicSimbol = dic.get(treeNode.getSimbol().getCharacter());
			int freq = dicSimbol.getFrequency();
			treeNode.setAccFreq(freq);
			
		}
		
		/*System.out.println(trees);
		
		heapify(trees,0);
		
		System.out.println(trees);
		
		trees.remove(0);
		trees.trimToSize();//wtf?
		System.out.println(trees);
		
		heapify(trees, 0);
		
		System.out.println(trees);*/
		
		System.out.println(trees);
		unifyTrees(trees);
		System.out.println(trees);
		Tree masterTree = trees.get(0);
		masterTree.printBonito();
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
	
	private static void heapify(ArrayList<Tree> trees, int i){
		if(i*2+1 >= trees.size() || i*2+2 >= trees.size()) return;
		heapify(trees, i*2+1);
		heapify(trees, i*2+2);
		Tree nLeft = trees.get(i*2+1);
		Tree nRight = trees.get(i*2+2);
		if(trees.get(i).getRoot().getAccFreq() > nLeft.getRoot().getAccFreq()){
			Tree temp = trees.get(i);
			trees.set(i, nLeft);
			trees.set(i*2+1, temp);
		}
		if(trees.get(i).getRoot().getAccFreq() > nRight.getRoot().getAccFreq()){
			Tree temp = trees.get(i);
			trees.set(i, nRight);
			trees.set(i*2+2, temp);
		}
	}
	
	public static void unifyTrees(ArrayList<Tree> trees){
		if(trees.size() == 1) return;
		
		heapify(trees, 0);
		Tree treeA = trees.get(0);
		trees.remove(0);
		trees.trimToSize();//wtf?
		heapify(trees, 0);
		Tree treeB = trees.get(0);
		trees.remove(0);
		trees.trimToSize();//wtf?2
		trees.add(unifyTrees(treeA, treeB));
		unifyTrees(trees);
	}
}
