package randTest;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {
	
	public enum Shade {SOLID,DASHED,HOLLOW}
	public enum Colors {RED,PURPLE,GREEN}
	public enum Shape {SQUIGGLE,OVAL,DIAMOND}
	
	final int scaleX = 100; // cards are 200 pixels wide (cards lay horizontally)
	final int scaleY = 70; // cards are 70 pixels tall
	
	Image myImage;
	BufferedImage bufferedImage = null;
	Shade myShade;
	Colors myColor;
	Shape myShape;
	int amt; // will be 1, 2, or 3
	
	
	int[] cardMap = {-1,-1,-1,-1};
	
	/**
	 * Constructor that sets the enumerated values for this card
	 * @param a the shade of the card 
	 * @param b the color of the card
	 * @param c the shape on the card
	 * @param d the amount of figures on the card
	 */
	public Card(int a, int b, int c, int d, File imgFile){
		switch(a){
		case 1:
			myShade = Shade.SOLID;
			break;
		case 2:
			myShade = Shade.DASHED;
			break;
		case 3:
			myShade = Shade.HOLLOW;
			break;
		default:
			System.err.println("Error creating a card");
			break;
		}
		switch(b){
		case 1:
			myColor = Colors.RED;
			break;
		case 2:
			myColor = Colors.PURPLE;
			break;
		case 3:
			myColor = Colors.GREEN;
			break;
		default:
			System.err.println("Error creating a card");
			break;
		}
		switch(c){
		case 1:
			myShape = Shape.SQUIGGLE;
			break;
		case 2:
			myShape = Shape.OVAL;
			break;
		case 3:
			myShape = Shape.DIAMOND;
			break;
		default:
			System.err.println("Error creating a card");
			break;
		}
		if(0 < d && d < 4){
			amt = d;
		}
		setCardMap(a,b,c,d);
		setImage(imgFile);
	}
	
	/**
	 * Set the array values that will be used to verify sets 
	 * @param a ^ same as in constructor
	 * @param b
	 * @param c
	 * @param d
	 */
	public void setCardMap(int a, int b, int c, int d){
		cardMap[0] = a;
		cardMap[1] = b;
		cardMap[2] = c;
		cardMap[3] = d;
	}
	
	public int[] getCardMap(){
		return cardMap;
	}
	
	public int setImage(File f){
		try {
			BufferedImage img = ImageIO.read(f);
			myImage = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
			bufferedImage = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_4BYTE_ABGR);
			// bufferedImage.getGraphics().drawImage(image, 0, 0 , null); -- used to print the image later
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public BufferedImage getBufferedImage(){
		return bufferedImage;
	}
}
