package fr.mds.pendu;

import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("Lancement du launcher...");
		
		Game theGame = new Game() ; 
		
		System.out.println("Wich mode do you want to play ? 1: SinglePlayer mode  |  2: MultiPLayer mode");
		Scanner scan = new Scanner(System.in);
		int gameMode = scan.nextInt() ; 
		
		if(gameMode == 1) {  theGame.startSinglePlayerGame(); } 
		else if(gameMode == 2 ) { theGame.startMultiPlayerGame() ; } 
		else { System.err.println("Le mode de jeu ne correspond ni à 1 ni à 2 !"); }
	}
}
