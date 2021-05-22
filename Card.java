public class Card {
    //Instance variables
    int value;
    String suit;
    boolean isFaceUp;

    //Constructor
    public Card(int value, String suit, Boolean isFaceUp){
        this.value=value;
        this.suit=suit;
        this.isFaceUp = isFaceUp;

    }
    //Methods
    //Get the face value of the card
    public int getValue(){return value;}

    //Get the suit of the card
    public String getSuit(){return suit;}

    //Return the position of the card (face up or face down)

    public boolean isFaceUp(){return isFaceUp;}

    //Set the position of the card (true = face up, false = face down)
    public void setFaceUpOrDown(boolean b){this.isFaceUp = b;}

    //Prints certain properties of a card
    public String checkAll(){
        String v = String.valueOf(getValue());
        String s = String.valueOf(getSuit());

        //This concatenate a summary of a card, but not whether or not it is face down.
        String message = v + " " + s + " "+ "\n";
        return message;
    }


}
