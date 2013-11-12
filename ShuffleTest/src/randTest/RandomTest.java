package randTest;

public class RandomTest {
	
	public static void main(String[]args){
		System.err.println("creating the dealer\n");
		Dealer sam = new Dealer();
		System.err.println("done creating the dealer\n");
		/* Test the Dealer */
		testRand(sam);
		System.err.println("finished with testRand(sam)\n");
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
