
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
        Deck cpuDeck = playerDecks[0];
        Deck player1Deck = playerDecks[1];
        Deck mainPile = new Deck();
        
        // ...then run the event loop
        while (cpuDeck.getDeckSize() > 0 && player1Deck.getDeckSize() > 0) {
            this.runEventLoop(cpuDeck, player1Deck, mainPile);
        }
    }
    
    /**
     * This is the game's event loop. The code in here should come
     * from the War flowchart you created for this game
     */
    public void runEventLoop(Deck cpuDeck, Deck player1Deck, Deck mainPile) {
        //Adds cpu's card to main pile for comparing
        Card playerTopCard = player1Deck.dealCardFromDeck();
        mainPile.addCardToDeck(playerTopCard);
        
        //Adds player 1's card to the main pile for comparing
        Card cpuTopCard = player1Deck.dealCardFromDeck();
        mainPile.addCardToDeck(cpuTopCard);
        
        //checks if a war should start
        if (playerTopCard.getRank() == cpuTopCard.getRank()) {
            
        }
        
        //compares cards in the instance that there isn't a war
        if 
    }
    
    /**
     * The main method is called when Java starts your program
     */
    public static void main(String[] args) {
        War war = new War();
    }

}
