import java.util.*;

public class Player {
    //Instance variable
    private ArrayDeque<Card> playerDeck = new ArrayDeque<Card>();


    //Constructor
    public Player() {
        playerDeck = new ArrayDeque<Card>(); //empty deck
    }

    //Methods
    //Add a card
    public void addCard(Card c) {
        playerDeck.addLast(c);

    }

    //Check value of deck
    public String toString() {
        String sb = " ";
        for (var card : playerDeck) {
            sb += card.getValue() + " " + card.getSuit() + "\n";
        }
        return sb;
    }

    //Get size of player deck (in cards)
    public int deckSize() {
        return playerDeck.size();
    }

    //Deal a card
    public Card dealCard(){
        return playerDeck.removeFirst();
    }

    //Check card if a card is face up
    public boolean isNextCardFaceUp() {
        return playerDeck.peekFirst().isFaceUp();
    }

    //Reset a deck, both shuffling and flipping every card to face down
    public void resetDeck() {
        for (var card : playerDeck) {
            card.setFaceUpOrDown(false);


        }

        shufflePlayerDeck();

    }
    //Shuffle a deck
    public void shufflePlayerDeck(){
        //The Java ArrayDeque lacks compatibility with the Collections.shuffle, so an ArrayList is used specifically
        //just for shuffling cards
        ArrayList<Card> cardArrayList = new ArrayList<Card>();

        int shuffleLoop = 0;
        //If we compare deckSize directly with shuffleLoop not all cards will be added
        int thisCausedHoursOfIssues = deckSize();

        while (shuffleLoop < thisCausedHoursOfIssues){

            cardArrayList.add(dealCard());

            shuffleLoop ++;
        }
        //The list for the cards is shuffled
        Collections.shuffle(cardArrayList);
        //The cards are added back to the player deck
        while (shuffleLoop != 0){

            addCard(cardArrayList.remove(shuffleLoop-1));

            shuffleLoop --;
        }


    }

}