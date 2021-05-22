import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    //Instance Variables
    private ArrayList<Card> deck;

    //Constructor
    public Deck(){
        deck = new ArrayList<Card>(54);
        //Array of strings for suits
        String[] suits = {"Clubs", "Hearts", "Diamonds", "Spades"};
        //Card values will start at 2
        //This loop will add 4 cards with a different suit for each value
        for(int i = 2; i < 15; i++){
            for(var suit: suits){
                Card card = new Card(i, suit, false);
                deck.add(card);
            }

        }
        //Add jokers to the deck
        Card card = new Card(15, "Joker", false);
        deck.add(card);
        deck.add(card);

    }
    //Other methods
    //Check deck
    public String toString(){
        String sb = " ";
        for(var card:deck){
            sb += card.getValue() + " " + card.getSuit() + " " + card.isFaceUp() + "\n";
        }
        return sb;
    }
    //Shuffle decks
    public void shuffle(){
        Collections.shuffle(deck);
    }
    //Deal the cards
    public Card dealCard(){
        if(deck.size() != 0 ){
            return deck.remove(0); //pop card from top of deck
        }
        return null;
    }




    //Get size
    public int getSize(){return deck.size(); }






}
