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
    int tableCardCount = 0;
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
                if(table[i][k] != null)
                    tableCardCount++; // increment if actual card is added
            }
        }
        return table;
    }
    
    public void addRow(){
        if(!sam.currentIsEmpty()){
            for(int i = 0; i < 3; i++)
                table[tableCardCount/3][i] = sam.dealCard(); // Add to bottom row
        }
    }
    
    public boolean positionsAreSet(int a, int b, int c){
        Card first = table[a / 3][a % 3];
        Card second = table[b / 3][b % 3];
        Card third = table[c / 3][c % 3];
        return joe.isSet(first,second,third);
    }
    
    public Card getCardAtPos(int a){
        if(a>=0 && a <=14){
            System.out.println("hello");
            return table[a / 3][a % 3];
        }
        else
            return null;
    }
    
    public void reorganizeCards(){
        if(tableCardCount <= 12 && joe.setAvailable(table)){
            int a, b;
            boolean holeFound;
            do{
                holeFound = false;
                a = -1;
                b = -1;
                /* Find Hole */
                for(int i = 0; i < tableCardCount/3; i++){
                    for(int j = 0; j < 3; j++){
                        /* Hole Found */
                        if(table[i][j] == null){
                            a = i;
                            b = j;
                            holeFound = true;
                        }
                    }
                }
                if(holeFound){
                    for(int i = 4; i >= tableCardCount/3; i--){
                        for(int j = 2; j <= 0; j--){
                            /* Found a card to reposition */
                            if(table[i][j] != null){
                                table[a][b] = table[i][j];
                                table[i][j] = null;
                            }
                        }
                    }
                }
            }
            while(holeFound); // Only as long as a hole remains
        }
    }
    
    public boolean setAvailable(){
        return joe.setAvailable(table);
    }
    
    public void removeSelection(int a, int b, int c){
        table[a / 3][a % 3] = null;
        table[b / 3][b % 3] = null;
        table[c / 3][c % 3] = null;
        tableCardCount = tableCardCount - 3;
    }
    
    public int getTableCardCount(){
        return tableCardCount;
    }
    
    public Card[][] getCurrentTable(){
        return table;
    }
    
    public Icon[] getSetIcons(int a, int b, int c){
        /* This is stubbed out for now */
        /* This method should return the icons cooresponding to */
        /* the cards in the set */
        Icon[] icons = {null,null,null};
        return icons;
    }
}
