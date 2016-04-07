
public class Simbol {
	char character;
	int frequency;
	
	public Simbol(char character){
		this.character = character;
		this.frequency = 1;
	}
	
	public void update(){
		this.frequency++;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/*@Override
	public String toString() {
		return "Simbol [character=" + character + ", frequency=" + frequency
				+ "]";
	}*/
	
	public String toString(){
		return "" + this.character;
	}
	
}
