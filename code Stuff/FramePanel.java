import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is the main part of the program. This is the connector between most of the classes and sets everything up like the board and the top menu
 */
public class FramePanel extends JFrame implements ActionListener{

	/**
	 * the board as its BNF format 
	 */
    private String dataOfBoardInFEN;
    
    /**
     * the file to save the boards name. 
     */
    private  String fileNameToSaveAs;

    /**
     * the frame which holds the main parts of the chess program with the board and menus.
     */
    private JFrame board = new JFrame("Chess Board");
    
    /**
     * The class selectPiece used in topMenu
     */
    private SelectPiece selectPiece = new SelectPiece();
    
    /**
     * The class fileHandler that handles the file part of the program
     */
    private FileHandler fileHandler = new FileHandler();
    
    /**
     * The class of selectFile that is used with JChoser that lets the user select the file. 
     */
    private SelectFile selectFile = new SelectFile();
    
    /**
     * The class of saveBoardState that is used with JChoser that lets the user save the board. 
     */
    private SaveBoardState saveBoardState = new SaveBoardState();
    
    /**
     * The class boardHolder  that is used to deal with the main board
     */
    private BoardHolder boardHolder = new BoardHolder(this);       
    
    /**
     * The tick used in this class to check everything to make sure it runs smoothly.
     */
    private Timer tick = new Timer(40, this);

    /**
     * The 2D char array to store the chess board.
     */
    private char[][] buttonArray;

    /**
     * The JPanel that the main board is on.
     */
    private JPanel boardPanel;

    /**
     * Loads the blank board from the file and runs setPanelUP
     */
    public FramePanel(){

        String name = "Blank.txt";
        
        fileHandler.readFile(name);
    	
        this.buttonArray = fileHandler.returnChessArray();
        tick.start();

        SetPanelUp();
    }

    /**
     * Uses board holder with the button array to set up the button array for chess and then sets it onto the panel
     */
    private void SetPanelUp(){
    	boardHolder.setButtonArray(buttonArray);
                
    board = boardHolder.returnBoard(board);
    boardPanel = boardHolder.getBoardPanel();   
   }

    /**
     * Checks if arrow mode is on. If so then it will not let pieces to move else it will
     * If true it will also run the boardHolders arrowDrawing to draw the arrow.
     */
    public void setPosisitonsForArrows(){ 
        boolean arrowMode = boardHolder.checkArrowMode();
        
        if(arrowMode){
        	boardHolder.setLetMove(false);
        	boardHolder.arrowDrawing();
        }

        else{
        	boardHolder.erraseArrow();
            boardHolder.setLetMove(true);
        }

    }

    /**
     * This will take the action event and check to see what happens and then does what it needs to do for each event. 
     * This contains the logic for the selectPieces, switchSides and file checking loading & saving. 
     * After checking all of the stuff then it will repaint the board
     * 
     * @param e the action event
     */
    private void actionLogic(ActionEvent e){

        if(boardHolder.getSelectedPiece()){

        		boardHolder.setLastButtonText(selectPiece.getName());
           
            if(!selectPiece.getSetUp()){
               selectPiece.setUpPanel();        
            }

            if (selectPiece.getDone()){
            		boardHolder.turnOffselectPiece();
                selectPiece.hidePanel();
            }

            else{
                selectPiece.showPanel();
            }
        }

        if(boardHolder.checkSwitchSides()){
	        	boardHolder.flipBoard();
	        	boardHolder.sidePartsOfBoard();
	        	boardHolder.switchSidesOff();
        }

       if(boardHolder.saveFileChecker()){
        	
	    	   if(!saveBoardState.isSetupDone()){
	               saveBoardState.setUpForInput();
	           }
	
	           if (saveBoardState.isSubmitted()) {
	               dataOfBoardInFEN = saveBoardState.convertToString(boardHolder.getBoardState());
	               fileNameToSaveAs = saveBoardState.getName();
	               fileHandler.dataToSave(fileNameToSaveAs, dataOfBoardInFEN);
	
	               boardHolder.turnOffSaveFileChecker();
	               saveBoardState.reset();
	           }
	
	           if(saveBoardState.isClosed()){
	        	   	   boardHolder.turnOffSaveFileChecker();
	               saveBoardState.reset();
	           }
       }

       
       if(boardHolder.checkSelectFile()){
            
	    	   if(!selectFile.isSetUpDone()){
	    	       selectFile.setUpForInput();
	    	   }
	
	    	   if(selectFile.isSubmitted()){
	    		   fileHandler.readFile(selectFile.getText());
	    		   fileHandler.turnDataToArray();
	    	    	
	    	    	
	    		   boardHolder.setButtonArray(fileHandler.returnChessArray());
	    		   boardHolder.loadBoard();
	
	    		   boardHolder.selectFileOff();
	    	       selectFile.reset();
	    	    }
	
	    	    if(selectFile.isClosed()){
	    	    		boardHolder.selectFileOff();
	    	        selectFile.reset();
	    	    }
       }

       boardPanel.repaint();
    }

    /**
     * Runs actionLogic with the action event that triggered it
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       actionLogic(e);
    }
}