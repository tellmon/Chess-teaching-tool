import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * This contains everything needed for the board that is visible like the board and the arrows. 
 */
public class BoardHolder {
	
	/**
	 * Width of the screen
	 */
	private int width = 0;
	
	/**
	 * Height of the screen
	 */
	private int height = 0;
    
    /**
     * JPanel to store the board
     */
	private JPanel boardPanel;
	
    /**
     * The class of the arrowLogic where drawing arrows takes place
     */
	private ArrowLogic arrowLogic = new ArrowLogic();
	
	/**
	 * The menu for the TopMenu that has all the buttons for the top menu
	 */
	private TopMenu topMenu = new TopMenu(50); //text size
    
    /**
     * This is the class for the side parts of the board that has the letters and numbers on the side
     */
	private SidePartsOfBoard sidePartsOfBoard = new SidePartsOfBoard(50); //text size
    
    /**
     * This is the main board of the chess array where everything with the board is here.  
     */
	private PanelForButtonArray panelForButtonArray;
    
    /**
     * This just parses the frame to the board needed for the board to function
     * 
     * @param frame the JFrame
     */
    public BoardHolder(FramePanel frame) {
    		panelForButtonArray = new PanelForButtonArray(frame);
    }
    
    // flip board
    /**
     *This runs the flipNumbers and letters part of side part of board 
     */
    public void sidePartsOfBoard() {
    		sidePartsOfBoard.flipNumbersAndLetters();
    }
    
    
    //top Menu
    
    /**
     * @return the boolean value of the buttons trigger for  select file
     */
    public boolean checkSelectFile() {
    		return topMenu.checkSelectFile();
    }
    
    /**
     * @return the boolean value of the buttons trigger for save file 
     */
    public boolean saveFileChecker() {
    		return topMenu.checkSaveFileChecker();
    }
    
    /**
     * @return the boolean value of the buttons trigger for select Piece
     */
    public boolean getSelectedPiece() {
    		return topMenu.getSelectPiece();
    }
    
    /**
     * @return the boolean value of the buttons trigger for the switch sides of the board file 
     */
    public boolean checkSwitchSides() {
    		return topMenu.checkSwitchSides();
    }
    
    /**
     * Turns the boolean value of select file buttons trigger off.
     */
    public void selectFileOff() {
    		topMenu.selectFileOff();
    }
    
    /**
     *  Turns the boolean value of save file buttons trigger off.
     */
    public void turnOffSaveFileChecker() {
    		topMenu.turnOffSaveFileChecker();
    }
    
    /**
     *  Turns the boolean value of switch sides buttons trigger off.
     */
    public void switchSidesOff() {
    		topMenu.switchSidesOff();
    }
    
   /**
   *  Turns the boolean value of select pieces buttons trigger off. 
   */
    public void turnOffselectPiece() {
    		topMenu.turnOffSelectPiece();
    }
    
       		
    
    
    
    // button panel
    /**
     * Runs the method to flips the board from the board
     */
    public void flipBoard() {
    		panelForButtonArray.flipBoard();
    }
    
    /**
     * @param name the string you want to put onto the button that was last pressed
     */
    public void setLastButtonText(String name) {
    		panelForButtonArray.getLastButton().setText(name);
    }
    
    /**
     * @param buttonArray the chess board 2D array of char's you want to set as the new boardd
     */
    public void setButtonArray(char[][] buttonArray) {
    		panelForButtonArray.setButtonArray(buttonArray);
    }
    
    /**
     * @return the JPanel of the button array
     */
    public JPanel getButtonArrayJPanel() {
    		return panelForButtonArray.buttonArray();
    }
    
    /**
     * Runs the load board method for the chess board
     */
    public void loadBoard() {
    		panelForButtonArray.loadBoard();
    }
    
    /**
     * @return the board as a 2D char array
     */
    public char[][] getBoardState(){
    		return panelForButtonArray.getBoardState();
    }
    
    /**
     * @param setLetMoveTORF sets the value of let move for board based of this boolean value
     */
    public void setLetMove(Boolean setLetMoveTORF) {
    		panelForButtonArray.setLetMove(setLetMoveTORF);
    }
    
        
    
    // arrow stuff
    
    /**
     * @return the boolean value of if arrow mode is active or not
     */
    public boolean checkArrowMode() {        
    		return topMenu.checkArrowMode();
    }
       
    /**
     * Runs the method to get the x and y of the button using the last pressed button and the button array to find it
     */
    public void arrowDrawing() {
    		arrowLogic.getXAndYOfButtonInPixels(panelForButtonArray.getActionEvent(), panelForButtonArray.getButtonArray());
    }
    
    /**
     * Runs the erase function in arrowLogic to put it back to default when arrow mode is no longer active
     */
    public void erraseArrow() {
    		arrowLogic.eraseArrows();
    }
    
    
    // board
    
    /**
     * 
     * Gets the size of the screen and then creates the board that the user sees with there dimensions using layered panels of the arrow mode 
     * and every added onto the frame to create the Graphical User Interface 
     * 
     * @param board the JFrame where the board is going to be onto
     * @return the Frame with the board fully complete
     */
    public JFrame returnBoard(JFrame board) {
    	boardPanel = getButtonArrayJPanel();

        // get it to be the same size as the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
             
        // sets up the board
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(width, height);
        board.setVisible(true);
    	
        JLayeredPane layeredPane = new JLayeredPane();

        layeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(arrowLogic, JLayeredPane.DRAG_LAYER);
        arrowLogic.setOpaque(false);

        layeredPane.setBounds(0, 0, width, height);
        layeredPane.setPreferredSize(new Dimension(width, height));
        
        
        layeredPane.setSize(new Dimension(width, height));
                
        boardPanel.setSize(new Dimension(width,  (int) (height * 0.8))); 
        arrowLogic.setSize(new Dimension(width,  (int) (height * 0.8)));

        // adds it all to the board
        board.add(layeredPane, BorderLayout.CENTER);
        board.add(topMenu.topMenuMaker(), BorderLayout.NORTH);
        board.add(sidePartsOfBoard.numbers(), BorderLayout.WEST);
        board.add(sidePartsOfBoard.letter(), BorderLayout.SOUTH);
        
        return board;
    }
    
    /**
     * @return the JPanel of the board
     */
    public JPanel getBoardPanel() {
    		return boardPanel;
    }
}
