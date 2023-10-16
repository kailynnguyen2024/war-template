/**
 * War game class
 *
 * @author Mr. Jaffe
 * @version 2022-10-19
 */
public class War
{
    /**
     * Constructor for the game
     * Include your initialization here -- card decks, shuffling, etc
     * Run the event loop after you've done the initializations
     */
    public War()
    {
        // Initializations here...
        Deck mainDeck = new Deck();
        mainDeck.initializeNewDeck();
        mainDeck.shuffle();
        Deck[] playerDecks = mainDeck.dealDeck();
        Deck player1Deck = playerDecks[0];
        Deck player2Deck = playerDecks[1];
        Deck mainPile = new Deck();
        Deck player1Stack = new Deck();
        Deck player2Stack = new Deck();

        // ...then run the event loop
        this.runEventLoop(player1Deck, player2Deck, mainPile, player1Stack, player2Stack);
    }

    /**
     * This is the game's event loop. The code in here should come
     * from the War flowchart you created for this game
     */
    public void runEventLoop(Deck player1Deck, Deck player2Deck, Deck mainPile, Deck player1Stack, Deck player2Stack) {
        System.out.println("War");
        System.out.println("");
        Card cardToStack;
        boolean play = true;
        boolean endGame = false;
        boolean war = false;
        int rounds = 0;
        Card player1TopCard = player1Deck.dealCardFromDeck();
        Card player2TopCard = player2Deck.dealCardFromDeck();
        mainPile.addCardToDeck(player1TopCard);
        mainPile.addCardToDeck(player2TopCard);

        while ((play == true && rounds <=300)) {
            //Checks if the player1 and player2 have at least 1 card in their Deck
            endGame = noCardsInDeck(player1Deck, player1Stack, player2Deck, player2Stack, endGame);

            if (endGame == false && rounds > 0) {
                player1TopCard = player1Deck.dealCardFromDeck();
                player2TopCard = player2Deck.dealCardFromDeck();

                //Adds player2's and player1's card to main pile to store
                mainPile.addCardToDeck(player1TopCard);
                mainPile.addCardToDeck(player2TopCard);
            }


            //checks if a war should start
            if (player1TopCard.getRank() == player2TopCard.getRank() && endGame == false ) {
                System.out.println("Player 1 placed their top card: " + player1TopCard.getFace() + " of " + player1TopCard.getSuit());
                System.out.println("Player 2 placed their top card: " + player2TopCard.getFace() + " of " + player2TopCard.getSuit());
                System.out.println("");
                System.out.println("A war has started!");
                System.out.println("");
                for (int i = 0; i < 4; i++) {
                    if (endGame == false) {
                        endGame = noCardsInDeck(player1Deck, player1Stack, player2Deck, player2Stack, endGame);
                        if (player1Deck.getDeckSize() > 0 && player2Deck.getDeckSize() > 0) {
                            player1TopCard = player1Deck.dealCardFromDeck();
                            player2TopCard = player2Deck.dealCardFromDeck();
                            mainPile.addCardToDeck(player1TopCard);
                            mainPile.addCardToDeck(player2TopCard); 
                        }
                        if (i==3) {
                            System.out.println("Both players have placed 4 cards from their deck.");
                            war = true;
                        }
                    }
                }

                
            }
            //compares cards. if the player1's card rank is better all the cards are added to their pile
            if (player1TopCard.getRank() > player2TopCard.getRank() && endGame == false ) {
                if (war == true) {
                    System.out.println("Player 1's war card is " + player1TopCard.getFace() + " of " + player1TopCard.getSuit());
                    System.out.println("Player 2's war card is " + player2TopCard.getFace() + " of " + player2TopCard.getSuit());
                    war = false;
                }
                else {
                    System.out.println("Player 1 placed their top card: " + player1TopCard.getFace() + " of " + player1TopCard.getSuit());
                    System.out.println("Player 2 placed their top card: " + player2TopCard.getFace() + " of " + player2TopCard.getSuit());
                }
                System.out.println("Player 1 wins " + mainPile.getDeckSize() + " cards and puts it in their stack.");
                

                while (mainPile.getDeckSize() > 0) {
                    cardToStack = mainPile.dealCardFromDeck();
                    player1Stack.addCardToDeck(cardToStack);
                }

                System.out.println("Player 1 has cards: " + player1Deck.getDeckSize()  + " and stack: " + player1Stack.getDeckSize());
                System.out.println("Player 2 has cards: " + player2Deck.getDeckSize()  + " and stack: " + player2Stack.getDeckSize());
                System.out.println("");
                
                rounds++;
            }
            //compares cards. if the player2's card rank ig better all the cards are added to their pile
            else if (player1TopCard.getRank() < player2TopCard.getRank() && endGame == false ) {
                if (war == true) {
                    System.out.println("Player 1's war card is " + player1TopCard.getFace() + " of " + player1TopCard.getSuit());
                    System.out.println("Player 2's war card is " + player2TopCard.getFace() + " of " + player2TopCard.getSuit());
                    war = false;
                }
                else {
                    System.out.println("Player 1 placed " + player1TopCard.getFace() + " of " + player1TopCard.getSuit());
                    System.out.println("Player 2 placed " + player2TopCard.getFace() + " of " + player2TopCard.getSuit());
                }
                System.out.println("Player 2 wins " + mainPile.getDeckSize() + " cards and puts it in their stack.");
                

                while (mainPile.getDeckSize() > 0) {
                    cardToStack = mainPile.dealCardFromDeck();
                    player2Stack.addCardToDeck(cardToStack);  
                }

                System.out.println("Player 1 has cards: " + player1Deck.getDeckSize()  + " and stack: " + player1Stack.getDeckSize());
                System.out.println("Player 2 has cards: " + player2Deck.getDeckSize() + " and stack: " + player2Stack.getDeckSize());
                System.out.println("");
                
                rounds++;
            }

            //checks if game should end if a player
            if (endGame == true) {
                if (player1Deck.getDeckSize() == 0) {
                    System.out.print("Player 1 has lost.");
                }

                if (player2Deck.getDeckSize() == 0) {
                    System.out.print("Player 2 has lost.");
                }

                play = false;
            }

        }
    }

    public boolean noCardsInDeck (Deck player1Deck, Deck player1Stack, Deck player2Deck, Deck player2Stack, boolean endGame) {
        Card cardToDeck;
        if (player1Deck.getDeckSize() == 0 && endGame == false ) {
            if (player1Stack.getDeckSize() > 0) {
                player1Stack.shuffle();
                while (player1Stack.getDeckSize() > 0) {
                    cardToDeck = player1Stack.dealCardFromDeck();
                    player1Deck.addCardToDeck(cardToDeck);
                }
                System.out.println("Player 1 has no cards in their deck, however, they have some in their pile. The cards in their pile has been transferred to their deck.");
                System.out.println("Player 1 has cards: " + player1Deck.getDeckSize() + " and stack: " + player1Stack.getDeckSize());
                endGame = false;
            }
            else {
                endGame = true;
                System.out.println("Player 1 has 0 cards in their deck and in their pile.");
            }
        }
        if (player2Deck.getDeckSize() == 0 && endGame == false ) {
            if (player2Stack.getDeckSize() > 0) {
                player2Stack.shuffle();
                while (player2Stack.getDeckSize() > 0) {
                    cardToDeck = player2Stack.dealCardFromDeck();
                    player2Deck.addCardToDeck(cardToDeck);
                }
                System.out.println("Player 2 has no cards in their deck, however, they have some in their pile. The cards in their pile has been transferred to their deck.");
                System.out.println("Player 2 has cards: " + player2Deck.getDeckSize() + " and stack:  " + player2Stack.getDeckSize());
                endGame = false;
            }
            else {
                endGame = true;
                System.out.println("Player 2 has no cards in their deck and in their pile.");
            }
        }
        return endGame;
    }

    /**
     * The main method is called when Java starts your program
     */
    public static void main(String[] args) {
        War war = new War();
    }
}

