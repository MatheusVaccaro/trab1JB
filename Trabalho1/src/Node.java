
public class Node {
	private Node left;
	private Node right;
	private Simbol simbol;
	
	public Node(){
		this.left = null;
		this.right = null;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Simbol getSimbol() {
		return simbol;
	}

	public void setSimbol(Simbol simbol) {
		this.simbol = simbol;
	}
}
