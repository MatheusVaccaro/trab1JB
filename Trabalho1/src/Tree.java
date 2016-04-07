
public class Tree {
	private Node root;
	
	public Tree(Node root){
		this.root = root;
	}
	
	public Tree(){
		
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/*@Override
	public String toString() {
		return "Tree [root=" + root + "]";
	}*/
	public String toString(){
		return this.root.toString();
	}
	
	public void printBonito(){
		printBonito(root, "");
		
	}
	
	private void printBonito(Node root, String offset){
		if(root == null) return;
		printBonito(root.getRight(), offset + "\t");
		System.out.println(offset + root);
		printBonito(root.getLeft(), offset + "\t");
	}
}
