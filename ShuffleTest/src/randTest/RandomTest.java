package randTest;

public class RandomTest {
	
	public static void main(String[]args){
		Dealer sam = new Dealer();
		/* Test the Dealer */
		testRand(sam);
	}
	
	/**
	 * Test the Randomness of the Deck Shuffle
	 * @param d This is a Dealer object
	 * @return the number of times a card appears in the same postion in 81 shuffled decks
	 */
	private static void testRand(Dealer d){
		while(!d.currentIsEmpty()){
			System.out.println(d.dealCard());
		}
	}
}
