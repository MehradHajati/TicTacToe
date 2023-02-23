package tictactoe;

/**
 * This class will hold all the information about the game and does the logic side of the game
 * @author Mehrad Hajati
 */
public class ModelManager {
    
    private final int EMPTY;
    private final int CIRCLE;
    private final int CROSS;
    private final TicTacToe controller;
    private int[][] board;
    private int turn; // this will keep track of the turn
    
    
    
    public ModelManager(TicTacToe con){
        this.controller = con;
        EMPTY = controller.EMPTY;
        CIRCLE = controller.CIRCLE; 
        CROSS = controller.CROSS;
        board = new int[3][3];
        turn = CROSS;
        fillBoard();
    }
    
    
    /**
     * Method to check if a position is empty
     * @param row row of the position in question
     * @param col column of the position in question
     * @return true if empty and false otherwise
     */
    private boolean emptySpot(int row, int col){ return board[row][col] == EMPTY; }
    
    
    /**
     * Method to make sure the input correctly responds to one of circle or cross
     * @param num input
     * @return true if it is one of cross or circle and false otherwise
     */
    public boolean checkInput(int num){ return num == CIRCLE || num == CROSS; }
    
    
    /**
     * Method to fill the board with 0 at the beginning of game
     */
    private void fillBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = EMPTY;
            }
        }
    }
    
    
    /**
     * Method to fill spot in the board with either a cross or a circle
     * @param num this number represents either a cross or circle and it is what will will the spot
     * @param row row of the spot in question
     * @param col column of the spot in question
     * @return 
     */
    public boolean fillSpot(int num, int row, int col){
        if(emptySpot(row, col)){
           if(checkInput(num)){
               board[row][col] = num;
               controller.incrementMoveNum();
               int moveNum = controller.getMoveNum();
               if(moveNum > 4){
                   int winner = checkBoard();
                   if(winner != EMPTY){
                       if(winner == CIRCLE){ controller.won(CIRCLE); }
                       else { controller.won(CROSS); }
                      
                   }
                   if(winner == EMPTY && moveNum == 9){
                       controller.tie();
                   }
               }
               incrementTurn();
               return true;
           } 
           else{ return false; } // will return false if the input is wrong
        }
        else{ return false; }// will return false if the spot is already filled
    }
    
    /**
     * Method to check if game is over by checking the rows and columns and diagonals for three in a row
     * @return if someone has one will return the number that represents that player or will return EMPTY value
     */
    public int checkBoard(){
        // Check the rows
        for(int i = 0; i < 3; i++){
            if( (board[i][0]==board[i][1]) && (board[i][1]==board[i][2]) && (board[i][0] != EMPTY)){
                return board[i][0];
            }
        }
        // Check the columns
        for(int j = 0; j < 3; j++){
            if( (board[0][j]==board[1][j]) && (board[1][j]==board[2][j]) && (board[0][j] != EMPTY)){
                return board[0][j];
            }
        }
        // Check the diagonals
        if( (board[0][0]==board[1][1]) && (board[1][1]==board[2][2]) && (board[0][0] != EMPTY)){
            return board[0][0];
        }
        if( (board[2][0]==board[1][1]) && (board[1][1]==board[0][2]) && (board[1][1] != EMPTY)){
            return board[1][1];
        }
        // returning empty if no one has won yet
        return EMPTY;
    }
 
    public void incrementTurn(){
        if(turn == CROSS){ turn = CIRCLE; }
        else { turn = CROSS; }
    }
    
    public int getTurn(){ return turn; }
   
}
