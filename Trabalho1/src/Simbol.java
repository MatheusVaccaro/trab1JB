
public class Simbol {
	char character;
	int frequency;
	
	public Simbol(char character){
		this.character = character;
		this.frequency = 1;
	}
	
	private void update(){
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
}
