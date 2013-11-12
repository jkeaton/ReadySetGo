package randTest;

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
		d.testNewDeck();
		d.setNewDeck();
		while(!d.currentIsEmpty()){
			System.out.println(d.dealCard());
		}
	}
}
