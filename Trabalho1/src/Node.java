
public class Node {
	private Node left;
	private Node right;
	private Simbol simbol;
	private int accFreq;
	
	public Node(){
		this.left = null;
		this.right = null;
		this.simbol = null;
		this.accFreq = 0;
	}
	
	public Node(Simbol simbol){
		this.simbol = simbol;
		this.accFreq = simbol.getFrequency();
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

	public int getAccFreq() {
		return accFreq;
	}

	public void setAccFreq(int accFreq) {
		this.accFreq = accFreq;
	}

	public Simbol getSimbol() {
		return simbol;
	}

	public void setSimbol(Simbol simbol) {
		this.simbol = simbol;
	}

	@Override
	public String toString() {
		return "Node [left=" + left + ", right=" + right + ", simbol=" + simbol
				+ ", accFreq=" + accFreq + "]";
	}

	
}
