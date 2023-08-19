import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private ArrayList<Integer> winnings = new ArrayList<Integer>();
    private ArrayList<Integer> hand = new ArrayList<Integer>();
    
    public void Player(){
    }
    
    public void reset(){
        winnings.clear();
        hand.clear();
    }
   
    public void setHand(ArrayList cards){
        this.hand = cards;
    }
    
    public Integer playCard(){
        if(this.hand.isEmpty()){
            Collections.shuffle(this.winnings);
            this.hand.addAll(this.winnings);
            this.winnings.clear();
        }
        
        Integer cardPlayed = this.hand.get(this.hand.size()-1);
        this.hand.remove(this.hand.size()-1);
        return cardPlayed;
    }
    
    public boolean hasCards(){
        if(this.winnings.size() > 0 || this.hand.size() > 0){
            return true;
        }
        
        return false;   
    }
    
    
    public void addCardsToWinningHand(ArrayList<Integer> cards){       
        this.winnings.addAll(cards);
    }
}
