import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("arq2.txt"))))){
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
		//atualiza frequencia dos caracteres no array
		for(Tree tree: trees){
			Node treeNode = tree.getRoot();
			Simbol dicSimbol = dic.get(treeNode.getSimbol().getCharacter());
			int freq = dicSimbol.getFrequency();
			treeNode.setAccFreq(freq);
			
		}
		
		//arvore de huffmann
		unifyTrees(trees);
		Tree masterTree = trees.get(0);
		//masterTree.printBonito();
		
		//coloca os codigos para cada caracter no dic
		for(Simbol simbol: dic.values()){
			String path = findCode(masterTree.getRoot(), simbol.getCharacter(), "");
			simbol.setCode(path);
		}
		masterTree.printBonito();
		//codificação do texto
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("arq2.txt"))))){
			int character;

			File file = new File("arq2Codified.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);			

			while((character = reader.read()) != -1){
				char letter = (char) character;
				Simbol simbol = dic.get(letter);
				String code = simbol.getCode();
				bw.write(code);
			}
			bw.close();
			System.out.println("Done");
		}
				catch(Exception e){
			e.printStackTrace();
			System.err.println("Erro de I/O - deu ruim na codificação");
		}
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
		if(i >= trees.size()) return;
		if(i*2+1 >= trees.size() && i*2+2 >= trees.size()) return;
		heapify(trees, i*2+1);
		heapify(trees, i*2+2);
		if(i*2+1 < trees.size()){
			Tree nLeft = trees.get(i*2+1);			
			if(trees.get(i).getRoot().getAccFreq() > nLeft.getRoot().getAccFreq()){
				Tree temp = trees.get(i);
				trees.set(i, nLeft);
				trees.set(i*2+1, temp);
			}
		}
		if(i*2+2 < trees.size()){
			Tree nRight = trees.get(i*2+2);			
			if(trees.get(i).getRoot().getAccFreq() > nRight.getRoot().getAccFreq()){
				Tree temp = trees.get(i);
				trees.set(i, nRight);
				trees.set(i*2+2, temp);
			}
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
	
	public static String findCode(Node node, char character, String path){
		if(node == null) return "";
		Simbol simbol = node.getSimbol();
		if(simbol != null){
			if(simbol.getCharacter() == character) return path;
		}
		String res = findCode(node.getLeft(), character, path + "0");
		if(res.equals("")){
			res = findCode(node.getRight(), character, path + "1");
		}
		return res;
	}
}
