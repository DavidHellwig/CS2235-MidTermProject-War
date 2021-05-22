import java.lang.Integer;

public class War {

    //Instance Variables
    //These specific ints will allow math to be done with averages in another file
    //Number of rounds
    int round = 0;
    //Number of wars, entire program broke when war was set to 0, can't be changed
    int warCounter = 1;
    //If player one wins, this number will be 1, but never more than 1
    int playerOneWins = 0;
    //If player two wins, this number will be 1, but never more than 1
    int playerTwoWins = 0;
    //This boolean will let the program know if the game is over, is set to false(not over)
    boolean isGameOver = false;

    Player playerOne;

    Player playerTwo;

    Table warTable = new Table();

    //Constructor
    public War(){
        //initialize the game
        initialize();
    }
    //Initialize Players and decks for the game so the game can be player
    public void initialize(){
        //Methods for war
        //Create players
        Player Player_1 = new Player();
        Player Player_2 = new Player();
        Deck myDeck = new Deck();
        //Shuffle
        myDeck.shuffle();


        //Populate decks
        for(int i = 0 ; i < 27 ; i++){
            Player_1.addCard(myDeck.dealCard());
            Player_2.addCard(myDeck.dealCard());
        }

        //Set instance variables of player to be these players
        playerOne = Player_1;
        playerTwo = Player_2;

        }
    //Compare the values of cards to see who won, adds cards to the winners hand, or starts a war(battleStage())
    public void compareCardValue(Player playerOne, Player playerTwo, int comparator){
        if (comparator == 1){
            addAll(playerOne);

            System.out.printf("Player 1 has won the %sth round\n", round);

            System.out.printf("Player 1 has %s cards left!\n",playerOne.deckSize());

            System.out.printf("Player 2 has %s cards left!\n",playerTwo.deckSize());

        }
        else if (comparator == -1){
            addAll(playerTwo);
            System.out.printf("Player 2 has won the %sth round\n", round);

            System.out.printf("Player 2 has %s cards left!\n",playerTwo.deckSize());

            System.out.printf("Player 1 has %s cards left!\n",playerOne.deckSize());
        }
        else if(comparator == 0){
            System.out.printf("There was a draw! Time for the %sth war and the %sth round!\n", warCounter,round);
            //If we reach this and either player has 0 cards and there is a tie, the player with no cards must lose
            if (playerOne.deckSize() == 0 || playerTwo.deckSize() ==0){
                checkEndGame();
            }
            else{
                //Begin a war
                battleStage(playerOne,playerTwo);
            }

        }
        //It shouldn't be possible for this condition to happen, but can happen if something went very wrong
        else {
            System.out.println("An error has occured in : War:52");
            System.exit(1);
        }
    }
    //Start the game
    public void gameStart(Player playerOne, Player playerTwo) {

        while (isGameOver == false){
            checkEndGame();
            if (isGameOver == true){
                break;
            }
            round ++;

            Card playerOneCard = playerOne.dealCard();

            playerOneCard.setFaceUpOrDown(true);

            System.out.printf("Player one plays %s \n",playerOneCard.checkAll());

            warTable.addCard(playerOneCard);

            Card playerTwoCard = playerTwo.dealCard();

            playerTwoCard.setFaceUpOrDown(true);

            System.out.printf("Player two plays %s \n",playerTwoCard.checkAll());

            warTable.addCard(playerTwoCard);
            //Uses Java's Integer.compare() method to compare card values
            int comparator = Integer.compare(playerOneCard.getValue(),playerTwoCard.getValue());
            //Compare the result of the above statement to find out who won
            compareCardValue(playerOne,playerTwo,comparator);



        }
    }
    //This method will handle a war(battle stage)
    public void battleStage(Player playerOne, Player playerTwo){
        //Check to see if the game shouldn't continue
        checkEndGame();
        //Return from this method if isGameOver says the game is over, in case the above statement fails
        if (isGameOver == true){
            return;
        }

        round ++;

        warCounter ++;
        //We know the first two cards won't be face up, just immediately add them to the table
        warTable.addCard(playerOne.dealCard());

        warTable.addCard(playerTwo.dealCard());
        //Check again to see if we need to stop the game
        checkEndGame();
        if (isGameOver == true){
            return;
        }
        Card playerOneNextCard = playerOne.dealCard();

        playerOneNextCard.setFaceUpOrDown(true);

        System.out.printf("Player one plays %s \n", playerOneNextCard.checkAll());

        warTable.addCard(playerOneNextCard);

        Card playerTwoNextCard = playerTwo.dealCard();

        playerTwoNextCard.setFaceUpOrDown(true);

        System.out.printf("Player two plays %s \n", playerTwoNextCard.checkAll());

        warTable.addCard(playerTwoNextCard);

        int recursiveComparator = Integer.compare(playerOneNextCard.getValue(),playerTwoNextCard.getValue());
        //Compare the face up cards
        compareCardValue(playerOne,playerTwo,recursiveComparator);
    }
    //Add all the cards in table
    public void addAll(Player player){

        int j = 0;

        //This loop will handle adding all the cards
        int thisAlsoTook8hoursToFix = warTable.getSize();

        while (j< thisAlsoTook8hoursToFix){

            player.addCard(warTable.removeCard());
            j++;
        }
    }
    //Reset the decks and then start the game again if no one has won yet
    public void resetGame(Player playerOne , Player playerTwo){

        playerOne.resetDeck();

        playerTwo.resetDeck();

    }
    //Check to see if the game should be over and displays who won along with a summary
    public void checkEndGame(){

        if (playerOne.deckSize() == 0){

            addAll(playerTwo);

            System.out.println("Player 1 has run out of cards, Player 2 Wins");

            System.out.printf("There were %s rounds, and %s wars.\n", round, warCounter);

            playerTwoWins ++;

            //Game should be over, so isGameOver is set to true
            isGameOver = true;

        }
        else if (playerTwo.deckSize() == 0){

            addAll(playerOne);

            System.out.println("Player 2 has run out of cards, player 1 wins");

            System.out.printf("There were %s rounds, and %s wars.\n", round, warCounter);

            playerOneWins ++;

            isGameOver = true;

        }
        //If there are still cards left AND the next card is face up, reset both decks and start the game again
        else if (playerOne.isNextCardFaceUp() == true || playerTwo.isNextCardFaceUp() == true){
            System.out.println("Someone drew a face up card, time to shuffle the decks!\n");
            resetGame(playerOne,playerTwo);

            gameStart(playerOne, playerTwo);
        }
    }
    //Get the amount of rounds in the game
    public int getRounds(){

        return round;
    }
    //Get the amount of wars in the game
    public int getWarCounter(){

        return warCounter;
    }
    //Returns 1 if player one wins
    public int getPlayerOneWins(){

        return playerOneWins;
    }
    //Returns 1 if player two winds
    public int getPlayerTwoWins(){
        return playerTwoWins;
    }


    }

