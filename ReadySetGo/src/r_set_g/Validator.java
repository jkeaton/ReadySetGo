/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package r_set_g;

/**
 *
 * @author Jestin
 */
public class Validator {
    public Validator(){}
    
    public boolean setAvailable(Card[][] c){
        int counter_a = 0;
        int counter_b = 1;
        int counter_c = 2;
        boolean foundSet = false;
        Card firstCard, secondCard, thirdCard;
        while(counter_a < 13 && !foundSet){
            if(counter_a < 13 && counter_b > counter_a && counter_c > counter_b && counter_c < 15){
                // set cards
                firstCard = c[counter_a / 3][counter_a % 3];
                secondCard = c[counter_b / 3][counter_b % 3];
                thirdCard = c[counter_c / 3][counter_c % 3];
                if(isSet(firstCard,secondCard,thirdCard)){
                    foundSet = true;
                    break;
                }
                else{ // we must move the counters that represent the cards
                    /* If the 3rd counter is all the way at the end and there is */
                    /* room to increment the 2nd counter                         */
                    /* In other words, counter 3 is allowed to wrap after        */
                    /* incrementing counter 2                                    */
                    if(counter_c == 14 && counter_b < 13){
                        counter_b++;
                        counter_c = counter_b + 1;
                    }
                    /* If the 2nd and 3rd counters are all the way to the end  */
                    /* but there is still room to increment the 1st counter    */
                    /* In other words, counter 3 is allowed to wrap only after */
                    /* incrementing counter 1 and counter 2                    */
                    else if(counter_c == 14 && counter_b == 13 && counter_a < 12){
                        counter_a++;
                        counter_b = counter_a + 1;
                        counter_c = counter_b + 1;
                    }
                    /* We can't increment counter_a again, break out of loop */
                    else if(counter_a == 12)
                        break;
                    /* Only increment counter_c, other 2 stay put */
                    else
                        counter_c++;
                }
            }
            else
                break;
        }
        return foundSet;
    }
    
    public boolean isSet(Card a, Card b, Card c){
        if(a == null || b == null || c == null)
            //return "not a set";
            return false;
        /* Check that each of the 4 conditions are met */
        for(int i = 0; i < 4; i++){
            if(a.getCardMap()[i] == b.getCardMap()[i] && a.getCardMap()[i] != c.getCardMap()[i])
                //return "not a set";
                return false;
            else if(a.getCardMap()[i] != b.getCardMap()[i] && (b.getCardMap()[i] == c.getCardMap()[i] || a.getCardMap()[i] == c.getCardMap()[i]))
                //return "not a set";
                return false;
        } // If it gets through this gauntlet, it is a set;
        //return ("\nThe Following is a set:\n"+a+b+c+"\n");
        return true;
    }
}
