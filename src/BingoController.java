import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class BingoController {

    private final String[] mainMenuItems = {"Exit",
            "Play bingo",
            "Set number separator",
            "Create a bingo card",
            "List existing cards",
            "Set bingo card size"};

    /**
     * complete constants attached to mainMenuItems
     */
    private final String OPTION_EXIT = "0";
    private final String OPTION_PLAY = "1";
    private final String OPTION_SEPARATOR = "2";
    private final String OPTION_CREATE_CARD = "3";
    private final String OPTION_LIST_CARDS = "4";
    private final String OPTION_SIZE = "5";

    /**
     * complete default size of rows / columns as specified in the Defaults class
     * create an arraylist of BingoCard cards
     * include getters and setters for row / column sizes
     */
    private int currentRowSize = Defaults.DEFAULT_NUMBER_OF_ROWS;
    private int currentColumnSize = Defaults.DEFAULT_NUMBER_OF_COLUMNS;

    /**
     * create an ArrayList of BingoCard cards
     */
    private ArrayList<BingoCard> cards = new ArrayList<>();

    /**
     * implement getters and setters for currentRowSize / currentColumnSize
     */
    public int getCurrentRowSize() {
        /**
         change the return from 0 to the appropriate return
         */
        return currentRowSize;
    }

    public void setCurrentRowSize(int currentRowSize) {
        /**
         implement code here
         */
        this.currentRowSize = currentRowSize;
    }

    public int getCurrentColumnSize() {
        /**
         change the return from 0 to the appropriate return
         */
        return currentColumnSize;
    }

    public void setCurrentColumnSize(int currentColumnSize) {
        /**
         implement code here
         */
        this.currentColumnSize = currentColumnSize;
    }

    /**
     * add a new BingoCard card
     */
    public void addNewCard(BingoCard card) {

        cards.add(card);
    }

    /**
     * include an appropriate message to the the number of rows as well as the number of columns
     */
    public void setSize() {
        setCurrentRowSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of rows for the card").trim()));
        setCurrentColumnSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of columns for the card").trim()));
        System.out.printf("The bingo card size is set to %d rows X %d columns%n",
                getCurrentRowSize(),
                getCurrentColumnSize());
    }

    /**
     * ensure that the correct amount of numbers are entered
     * print a message that shows the numbers entered using Toolkit.printArray(numbers)
     * create, setCardNumbers and add the card as a BingoCard
     */
    public void createCard() {
        /**
         calculate how many numbers are required to be entered based on the number or rows / columns
         */
        int numbersRequired = currentRowSize * currentColumnSize;

        String[] numbers;

        boolean correctAmountOfNumbersEntered;


        do {
            numbers = Toolkit.getInputForMessage(
                            String.format(
                                    "Enter %d numbers for your card (separated by " +
                                            "'%s')",
                                    numbersRequired,
                                    Defaults.getNumberSeparator()))
                    .trim()
                    .split(Defaults.getNumberSeparator());

            /**
             verify if the correctAmountOfNumbersEntered is true or false dependant on the numbersRequired calculation
             */
            correctAmountOfNumbersEntered = false; //changes according to calculation inserted here

            /**
             verify whether the numbers entered is not correct by printing an appropriate message
             verify against the expected output files
             */
            if (numbers.length != numbersRequired)
                System.out.printf("Try again: you entered %d numbers instead of %d%n", numbers.length, numbersRequired);
            else {
                correctAmountOfNumbersEntered = true;
            }

        } while (!correctAmountOfNumbersEntered);

        /**
         print an appropriate message using ToolKit.printArray() to show the numbers entered
         */
        System.out.println("You entered");
        System.out.println(Toolkit.printArray(numbers));

        /**
         create new BingoCard
         */

        BingoCard card = new BingoCard(currentRowSize, currentColumnSize);

        /**
         setCardNumbers for the new card
         */

        card.setCardNumbers(numbers);
        /**
         add the card to the ArrayList
         */

        addNewCard(card);

    }

    /**
     * this method goes with printCardAsGrid() seen below
     * when option 4 is chosen to list existing cards it prints each card accordingly
     * for example, it should show the following
     * Card 0 numbers:
     * 1  2
     * 3  4 (check with expected output files)
     */
    public void listCards() {
        /**
         insert code here to find all cards to be printed accordingly
         */

        for (int x = 0; x < cards.size(); x++) {
            System.out.printf("Card%3d numbers:%n", x);

            /**
             call printCardAsGrid() method here, Hint: use getCardNumbers() when getting cards
             */
            printCardAsGrid(cards.get(x).getCardNumbers());
        }

    }

    /**
     * this is for option 4, list existing cards where all the cards are printed as a grid
     * of rows / columns, so numbers 3 4 5 6 will be printed as follows:
     * 3  4
     * 5  6
     * it is a follow on method from listCards() and ensures that the grid structure is printed
     */
    public void printCardAsGrid(String numbers) {

        System.out.printf("%s", numbers);

    }

    /**
     * use Toolkit.getInputForMessage to enter a new separator
     * print a message what the new separator is
     */
    public void setSeparator() {
        String sep = Toolkit.getInputForMessage("Enter the new separator");
        /**
         make use of setNumberSeparator() and getNumberSeparator()
         */
        Defaults.setNumberSeparator(sep);
        System.out.printf("Separator is '%s'%n", sep);
    }

    /**
     * reset all BingoCards using resetMarked (to false)
     */
    public void resetAllCards() {

        for (BingoCard card : cards) card.resetMarked();
    }

    /**
     * mark off a number that was called when it equals one of the numbers on the BingoCard
     */
    public void markNumbers(int number) {

        for (int x = 0; x < cards.size(); x++) {
            System.out.printf("Checking card %d for %d%n", x, number);
            cards.get(x).markNumber(number);
        }

    }

    /**
     * make use of isWinner() to determine who the winner is
     * the method should return the index of who the winner is
     */
    public int getWinnerId() {

        for (int x = 0; x < cards.size(); x++) {
            boolean haveWinner = cards.get(x).isWinner();
            if (haveWinner)
                return x;
        }
        return Defaults.NO_WINNER;
    }

    /**
     * please take note that the game will not end until there is a winning sequence
     */
    public void play() {
        System.out.println("Eyes down, look in!");
        resetAllCards();

        boolean weHaveAWinner;
        do {
            markNumbers(parseInt(
                    Toolkit.getInputForMessage("Enter the next number")
                            .trim()));

            int winnerID = getWinnerId();
            weHaveAWinner = winnerID != Defaults.NO_WINNER;
            if (weHaveAWinner)
                System.out.printf("And the winner is card %d%n", winnerID);
        } while (!weHaveAWinner);
    }

    public String getMenu(String[] menuItems) {
        /**
         change this method so it prints a proper numbered menu
         analyse the correct format from the ExpectedOutput files
         menuText is returned
         */
        StringBuilder menuText = new StringBuilder();

        for (int x = 0; x < menuItems.length; x++) {
            menuText.append(" ").append(x).append(": ").append(menuItems[x]).append("\n");
        }

        return menuText.toString();
    }

    /**
     * complete the menu using switch to call the appropriate method calls
     */
    public void run() {
        boolean finished = false;
        do {
            switch (Toolkit.getInputForMessage(getMenu(mainMenuItems)).trim()) {

                case OPTION_EXIT:
                    System.out.println(Toolkit.GOODBYEMESSAGE);
                    finished = true;
                    break;

                case OPTION_PLAY:
                    if (!cards.isEmpty())
                        play();
                    break;

                case OPTION_SEPARATOR:
                    setSeparator();
                    break;

                case OPTION_CREATE_CARD:
                    createCard();
                    break;

                case OPTION_LIST_CARDS:
                    listCards();
                    break;

                case OPTION_SIZE:
                    setSize();
                    break;
            }
        } while (!finished);
    }
}
