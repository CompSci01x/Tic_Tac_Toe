import java.util.Random;

public class Board
{
    
    //********************************
    // Attributes
    //********************************
    private char[][] board;
    private final char EMPTY = ' ';
    private int rowNum;
    private int colNum;
    private char humanMark;
    private char aiMark;

    
    //********************************
    // Default Constructor
    //********************************
    public Board()
    {
        rowNum = 0;
        colNum = 0;
        this.humanMark = ' ';
        this.aiMark = ' ';

        board = new char[3][3];
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                board[x][y] = EMPTY;
    }

    //********************************
    // Parameterized Constructor
    //********************************
    public Board(char humanMark, char aiMark)
    {
        rowNum = 0;
        colNum = 0;
        this.humanMark = humanMark;
        this.aiMark = aiMark;

        board = new char[3][3];
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                board[x][y] = EMPTY;
    }

    

    //------------------------------------------------------------------------------------------------
    //                                     Public Methods
    //------------------------------------------------------------------------------------------------

    //*********************************************************
    //
    //  Display / Print the current tic-tac-toe board
    //
    //*********************************************************
    public void displayBoard()
    {
        System.out.println();

        System.out.print("\t     1   2   3\n");
        System.out.print("\t   ╔═══╦═══╦═══╗");
        System.out.println();

        char[] rowLetter = {'A','B','C'};
        int rowIdx = 0;
        for (int x = 0; x < 5; x++){
            if (x % 2 == 1)
                System.out.println("\t   ╠═══╬═══╬═══╣");
            else{
                System.out.print("\t" + (rowLetter[rowIdx]) + "  ║");
                
                for (int j = 0; j < 3; j++)
                    System.out.print(" " + board[rowIdx][j] + " ║");

                System.out.println(); 
                rowIdx++;
            }           
        }

        System.out.println("\t   ╚═══╩═══╩═══╝");
    }


    //*********************************************************
    //
    //  Check if tile inputted by the user via Row & Col
    //      is playable i.e. EMPTY
    //  This method is called in Main.java -> in getUserInput() 
    //
    //*********************************************************
    public boolean checkTileAvailable(String userRow, String userCol)
    {
        convertUserInputStringsToInts(userRow, userCol);
        
        if (board[rowNum][colNum] == EMPTY)
            return true;
        else
            return false;
    }


    //*********************************************************
    //
    //  Move(whosTurn) whos turn represents whos moving
    //      Human or AI
    //  whosTurn == 1 --> Human
    //  whosTurn == 2 --> AI
    //
    //*********************************************************
    public int move(int whosTurn)
    {
        if ( whosTurn == 1 ){
            board[rowNum][colNum] = humanMark;
            whosTurn = 2;   // Human's turn ended -> back to AI
        }
        else if ( whosTurn == 2 ){
            System.out.print("\nAI's Turn");

            if(board[1][1] == EMPTY)
                board[1][1] = aiMark;
            else{
                Random rand = new Random();
                
                while(true){
                    int aiRow = rand.nextInt(3); // generate random int in range 0 to 2
                    int aiCol = rand.nextInt(3); // generate random int in range 0 to 2
                
                    if (board[aiRow][aiCol] == EMPTY){
                        board[aiRow][aiCol] = aiMark;
                        break;
                    }
                }
            }
            whosTurn = 1;   // AI's turn ended -> back to Human
        }

        return whosTurn;
    }

    //*********************************************************
    //
    //  Check if there is a 3 in a row present
    //      if so returns true -> game over, win
    //      else returns false -> game continues
    //
    //*********************************************************
    public boolean checkIfGameWon()
    {
        boolean isGameWon = true;
        char symbol = ' ';

        // Check from upper left corner - right, diagonal, down
        if (board[0][0] == humanMark)
            symbol = humanMark;
        else
            symbol = aiMark;
        
        // check top row from left to right
        for (int y = 0; y < 3; y++)
            if (board[0][y] != symbol){
                isGameWon = false;
                break;
            }
        if (isGameWon)
            return true;
        else
            isGameWon = true;
                

        // check leftmost column top - down
        for (int x = 0; x < 3; x++)
            if (board[x][0] != symbol){
                isGameWon = false;
                break;
            }
        if (isGameWon)
            return true;
        else
            isGameWon = true;
        
        // check diagonal - from upper left corner to lower right corner
        if ( board[1][1] == symbol && board[1][1] == symbol && board[2][2] == symbol )
            return true;
        



        // check leftmost column center - right
        if (board[1][0] == humanMark)
            symbol = humanMark;
        else
            symbol = aiMark;
        
        // check leftmost column center rown from left to right
        for (int y = 0; y < 3; y++)
            if (board[1][y] != symbol){
                isGameWon = false;
                break;
            }
        if (isGameWon)
            return true;
        else
            isGameWon = true;

        
        // check lowerleft corner - right, diagonal
        if (board[2][0] == humanMark)
            symbol = humanMark;
        else
            symbol = aiMark;

        // check lowerleft corner from left to right
        for (int y = 0; y < 3; y++)
            if (board[2][y] != symbol){
                isGameWon = false;
                break;
            }
        if (isGameWon)
            return true;
        else
            isGameWon = true;

         // check diagonal - from lowerleft corner to upper right corner
        if ( board[2][0] == symbol && board[1][1] == symbol && board[0][2] == symbol )
            return true;




        // check top row center - down
        if (board[0][1] == humanMark)
            symbol = humanMark;
        else
            symbol = aiMark;

         // check top row from center - down
        for (int x = 0; x < 3; x++)
            if (board[x][1] != symbol){
                isGameWon = false;
                break;
            }
        if (isGameWon)
            return true;
        else
            isGameWon = true;



        // check top right corner from top - down
        if (board[0][2] == humanMark)
            symbol = humanMark;
        else
            symbol = aiMark;

        // check top right corner from top - down
        for (int x = 0; x < 3; x++)
            if (board[x][2] != symbol){
                isGameWon = false;
                break;
            }
        if (isGameWon)
            return true;
        
        return false;
    }

    //*********************************************************
    //
    //  Check if there are any EMPTY tiles left
    //      if so returns false -> game continues
    //      else returns true -> game over, tie
    //
    //*********************************************************
    public boolean areAllTilesFilled()
    {
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                if (board[x][y] == EMPTY)
                    return false;
        
        return true;
    }



    //------------------------------------------------------------------------------------------------
    //                                     Private Methods
    //------------------------------------------------------------------------------------------------



    //*********************************************************
    //
    //  Converts the user inputed Row & Col -
    //      which are stored as strings to ints
    //  This method is called in this class
    //      Board.java -> in checkTileAvailable()
    //
    //*********************************************************
    private void convertUserInputStringsToInts(String userRow, String userCol)
    {
        switch(userRow.toUpperCase()){
            case "A":
                rowNum = 0;
                break;
            case "B":
                rowNum = 1;
                break;
            case "C":
                rowNum = 2;
        }

        switch(Integer.parseInt(userCol)){
            case 1:
                colNum = 0;
                break;
            case 2:
                colNum = 1;
                break;
            case 3:
                colNum = 2;
        }
    }



}