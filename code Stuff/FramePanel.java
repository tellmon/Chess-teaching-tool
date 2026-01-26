import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FramePanel extends JFrame implements ActionListener{

    String dataOfBoardInNFS;
    String fileNameToSaveAs;

    JFrame board = new JFrame("Chess Board");
    
    SelectPiece selectPiece = new SelectPiece();
    FileHandler fileHandler = new FileHandler();
    SelectFile selectFile = new SelectFile();
    SaveBoardState saveBoardState = new SaveBoardState();
    BoardHolder boardHolder = new BoardHolder(this);       
    
    Timer tick = new Timer(40, this);

    char[][] buttonArray;

    JPanel boardPanel;

    public FramePanel(){

        String name = "Blank.txt";
        
        fileHandler.readFile(name);
    	
        this.buttonArray = fileHandler.returnChessArray();
        tick.start();

        SetPanelUp();
    }

    public void SetPanelUp(){
    	boardHolder.setButtonArray(buttonArray);
                
        board = boardHolder.returnBoard(board);
        boardPanel = boardHolder.getBoardPanel();   
   }

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

    private void actionLogic(ActionEvent e){

        if(boardHolder.getSelectedPiece()){

        	boardHolder.getLastButtonAsText(selectPiece.getName());
           
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
               dataOfBoardInNFS = saveBoardState.convertToString(boardHolder.getBoardState());
               fileNameToSaveAs = saveBoardState.getName();
               fileHandler.dataToSave(fileNameToSaveAs, dataOfBoardInNFS);

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

    @Override
    public void actionPerformed(ActionEvent e) {
       actionLogic(e);
    }
}