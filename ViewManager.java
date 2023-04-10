package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Mehrad Hajati
 */
public class ViewManager implements ActionListener{
    
    private final TicTacToe controller;
    private final int CIRCLE;
    private final int CROSS;
    private JFrame frame;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JLabel label;
    private JButton[][] buttons;
    
    public ViewManager(TicTacToe con){
        controller = con;
        CIRCLE = controller.CIRCLE; 
        CROSS = controller.CROSS;
        createObjects();
    }

    /**
     * This method creates all the objects needed for the GUI
     */
    private void createObjects() {
        
        // Creating the initial objects
        frame = new JFrame();
        textPanel = new JPanel();
        buttonPanel = new JPanel();
        label = new JLabel();
        buttons = new JButton[3][3];
        
        // giving the frame some specifications and making it visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setTitle("Tic Tac Toe");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        
        // Making the label and giving it some specifications
        label.setBackground(new Color(120, 20, 124));
        label.setForeground(new Color(25, 255, 0));
        label.setFont(new Font("Ink Free", Font.BOLD, 75));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setText("Tic Tac Toe");
        label.setOpaque(true);
        
        
        textPanel.setLayout(new BorderLayout());
        textPanel.setBounds(0, 0, 800, 100);
        buttonPanel.setLayout(new GridLayout(3, 3));// setting layout of bt_pannel as gridlayout 
        buttonPanel.setBackground(new Color(150, 150, 150));
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                buttons[i][j] = new JButton();// creating each button object in the 2d array
                buttonPanel.add(buttons[i][j]); // adding each button to the buttonPanel
                buttons[i][j].setFont(new Font("Ink Free", Font.BOLD, 120));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
            
        }
        
        // adding the label to the text panel and then adding the textpanel and button panel to the frame
        textPanel.add(label);
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(buttonPanel);
        
    }
      
    /**
     * Method to update the turn on the text panel
     */
    private void updateTurnText(){
        int turn = controller.getTurn();
        if(turn == CIRCLE){
            label.setText("O Turn");
        }
        else { label.setText("X Turn"); }
    }
        
    
    /**
     * This method will handle what happens after a button gets pressed
     * @param e the button pressed
     */
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                if(e.getSource() == buttons[i][j]){
                    int turn = controller.getTurn();
                    if(controller.fillSpot(controller.getTurn(), i, j)){
                        if(turn == CIRCLE){
                            buttons[i][j].setForeground(new Color(255, 0, 0));
                            buttons[i][j].setText("O");
                            updateTurnText();
                        }
                        else{ // here it is X's turn
                            buttons[i][j].setForeground(new Color(255, 0, 0));
                            buttons[i][j].setText("X");
                            updateTurnText();
                        }
                        controller.checkBoard();
                    }
                    else{ // here the button pressed is not empty
                        JOptionPane.showMessageDialog(frame,"That spot is already filled","Full Spot",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }      
     
    
    /**
     * Method to display who won the game
     * @param num the player who won
     */
    public void won(int num){
        if(num == CIRCLE){
            JOptionPane.showMessageDialog(frame,"Circle won the game","Winner Winner Chicken Dinner",JOptionPane.INFORMATION_MESSAGE);
        }
        else{ 
            JOptionPane.showMessageDialog(frame,"Cross won the game","Winner Winner Chicken Dinner",JOptionPane.INFORMATION_MESSAGE);
        }
        resetGame();
    }
    
    
    /**
     * Method to display the game is a tie
     */
    public void tie(){
        JOptionPane.showMessageDialog(frame,"The game is a tie","Tie",JOptionPane.INFORMATION_MESSAGE);
        resetGame();
    }

    
    /**
     * Method to reset the game
     */
    public void resetGame(){
        deleteObjects();
        createObjects();
        controller.resetGame();
    }

    /**
     * Method to delete objects when creating a new game
     */
    public void deleteObjects(){
        frame.dispose();
    }

}
