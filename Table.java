import java.util.ArrayList;
public class Table {
    //Instance variables
    ArrayList<Card> table = new ArrayList<Card>();
    private int size = 0;



    //Add a card to the table
    public void addCard(Card card) {
        table.add(card);
        size ++;

    }
    //Remove a card from the table
    public Card removeCard(){
        Card tableCard =  table.remove(0);
        size --;
        return tableCard;



    }
    //Return the amount of cards held on the table
    public int getSize(){return size;}



        }










