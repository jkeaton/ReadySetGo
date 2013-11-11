package randTest;

import java.util.Vector;

public class RandomTest {
	public static void main(String[]args){
		Dealer sam = new Dealer();
		/* Create decks to be tested */
		testRand(sam);
	}
	
	/**
	 * Test the Randomness of the Deck Shuffle
	 * @param d This is a Dealer object
	 * @return the number of times a card appears in the same postion in 81 shuffled decks
	 */
	private static void testRand(Dealer d){
		Vector<Vector<Card>> decks = new Vector<Vector<Card>>();
		Card[][] cards = new Card[10][81];
		for(int i = 0; i < 10; i++){
			d.testNewDeck();
			Vector<Card> v = d.getNewDeck();
			decks.add(v);
			System.err.println(v);
		}
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 81; j++){
				cards[i][j] = decks.elementAt(i).elementAt(j);
			}
		}
	}
}
