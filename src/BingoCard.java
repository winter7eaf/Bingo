import java.util.Arrays;

public class BingoCard {
    /*
      The two arrays are private and their structure is NEVER exposed to another
      class, which is why the getCardNumbers returns a String that needs
      further processing.

      While this is not computationally efficient, it is good programming
      practice to hide data structures (information hiding).
     */
    private int[][] numbers;
    private boolean[][] markedOff;

    private int numberOfRows;
    private int numberOfColumns;

    public BingoCard(int numberOfRows, int numberOfColumns) {
        setNumberOfRows(numberOfRows);
        setNumberOfColumns(numberOfColumns);

        numbers = new int[numberOfRows][numberOfColumns];
        markedOff = new boolean[numberOfRows][numberOfColumns];
        resetMarked();
    }

    public void resetMarked() {
        /**
         Reset the data structure to be entirely false. Java defaults booleans
         to false so you can make use of that.
         */
        markedOff = new boolean[numberOfRows][numberOfColumns];
    }

    /**
     * implement the getters and setters for rows / columns as seen below
     */
    public int getNumberOfRows() {
        /**
         change the return from 0 to the appropriate return
         */
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        /**
         change the return from 0 to the appropriate return
         */
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        /**
         implement code here
         */
        this.numberOfColumns = numberOfColumns;
    }

    public String getCardNumbers() {
        /**
         flatten the numbers array into a single string with each number separated by spaces but no leading or trailing copies of
         that character: that is no spaces before the first number nor after the last number.
         */

        StringBuilder sb = new StringBuilder();

        /**
         all the cards are stored as a grid ([][] numbers) of rows / columns, so for example, numbers 3 4 5 6 will be
         printed as follows:
         3  4
         5  6
         */

        for (int[] rows : numbers) {
            int x = 0;
            for (int column : rows) {
                if (column < 10)
                    sb.append(" ");
                sb.append(column);

                if (x != rows.length - 1) {
                    sb.append(Defaults.getNumberSeparator());
                    x++;
                }
            }
            sb.append("\n");
        }

        /**
         return the grid as a string
         */
        return sb.toString();
    }

    public void setCardNumbers(String[] numbersAsStrings) {
        /**
         the array of strings [] numbersAsStrings is cast to an integer as [] numbersList, for you
         set the grid from this list
         */
        int[] numbersList =
                Arrays.stream(numbersAsStrings).mapToInt(Integer::parseInt).toArray();

        /**
         the goal of this method is to get the numbers entered into the [][] numbers format
         */
        for (int y = 0; y < getNumberOfRows(); y++) {
            for (int z = 0; z < getNumberOfColumns(); z++) {
                numbers[y][z] = numbersList[(y * numberOfColumns) + z];
            }
        }
    }

    public void markNumber(int number) {
        /**
         make use of the [][] markedOff to mark off numbers from [][] numbers as they match
         if not matching an appropriate message must be printed, verify against expected output files
         */
        boolean containNum = false;
        for (int x = 0; x < getNumberOfRows(); x++) {
            for (int y = 0; y < getNumberOfColumns(); y++) {

                if (numbers[x][y] == number) {
                    markedOff[x][y] = true;
                    containNum = true;
                    break;
                }
            }
        }
        if (containNum)
            System.out.printf("Marked off %d\n", number);
        else System.out.printf("Number %d not on this card\n", number);


    }

    public boolean isWinner() {
        /**
         check if there is a winner or not
         all elements of [][] markedOff should be marked off to have a winner (full house)
         */
        //change return statement accordingly (either true or false)
        boolean haveWinner = true;
        for (int x = 0; x < getNumberOfRows(); x++) {
            for (int y = 0; y < getNumberOfColumns(); y++) {
                if (!markedOff[x][y]) {
                    haveWinner = false;
                    break;
                }
            }
        }
        return haveWinner;
    }
}