package randTest;

import java.io.File;
import java.util.Vector; 
import java.util.Random;

public class Dealer {
	Vector<Vector<Integer>> possibilities;
	Vector<Vector<Integer>> actualDecks;
	Vector<Integer> nextVec;
	File[] cardImages = new File[81];
	int[][] cardCombos = new int[81][4];
	
	public Dealer(){
		/* Initialize the position Vectors */
		possibilities = new Vector<Vector<Integer>>();
		actualDecks = new Vector<Vector<Integer>>();
		nextVec = new Vector<Integer>();
		setCardCombos(); // set card combinations 
		/* Initialize 81 sets of cards */
		for(int i = 0; i < 81; i++){
			Vector<Integer> v = new Vector<Integer>();
			possibilities.add(v);
			/* Each of the 81 cards has 81 possible positions. */
			/* By keeping track of which positions for each    */
			/* card have been used already, we ensure that no  */
			/* card is placed in the same position in a set of */
			/* 10 shuffled decks                               */
			for(int j = 1; j < 82; j++){
				v.add(j);
			}
		}
		int r = picTest();
		if(r == -1)
			System.err.println("Error loading card image files");
		shuffle();
	}
	
	private int picTest(){
		for(int i = 0; i < 81; i++){
			cardImages[i] = new File(String.format("cardImage%d%d%d%d.png",cardCombos[i][0],cardCombos[i][1],cardCombos[i][2],cardCombos[i][3]));
		}
		return 0;
	}
	
	/**
	 * Fill 10 decks with cards that are never in the same spot twice
	 */
	public void shuffle(){
		Random gen = new Random(System.currentTimeMillis()); /* Seed with current time (ms)*/
		for(int i = 0; i < 10; i++){ // 10 decks
			Vector<Integer> v = new Vector<Integer>();
			actualDecks.add(v);
			for(int j = 0; j < 81; j++){
				int currInt = gen.nextInt(possibilities.elementAt(j).size());
				while(v.indexOf(possibilities.elementAt(j).elementAt(currInt))!= -1){
					currInt = (currInt + 1)%possibilities.elementAt(j).size();
				}
				v.add(possibilities.elementAt(j).remove(currInt));
			}
		}
	}
	
	/**
	 * Reset the Vectors so we have a brand new set of 81 shuffled decks
	 */
	public void resetVecs(){
		/* Clear actual decks */
		for(Vector<Integer> v : actualDecks){
			v.clear();
		}
		for(Vector<Integer> v : possibilities){
			v.clear();
		}
		actualDecks.clear(); // empty the actual decks to free up space again
		/* Clear possibilities */
		possibilities.clear();
		for(int i = 0; i < 81; i++){
			Vector<Integer> v = new Vector<Integer>();
			possibilities.add(v);
			for(int j = 1; j < 82; j++){
				v.add(j);
			}
		}
		shuffle();
	}
	
	/**
	 * Recursively gets me a new deck for each call
	 * @return new shuffled deck 
	 */
	public void testNewDeck(){
		if(actualDecks.size()>1){
			nextVec = actualDecks.remove(0);
		}
		else if(actualDecks.size() == 1){
			nextVec = actualDecks.remove(0);
			resetVecs();
		}
	}
	
	private void setCardCombos(){
		int fillCounter = 0;
		/* This array will be used to set the card values later */
		for(int i = 1; i < 4; i++){
			for(int j = 1; j < 4; j++){
				for(int k = 1; k < 4; k++){
					for(int m = 1; m < 4; m++){
						cardCombos[fillCounter][0] = i;
						cardCombos[fillCounter][1] = j;
						cardCombos[fillCounter][2] = k;
						cardCombos[fillCounter][3] = m;
					}
				}
			}
		}
	}
	
	public Vector<Card> getNewDeck(){
		Vector<Card> myVec = new Vector<Card>();
		for(int i : nextVec){
			Card c = new Card(cardCombos[i][0],cardCombos[i][1],cardCombos[i][2],cardCombos[i][3],cardImages[i]);
			myVec.add(c);
		}
		return myVec;
	}
}
