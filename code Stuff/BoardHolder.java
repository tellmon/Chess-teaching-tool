import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class BoardHolder {
	
	int width = 0;
    int height = 0;
    JPanel boardPanel;
	
	ArrowLogic arrowLogic = new ArrowLogic();
    TopMenu topMenu = new TopMenu(50); //text size
    SidePartsOfBoard sidePartsOfBoard = new SidePartsOfBoard(50); //text size
    PanelForButtonArray panelForButtonArray;
    
    public BoardHolder(FramePanel frame) {
    	panelForButtonArray = new PanelForButtonArray(frame);
    }
    
    //side menu
    public void sidePartsOfBoard() {
    	sidePartsOfBoard.flipNumbersAndLetters();
    }
    
    
    //top Menu
    
    public boolean checkSelectFile() {
    	return topMenu.checkSelectFile();
    }
    
    public boolean saveFileChecker() {
    	return topMenu.saveFileChecker;
    }
    
    public boolean getSelectedPiece() {
    	return topMenu.getSelectPiece;
    }
    
    public boolean checkSwitchSides() {
    	return topMenu.checkSwitchSides();
    }
    
    public void selectFileOff() {
    	topMenu.selectFileOff();
    }
    
    public void turnOffSaveFileChecker() {
    	topMenu.turnOffSaveFileChecker();
    }
    
    public void switchSidesOff() {
    	topMenu.switchSidesOff();
    }
    
    public void turnOffselectPiece() {
    	topMenu.turnOffSelectPiece();
    }
    
       		
    
    
    
    // button panel
    public void flipBoard() {
    	panelForButtonArray.flipBoard();
    }
    
    public void getLastButtonAsText(String name) {
    	panelForButtonArray.getLastButton().setText(name);
    }
    
    public void setButtonArray(char[][] buttonArray) {
    	panelForButtonArray.setButtonArray(buttonArray);
    }
    
    public JPanel getButtonArrayJPanel() {
    	return panelForButtonArray.buttonArray();
    }
    
    public void loadBoard() {
    	panelForButtonArray.loadBoard();
    }
    
    public char[][] getBoardState(){
    	return panelForButtonArray.getBoardState();
    }
    
    public void setLetMove(Boolean setLetMoveTORF) {
    	panelForButtonArray.setLetMove(setLetMoveTORF);
    }
    
        
    
    // arrow stuff
    public boolean checkArrowMode() {        
    	return topMenu.checkArrowMode();
    }
       
    public void arrowDrawing() {
    	arrowLogic.getXAndYOfButtonInPixels(panelForButtonArray.getActionEvent(), panelForButtonArray.getButtonArray());
    }
    
    public void erraseArrow() {
    	arrowLogic.eraseArrows();
    }
    
    
    // board
    
    
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
    
    public JPanel getBoardPanel() {
    	return boardPanel;
    }
}
