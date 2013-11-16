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
    Icon[] icons;
    int tableCardCount = 0;
    public Game(Dealer s, Validator j){
        icons = new Icon[3];
        sam = s;
        sam.shuffle(); // get the deck ready to be used
        sam.setNewDeck();
        joe = j;
        table = new Card[3][5]; // 3 rows and 5 columns 
        for(int i = 0; i < 3; i++){ /* Clear the cards initially */
            for(int k = 0; k < 5; k++){
                table[i][k] = null;
            }
        }
    }
    
    public void setInitialTable(){
        for(int k = 0; k < 3; k++){ /* set the initial 12 cards */
            for(int i = 0; i < 4; i++){ 
                table[k][i] = sam.dealCard();
                if(table[k][i] != null)
                    tableCardCount++; // increment if actual card is added
                if(tableCardCount > 12)
                    System.out.println("k: "+k+" i: "+i+"\n");
                System.out.println("Table count after initialization: "+tableCardCount);
            }
        }
    }
    
    public void addColumn(){
        if(!sam.currentIsEmpty()){
            for(int i = 0; i < 3; i++)
                table[i][4] = sam.dealCard(); // Add to column to right
            tableCardCount += 3;
        }
    }
    
    public boolean positionsAreSet(int a, int b, int c){
        Card first = table[a / 5][a % 5];
        Card second = table[b / 5][b % 5];
        Card third = table[c / 5][c % 5];
        return joe.isSet(first,second,third);
    }
    
    public Card getCardAtPos(Integer a){
        if(a>=0 && a <=14){
            try{
                return table[a / 5][a % 5];
            }
            catch(Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
        else{
            return null;
        }
    }
    
    public void reorganizeCards() throws InterruptedException{
        if(tableCardCount < 12)
            addColumn();
        if(tableCardCount <= 12){
            int a, b;
            boolean holeFound,holeFilled;
            do{
                holeFound = false;
                holeFilled = false;
                a = -1;
                b = -1;
                /* Find Hole */
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < tableCardCount/3; j++){
                        /* Hole Found */
                        if(table[i][j] == null){
                            a = i;
                            b = j;
                            holeFound = true;
                            break; // found a hole, stop looking for one
                        }
                        if(holeFound)
                            break;
                    }
                }
                if(holeFound){
                    for(int i = 2; i >= 0; i--){
                        for(int j = 4; j >= tableCardCount/3; j--){
                            /* Found a card to reposition */
                            if(table[i][j] != null){
                                table[a][b] = table[i][j];
                                table[i][j] = null;
                                holeFilled = true;
                                break; // replaced one hole, next extra card is for next hole
                            }
                        }
                        if(holeFilled)
                            break;
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
        table[a / 5][a % 5] = null;
        table[b / 5][b % 5] = null;
        table[c / 5][c % 5] = null;
        tableCardCount = tableCardCount - 3;
        icons[0] = null;
        icons[1] = null;
        icons[2] = null;
    }
    
    public int getTableCardCount(){
        return tableCardCount;
    }
    
    public Card[][] getCurrentTable(){
        return table;
    }
    
    public Icon[] getSetIcons(int a, int b, int c){
        icons[0] = getCardAtPos(a).getIconImage();
        icons[1] = getCardAtPos(b).getIconImage();
        icons[2] = getCardAtPos(c).getIconImage();
        return icons;
    }
}
