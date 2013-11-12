package randTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Dealer {
	ArrayList<Integer> cardNumbers;
	int[] shuffledCardNums;
	ArrayList<Card> currentDeck;
	File[] cardImages = new File[81];
	int[][] cardCombos = new int[81][4];
	
	public Dealer(){
		
		/* Initialize the card numbers in an ArrayList */
		cardNumbers = new ArrayList<Integer>();
		for(int i = 1; i < 82; i++){
			cardNumbers.add(i);
		}
		
		/* Initialize an array to hold shuffledCardNumbers */
		shuffledCardNums = new int[81];
		for(int i = 0; i < 81; i++)
			shuffledCardNums[i] = -1; // set to test modifications
		
		/* Initialize an ArrayList to represent the current deck */
		currentDeck = new ArrayList<Card>();
		
		/* Set card combinations */
		setCardCombos();
		shuffle();
		setNewDeck();
		int r = picTest();
		if(r == -1)
			System.err.println("Error loading card image files");
	}
	
	private int picTest(){
		for(int i = 0; i < 81; i++){
			try{
				cardImages[i] = new File(String.format("cardImage%d%d%d%d.png",cardCombos[i][0],cardCombos[i][1],cardCombos[i][2],cardCombos[i][3]));
			}
			catch(Exception e){
				e.printStackTrace();
				return -1;
			}
		}
		return 0;
	}
	
	/**
	 * Shuffle the values in the shuffledCardNums array
	 */
	public void shuffle(){
		Random gen = new Random(System.nanoTime()); /* Seed with current time (nanoseconds) */
		// remove a random index from the arrayList to add to the shuffledCardNums array
		for(int j = 0; j < 81; j++)
			shuffledCardNums[j] = cardNumbers.remove(gen.nextInt(cardNumbers.size()));
	}
	
	/**
	 * Reset the Vectors so we have a brand new set of 81 shuffled decks
	 */
	public void reinitialize(){
		/* Clear and refill cardNumbers */
		cardNumbers.clear();
		for(int i = 1; i < 82; i++){
			cardNumbers.add(i);
		}
		/* Reset shuffled array */
		for(int i = 0; i < 81; i++)
			shuffledCardNums[i] = -1; // set to test modifications
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
						fillCounter++;
					}
				}
			}
		}
	}
	
	public Card dealCard(){
		if(!currentIsEmpty())
			return currentDeck.remove(0);
		else{
			return null;
		}
	}
	
	public boolean currentIsEmpty(){
		return currentDeck.isEmpty();
	}
	
	public void setNewDeck(){
		currentDeck.clear();
		for(int i : shuffledCardNums){
			Card c = new Card(cardCombos[i-1][0],cardCombos[i-1][1],cardCombos[i-1][2],cardCombos[i-1][3]/*,cardImages[i]*/);
			currentDeck.add(c);
		}
	}
}
