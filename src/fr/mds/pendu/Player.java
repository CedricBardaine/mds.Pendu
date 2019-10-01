package fr.mds.pendu;

public class Player {
	String name ; 
	int points ;
	boolean guesser ; 
	
	
	public Player(String name, int points , boolean guesser) {
		this.name = name;
		this.points = points;
		this.guesser = guesser ; 
	} 
	
	
	public void switchRole() {
		if (this.guesser == false) { this.guesser = true ; }
		else if (this.guesser == true) { this.guesser = false ; }
	}


	@Override
	public String toString() {
		return "Player [name=" + name + ", points=" + points + ", guesser=" + guesser + "]";
	}
	
	
	
	
}
