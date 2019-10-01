package fr.mds.pendu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Note : this class has been coded
 * 
 * @author cedri
 *
 */
public class Game {
	String[] words = { "chat", "tigre", "panthere", "lynx" };
	String word; // prend une valeur lors de la construction
	char[] wordArray;
	boolean[] lettersRevealed;
	boolean finished = false;
	int hangmanLife = 0;

	public Game() {
		int theRandom = (int) (4 * Math.random());
		this.word = this.words[theRandom];
	}

	public void startSinglePlayerGame() {
		System.out.println("Starting game in single player mode.");

		this.wordArray = this.word.toCharArray(); // le tableau de char du mot à deviner, depuis le String mot à deviner
													// de la class
		this.lettersRevealed = new boolean[wordArray.length]; // réer un tableau de boolean, associé au tableau de char

		boolean uneLettreTrouvee = false;

		while (!finished) {
			System.out.println("Guess a letter : ");
			Scanner scan = new Scanner(System.in);
			char letterGuessed = scan.next().charAt(0);

			uneLettreTrouvee = false;
			for (int i = 0; i < wordArray.length; i++) {
				if (wordArray[i] == letterGuessed) {
					System.out.println("lettre " + letterGuessed + " trouvée !");
					uneLettreTrouvee = true;
					this.lettersRevealed[i] = true;
				}
			}
			if (uneLettreTrouvee == false) {
				this.hangmanLife++;
			}

			displayWordArray();
			System.out.println(getHangman());

			if (isWordFound()) {
				System.out.println("GAGNÉ");
				finished = true;
			}
			if (this.hangmanLife == 4) {
				System.out.println("PERDU");
				finished = true;
			} // condition de fin
		}
	}

	public void startMultiPlayerGame() {
		
		
		System.out.println("Starting game in multi player mode.");

		System.out.println("Enter the name of the first player : ");
		Scanner sc = new Scanner(System.in);
		String nameP1 = sc.nextLine();
		System.out.println("Enter the name of the second player : ");
		Scanner sc2 = new Scanner(System.in);
		String nameP2 = sc2.nextLine();

		Player p1 = new Player(nameP1, 0, true); 
		Player p2 = new Player(nameP2, 0, false);

		int tour = 0; 
		while (!aPlayerHasWon(p1, p2)) {
			this.hangmanLife= 0 ; 
			
			System.out.println();
			System.out.println("Tour : " + tour++ ) ; 
//			System.out.println();
//			System.out.println(this); 
//			System.out.println(p1);
//			System.out.println(p2);
//			System.out.println();

			if (p1.guesser) {
				System.out.println(p2.name+", enter the word that Player 1 ("+p1.name+") has to guess");
			}
			if (p2.guesser) {
				System.out.println(p1.name+", enter the word that Player 2 ("+p2.name+") has to guess");
			}
			Scanner sc3 = new Scanner(System.in);
			this.word = sc3.nextLine();

			this.wordArray = this.word.toCharArray(); // le tableau de char du mot à deviner, depuis le String mot à
														// deviner de la class
			this.lettersRevealed = new boolean[wordArray.length]; // réer un tableau de boolean, associé au tableau de
																	// char

			boolean uneLettreTrouvee = false;
			finished = false ; 
			while (!finished) {
				System.out.println("Guess a letter : ");
				Scanner scan = new Scanner(System.in);
				char letterGuessed = scan.next().charAt(0);

				uneLettreTrouvee = false;
				for (int i = 0; i < wordArray.length; i++) {
					if (wordArray[i] == letterGuessed) {
						System.out.println("lettre " + letterGuessed + " trouvée !");
						uneLettreTrouvee = true;
						this.lettersRevealed[i] = true;
					}
				}
				if (uneLettreTrouvee == false) {
					this.hangmanLife++;
				}

				displayWordArray();
				System.out.println(getHangman());

				if (isWordFound()) {
					System.out.println("GAGNÉ");
					if (p1.guesser)
						p1.points++;
					if (p2.guesser)
						p2.points++;
					p1.switchRole();
					p2.switchRole();
					finished = true;
				}
				if (this.hangmanLife == 4) {
					System.out.println("PERDU");
					p1.switchRole();
					p2.switchRole();
					finished = true;
				} // condition de fin
			}

		}

	}

	public boolean aPlayerHasWon(Player p1, Player p2) {
		boolean ret = false;
		if (Math.abs(p1.points) - Math.abs(p2.points) >= 2 ) {
			ret = true;
			System.out.println();
			System.out.println("NOUS AVONS UN VAINQUEUR !!");
			System.out.println();
		} else
			ret = false;

		System.out.println("Points player 1 ("+p1.name+") : " + p1.points);
		System.out.println("Points player 2 ("+p2.name+") : " + p2.points);
		return ret;
	}

	public boolean isWordFound() {
		boolean ret = true; // on suppose que tout est trouvé
		for (int i = 0; i < this.lettersRevealed.length; i++) {
			if (this.lettersRevealed[i] == false)
				ret = false; // si un est pas trouvé alors c'est faux
		}
		return ret;
	}

	public void displayWordArray() {
		for (int i = 0; i < this.wordArray.length; i++) {
			if (this.lettersRevealed[i] == true)
				System.out.print(this.wordArray[i]);
			else
				System.out.print("_");
		}
		System.out.println();
	}

	public String getHangman() {
		String ret = "";
		ret += "____________\n";
		ret += "|          |\n";
		ret += "|           \n";
		ret += "|           \n";
		ret += "|           \n";
		ret += "|           \n";
		ret += "|           \n";

		switch (this.hangmanLife) {
		case 0:
			ret = "";
			ret += "____________\n";
			ret += "|          |\n";
			ret += "|           \n";
			ret += "|           \n";
			ret += "|           \n";
			ret += "|           \n";
			ret += "|           \n";
			break;
		case 1:
			ret = "";
			ret += "____________\n";
			ret += "|          |\n";
			ret += "|          O\n";
			ret += "|           \n";
			ret += "|           \n";
			ret += "|           \n";
			ret += "|           \n";
			break;

		case 2:
			ret = "";
			ret += "____________\n";
			ret += "|          |\n";
			ret += "|          O\n";
			ret += "|          |\n";
			ret += "|           \n";
			ret += "|           \n";
			ret += "|           \n";
			break;
		case 3:
			ret = "";
			ret += "____________\n";
			ret += "|          |\n";
			ret += "|          O\n";
			ret += "|         /|\\\n";
			ret += "|           \n";
			ret += "|           \n";
			ret += "|           \n";
			break;
		case 4:
			ret = "";
			ret += "____________\n";
			ret += "|          |\n";
			ret += "|          O\n";
			ret += "|         /|\\\n";
			ret += "|         / \\\n";
			ret += "|           \n";
			ret += "|           \n";
			break;

		default:
			break;
		}

		return ret;
	}

	@Override
	public String toString() {
		return "Game [words=" + Arrays.toString(words) + ", word=" + word + ", wordArray=" + Arrays.toString(wordArray)
				+ ", lettersRevealed=" + Arrays.toString(lettersRevealed) + ", finished=" + finished + ", hangmanLife="
				+ hangmanLife + "]";
	}
	
	
	
}
