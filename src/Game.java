import java.util.ArrayList;
import java.util.*;

public class Game {
    private ArrayList<Integer> cards = new ArrayList<Integer>();
    private ArrayList<Integer> activeCards = new ArrayList<Integer>();
    private Player P1 = new Player();
    private Player P2 = new Player();
    private boolean gameActive = false;
    private int roundsPlayed = 0;
    
    public Game(){
        
        for(int i = 0; i<13; i++){
            for(int j = 0; j<4; j++){
                this.cards.add(i);
            }
        }
        this.cards.add(13); this.cards.add(13);
        
    }
    
    public void shuffleDeck(){
        Collections.shuffle(this.cards);
        System.out.println("All cards have been shuffled!");
    }
    
    public void start(){
        P1.reset();
        P2.reset();
        this.shuffleDeck();
        this.splitCardsBetweenPlayers();
        this.gameActive = true;
        
        System.out.println("Game has started!");
        
        this.run();
    }
    
    private void splitCardsBetweenPlayers(){
        P1.setHand(new ArrayList( this.cards.subList(0,27) ) );
        P2.setHand(new ArrayList( this.cards.subList(27,this.cards.size()) ) );
        
        System.out.println("Cards have been dealt to players.");
    }
    
    private void run(){
        
        while(this.gameActive){
            if(!P1.hasCards()){
                this.gameActive = false;
                System.out.println("Game has ended after "+roundsPlayed+" rounds.");
                break;
            }
            if(!P2.hasCards()){
                this.gameActive = false;
                System.out.println("Game has ended after "+roundsPlayed+" rounds.");
                break;
            }
            
            this.activeCards.add(P1.playCard());
            this.activeCards.add(P2.playCard());
            
            compare(this.activeCards.get(0), this.activeCards.get(1) );
            
            roundsPlayed = roundsPlayed+1;
        }
    }
    
    private void compare(Integer P1Card, Integer P2Card){
        if (P1Card > P2Card) {
            P1.addCardsToWinningHand(this.activeCards);
            this.activeCards.clear();
            
            //System.out.println("Player 1 won this round!");
        }
        
        if(P2Card>P1Card){
            P2.addCardsToWinningHand(this.activeCards);
            this.activeCards.clear();
            
            //System.out.println("Player 2 won this round!");
        }
        
        if(P1Card == P2Card){
            this.war();
        }
    }
    
    private void war(){
        
        for( int i = 0; i<2; i++ ){
            this.activeCards.add(P1.playCard());
            this.activeCards.add(P2.playCard());  
        }

                
        if(this.activeCards.get(this.activeCards.size()-1) > this.activeCards.get(this.activeCards.size()-2)){
            P2.addCardsToWinningHand(this.activeCards);
            this.activeCards.clear();           
            //System.out.println("Player 2 has won the war!");
        }else if(this.activeCards.get(this.activeCards.size()-1) < this.activeCards.get(this.activeCards.size()-2)){
            P1.addCardsToWinningHand(this.activeCards);
            this.activeCards.clear();               
            //System.out.println("Player 1 has won the war!");
        }else{
            if(this.activeCards.get(this.activeCards.size()-2) > this.activeCards.get(this.activeCards.size()-3)){
                P2.addCardsToWinningHand(this.activeCards);
                this.activeCards.clear();                   
                //System.out.println("Player 2 has won the war!");
            }else if(this.activeCards.get(this.activeCards.size()-2) < this.activeCards.get(this.activeCards.size()-3)){
                P1.addCardsToWinningHand(this.activeCards);
                this.activeCards.clear();                    
                //System.out.println("Player 1 has won the war!");
            }            
        }
 
    }
}
