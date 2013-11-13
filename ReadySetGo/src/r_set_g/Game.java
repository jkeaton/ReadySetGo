/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package r_set_g;

import javax.swing.Icon;

/**
 *
 * @author Jestin
 */
public class Game {
    Dealer sam;
    Validator joe;
    Card[][] table;
    public Game(Dealer s, Validator j){
        sam = s;
        sam.shuffle(); // get the deck ready to be used
        joe = j;
        table = new Card[5][3]; // 3 colums and 5 rows created in an array 
        for(int i = 0; i < 5; i++){ /* Clear the cards initially */
            for(int k = 0; k < 3; k++){
                table[i][k] = null;
            }
        }
    }
    
    public Card[][] getInitialTable(){
        for(int i = 0; i < 4; i++){ /* set the initial 12 cards */
            for(int k = 0; k < 3; k++){
                table[i][k] = sam.dealCard();
            }
        }
        return table;
    }
    
    public void addRow(){
        if(!sam.currentIsEmpty()){
            for(int i = 0; i < 3; i++)
                table[4][i] = sam.dealCard();
        }
    }
    
    public boolean positionsAreSet(int a, int b, int c){
        Card first = table[a / 3][a % 3];
        Card second = table[b / 3][b % 3];
        Card third = table[c / 3][c % 3];
        return joe.isSet(first, second, third);
    }
    
    public Icon[] getSetIcons(){
        Icon[] icons = {null,null,null};
        return icons;
    }
}
