import java.util.Scanner;   // Import the Scanner class
import java.util.Random;

public class Main
{
    private static Scanner userInput = new Scanner(System.in);     // Create a Scanner obj
    private static Board boardObj;
    private static String userRow;
    private static String userCol;

    //*********************************************************
    //
    //  Welcome message, choose marking
    //  and init variables/attributes
    //
    //*********************************************************
    private static void startGame()
    {
        String humanMark = "";
        String aiMark = "";

        System.out.println("\n\t       Welcome");
        System.out.println("\t______________________");
        Board showBoard = new Board(); // This obj is only used to display the initial board (Purpose - "Eye Candy") - Thats it. It's NOT used anywhere else
        showBoard.displayBoard();

        System.out.println("Choose your mark: X  or  O");
        
        // Validating user input
        while(true){     
            System.out.print("Choice: ");
            humanMark = userInput.nextLine();

            if ( ! humanMark.matches("[XOxo]{1}") )
                System.out.println("Incorrect Input! - Symbol Must Be Either X  or  O");
            else
                break;
        }

        // Finialize setting humanMark and set aiMark 
        humanMark = humanMark.toUpperCase();
        if (humanMark.equals("X"))
            aiMark = "O";
        else
            aiMark = "X";
        
        // Init userRow, userCol and the boardObj (which will be used for the remainder of the program)
        boardObj = new Board(humanMark.charAt(0), aiMark.charAt(0));
        userRow = "";
        userCol = "";
    }

    //*********************************************************
    //
    //  Flip a coin to decide who goes 1st
    //
    //*********************************************************
    private static int flipCoin()
    {
        System.out.println("Flipping a coin to decide who goes 1st");
        System.out.print("Going 1st -->  ");
        
        // Create 3 sec time delay (Purpose - "Create Slight Suspense")
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        
        Random rand = new Random();
        int whosTurn = 0;
        if (rand.nextInt(2) == 1){  // Generates a random int in range 0 to 1
            whosTurn = 1;   // Human's turn
            System.out.println("Human");
        }
        else{
            whosTurn = 2;   // AI's turn
            System.out.println("AI");
        }
        
        System.out.println("----------------------");
        // Create 2 sec time delay (Purpose - "Give user time to read whats going on")
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return whosTurn;
    }

    //*********************************************************
    //
    //  Get & validate user Row & Col input
    //  ensure the inputted tile is playable
    //  i.e. EMPTY
    //
    //*********************************************************
    private static void getUserInput()
    {
        System.out.println("\nHuman's Turn");
        while (true){

            // Validating user Row input
            while(true){
                System.out.print("Enter Row: ");
                userRow = userInput.nextLine();

                if ( ! userRow.matches("[A-Ca-c]{1}") )
                    System.out.println("Incorrect Input! - Input Must Be A Valid Row I.e. A, B or C");
                else
                    break;
            }
            
            // Validating user Column input
            while(true){
                System.out.print("Enter Column: ");
                userCol = userInput.nextLine();

                if ( ! userCol.matches("[1-3]{1}") )
                    System.out.println("Incorrect Input! - Input Must Be A Valid Column I.e. 1, 2 or 3");
                else
                    break;
            }   

            // Checking if the user inputed Row & Col are EMPTY i.e. not already taken
            if (boardObj.checkTileAvailable(userRow, userCol))
                break;
            else
                System.out.println("Incorrect Input! - Tile is NOT available");
        }
    }

    
        

    public static void main(String[] args)
    {
        startGame();
        int whosTurn = flipCoin();
      
        // Game loop - will end when Human or AI wins or a Tie occurs
        while (true){
    
            if (whosTurn == 1)  // if Human's turn --> getUserInput()
                getUserInput();

            whosTurn = boardObj.move(whosTurn);
            boardObj.displayBoard();

            // Create 2 sec time delay (Purpose - "Give user time to see the current state of the board")
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            
            if ( boardObj.checkIfGameWon() ) 
                break;
            else if ( boardObj.areAllTilesFilled() ){
                whosTurn = -1;
                break;
            }
        }

        // if the last player who made the turn == 1 --> Human Won else AI Won
        if (whosTurn - 1 == 1)
            System.out.println("Human Won!");
        else if (whosTurn - 1 == 0)
            System.out.println("AI Won!");
        else
            System.out.println("It's a Tie!");
    }
}