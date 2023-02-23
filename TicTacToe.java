package tictactoe;

import javax.swing.JOptionPane;

/**
 *
 * @author Mehrad Hajati
 */
public class TicTacToe {

    // Made these three public so that view and model can see them and take example from them
    public final int EMPTY = 0;
    public final int CROSS = 1;
    public final int CIRCLE = 2;
    private int moveNum = 0;
    private int won = 0;
    private ModelManager model;
    private ViewManager view;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
    }
    
    public TicTacToe(){
        model = new ModelManager(this);
        view = new ViewManager(this);
    }
    
    //Getter Methods
    public int getMoveNum(){ return moveNum; }
    public int getEmpty(){ return EMPTY; }
    public int getCircle(){ return CIRCLE; }
    public int getCross(){ return CROSS; }
    public int getTurn(){ return model.getTurn(); }
    
    public void incrementMoveNum(){ moveNum++; }
    
    
    public void won(int num){ view.won(num); }
    
    public void tie(){ view.tie(); }
    
    
    // Wrapper Methods for ModelManager
    public boolean fillSpot(int num, int row, int col){ return model.fillSpot(num, row, col); }
    
}
