import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Panel for the button array which is the chess board in the centre panel
 */
public class PanelForButtonArray implements ActionListener{

	/**
	 * Stores the last piece selected
	 */
	private String piece = "";

	/**
	 * Active when a piece is picked up, shows when a piece is getting moved
	 */
	private boolean movePiece = true;
	
	/**
	 * Show when the user can move a piece on the board
	 */
	private boolean letMove = true;

	/**
	 * The chess array stored as a 2D array of chars
	 */
	private  char[][] chessBoardState = new char[8][8];
	
	/**
	 * The chess board stored as a 2D array of JButtons
	 */
	private JButton[][] buttonArray = new JButton[8][8];

	/**
	 * The class to validate moves made by the user
	 */
	private ValidMoveGetter validMoveGetter = new ValidMoveGetter();

	/**
	 * Stores the last button pressed by the user on the board
	 */
	private JButton lastButtonPressed = new JButton();

	/**
	 * The last action event that was course by the user
	 */
	private ActionEvent lastEvent;

	/**
	 * The timer so the action event can trigger not just by user action
	 */
	private Timer tick = new Timer(40, this);

	/**
	 * The old x position of the last piece moved
	 */
	private int oldx = 0;
	
	/**
	 * The old y position of the last piece moved
	 */
	private int oldy = 0;

	/**
	 * The old button colour of the last piece moved
	 */
	private Color oldButtonColour = new Color(100, 100, 250);
	
	/**
	 * The x and y position of the button that changed colour to display valid move
	 */
	private int colourX, colourY;

	/**
	 * The frame that is pasted down from FramePanel which holds the main board
	 */
    private FramePanel frame;

    /**
     * this starts the timer and then sets the this frame as the frame passed to it
     * 
     * @param frame the frame of the main board that the user see with all the buttons
     */
    public PanelForButtonArray(FramePanel frame) {
        tick.start();

        this.frame = frame;
    }

    /**
     * This creates the JPanel and all the buttons. then it goes and alternate the colour to put onto the board, makes its preferred size and adds to to the panel.
     * then runs loadBoard for the icons.
     *  
     * @return the panel of the buttons with the pieces on the JPanel
     */
    public JPanel buttonArray() {
        
        JPanel panel = new JPanel(new GridLayout(0, 8));

        boolean switchColours = false;
        
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
             
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y] = new JButton();
                
                buttonArray[x][y].setBorderPainted(false);
                buttonArray[x][y].setFocusable(false);
                
                buttonArray[x][y].setFont(new Font("Arial", Font.PLAIN, 80));
                                               
                buttonArray[x][y].addActionListener(this);

                buttonArray[x][y].setForeground(new Color(0, 0, 0, 0));

                if(switchColours){
                    buttonArray[x][y].setBackground(Color.gray);
                }

                else{
                    buttonArray[x][y].setBackground(new Color(100, 100, 250));
                }

                switchColours = !switchColours;
                
                buttonArray[x][y].setPreferredSize(new Dimension(64, 64));
                panel.add(buttonArray[x][y]);
            }
            switchColours = !switchColours;
        }

        loadBoard();
        
        return panel;
    }

    /**
     * Takes an actionEvent and stores the button it goes with in lastButtonPressed
     * 
     * @param e an action event 
     */
    private void lastButtonPresed(ActionEvent e){
        for (int x = 0; x < 8; x ++){
           for (int y = 0; y < 8; y ++){
                if (e.getSource() == buttonArray[x][y]){
                    lastButtonPressed = buttonArray[x][y];
                }
            }
        }
    }

    /**
     * Sets the icons of the pictures onto the board based off what pieces are on the at the square
     */
    private void setIcons(){

        String text = "";

        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){

                text = buttonArray[x][y].getText();

                // run a swich case for this. 

                switch(text){

                    // white
                    case "P":
                        buttonArray[x][y].setIcon(new ImageIcon("whitePawn.png"));
                    break;

                    case "R":
                        buttonArray[x][y].setIcon(new ImageIcon("whiteRook.png"));
                    break;

                    case "N":
                        buttonArray[x][y].setIcon(new ImageIcon("whiteKnight.png"));
                    break;

                    case "B":
                        buttonArray[x][y].setIcon(new ImageIcon("whiteBishop.png"));
                    break;

                    case "Q":
                        buttonArray[x][y].setIcon(new ImageIcon("whiteQueen.png"));
                    break;

                    case "K":
                        buttonArray[x][y].setIcon(new ImageIcon("whiteKing.png"));
                    break;


                    // black 

                    case "p":
                        buttonArray[x][y].setIcon(new ImageIcon("blackPawn.png"));
                    break;

                    case "r":
                        buttonArray[x][y].setIcon(new ImageIcon("blackRook.png"));
                    break;

                    case "n":
                        buttonArray[x][y].setIcon(new ImageIcon("blackKnight.png"));
                    break;

                    case "b":
                        buttonArray[x][y].setIcon(new ImageIcon("blackBishop.png"));
                    break;

                    case "q":
                        buttonArray[x][y].setIcon(new ImageIcon("blackQueen.png"));
                    break;

                    case "k":
                        buttonArray[x][y].setIcon(new ImageIcon("blackKing.png"));
                    break;

                    
                }
            
            }
        }
    }

    /**
     * Does the move logic for the pieces
     * 
     * logic:
     * If let move is active then it will take the square selected and see if a move is already in play if not then it will take what piece is on the board and then take its x and y position as well as its colour
     * If a move is currently in progress then all the piece is set as the new text and the old and new place as well as the piece is used in validMoveGetter to see if that move was valid.
     * 
     * @param e the action event
     */
    private void movePieces(ActionEvent e){
        if(letMove){
            for (int x = 0; x < 8; x ++){
                for (int y = 0; y < 8; y ++){
                    if (e.getSource() == buttonArray[x][y] && buttonArray[x][y].getText().charAt(0) != 0){
                    	                  	
                    	                    	
                        //check if its a piece then pick it up if its not then don't but ever way let it place. 
                        if (movePiece && !buttonArray[x][y].getText().matches("") && !buttonArray[x][y].getText().matches(" ")){
                            piece = buttonArray[x][y].getText();
                            buttonArray[x][y].setIcon(null);

                            oldx = x;
                            oldy = y;

                            movePiece = false;
                            buttonArray[x][y].setText(" ");

                            buttonArray[colourX][colourY].setBackground(oldButtonColour);
                        }
                        
                        else if(!movePiece){

                            oldButtonColour = buttonArray[x][y].getBackground();
                            colourX = x;
                            colourY = y;

                            if(validMoveGetter.checkIfValidMove(piece, oldx, oldy, x, y)){
                                buttonArray[x][y].setBackground(Color.green);
                            }

                            else{
                                buttonArray[x][y].setBackground(Color.red);
                            }

                            buttonArray[x][y].setText(piece);
                            movePiece = true;
                        }
                        
                    }
                }
            }
        }
    }

    /**
     * Takes the board and flips it around so you can see if from the other sides perspective
     */
    public void flipBoard(){

        JButton[][] flipedButtonArray = new JButton[8][8];

        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y].setIcon(null);
            }
        }


        // fliping in x and y axis
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                flipedButtonArray[x][y] = new JButton();  
            }
        }

        //flipping axis
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                flipedButtonArray[-y + 7][-(x-7)].setText(buttonArray[y][x].getText());
            }
        }

        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y].setText(flipedButtonArray[x][y].getText());
            }
        }

        resetColourOfButton();
        
        /*   
        * logic:
        * 
        * so we need to flip the board in the black and white center lines.
        * so y = x
        * and
        * y = -(x-7) # this is using the first square a 0,0 not 1,1 to as we are doing arrays
        * 
        * to get the x its -y + 7 = x
        * and for the y its y = -(x-7) 
        * we would need this in a sepreat list so we can switch between them, 
        * 
        * Switch between the two list like this:
        * temp = x
        * x = y
        * y = temp
        * 
        */
    }

    /**
     * The actionPerformed that is the triggers when a action is taken by the user
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        lastEvent = e;
        frame.setPosisitonsForArrows();
        movePieces(e);
        lastButtonPresed(e);
        setIcons();
    }

    /**
     * Sets all the boards icons to null and then sets the text of the boards buttons as whats stored at chessBoardStates in the same part of the array
     */
    public void loadBoard(){
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y].setIcon(null);
                buttonArray[x][y].setText(""+chessBoardState[y][x]);
            }
        }
        resetColourOfButton();
    }

    /**
     * Sets the value of letMove from the passed variable
     * @param letMove the value to set letMove
     */
    public void setLetMove(boolean letMove){
        this.letMove = letMove;
    }

    /**
     * Returns the last event Triggered
     * @return an ActionEvent of the last event triggered
     */
    public ActionEvent getActionEvent(){
        return lastEvent;
    }    

    /**
     * Sets the chessBoardState as the value inputed
     * @param piecesCharArray the chess array as a 2D char
     */
    public void setButtonArray(char[][] piecesCharArray){
        chessBoardState = piecesCharArray;
    }

    /**
     * Returns the buttonArray
     * @return the button array as a 2D array of JButtons
     */
    public JButton[][] getButtonArray(){
        return buttonArray;
    }

    /**
     * Gets the last button pressed
     * @return the last button pressed as a JButton
     */
    public JButton getLastButton(){
        return lastButtonPressed;
    }

    /**
     * Gets the button array and takes all the texts and puts it to the chessBoardState
     * @return chessBoardState that is returned as the 2D array
     */
    public char[][] getBoardState(){
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                chessBoardState[y][x] = buttonArray[x][y].getText().charAt(0);
            }
        }

        return chessBoardState;
    }

    /**
     * Sets the last button pressed colour as the last colour that was changed
     */
    private void resetColourOfButton(){
        lastButtonPressed.setBackground(oldButtonColour);
    }
}