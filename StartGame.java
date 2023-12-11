// call the Puzzle game with the correct amount of rows



/**
 * StartGame
 */

public class StartGame {

    String filePath;
    
    public StartGame(String filePath, int ROWS, int COLS) 
    {
        this.filePath = filePath;
        PuzzleGame game = new PuzzleGame(filePath, ROWS, COLS);
        System.out.println(filePath);
    }
}