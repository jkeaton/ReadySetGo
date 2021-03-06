package r_set_g;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;

enum Shade {SOLID,DASHED,HOLLOW}
enum Colors {RED,BLUE,GREEN}
enum Shape {SQUIGGLE,OVAL,DIAMOND}

public class Card {
    final int scaleX = 100; // cards are 200 pixels wide (cards lay horizontally)
    final int scaleY = 70; // cards are 70 pixels tall

    Image myImage;
    Icon img = null;
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
    public Card(int a, int b, int c, int d,/* File imgFile*/String s){
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
                myColor = Colors.BLUE;
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
        //setImage(imgFile);
        setImage(s);
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

    public int setImage(/*File f*/String s){
        String modifiedImagePath = "/r_set_g/"+s;
        img = (new javax.swing.ImageIcon(getClass().getResource(modifiedImagePath)));
        return 0;
    }

    public Icon getIconImage(){
        return img;
    }

    public String toString(){
        return String.format("Card : %d%d%d%d\n",cardMap[0],cardMap[1],cardMap[2],cardMap[3]);
    }
}
