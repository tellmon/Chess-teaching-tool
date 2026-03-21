import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * this is the top menu contains all the buttons needed for the options for the chess board
 */
public class TopMenu implements ActionListener{

	/**
	 * The button for selectFile
	 */
	private JButton selectFile = new JButton();
    
    /**
     * The button for selectPiece
     */
	private JButton selectPiece = new JButton();
    
    /**
     * The button for switchSides
     */
	private JButton switchSides = new JButton();
    
    /**
     * The button to turn arrow mode on
     */
	private JButton arrowMode = new JButton();
    
    /**
     * The button to save a file 
     */
	private JButton saveFile = new JButton();

    /**
     * Boolean value of if arrow Mode is on
     */
	private boolean arrowModeOn = false;
    
    /**
     * Boolean value of if runSwitchSides is on
     */
	private boolean runSwitchSides = false;
    
    /**
     * Boolean value of if selectFileCheck is on
     */
	private boolean selectFileCheck = false;
    
    /**
     * Boolean value of if saveFileChecker is on
     */
	private boolean saveFileChecker = false;
    
    /**
     * Boolean value of if getSelectPiece is on
     */
	private boolean getSelectPiece = false;
    
    /**
     * Text size of the buttons
     */
	private int textSize = 50;
    
    /**
     * When creating the class it will set the textSize
     * @param x sets the text size of the buttons 
     */
    public TopMenu(int x) {
    		textSize = x;
    	
    }
    
    /**
     * This makes all the buttons with there names, colours and actionListener and everything else they need as well as putting in on a JPanel that it returns 
     * 
     * @return JPanel of the buttons
     */
    public JPanel topMenuMaker(){
        JPanel panel = new JPanel();

        selectFile = new JButton("Select File");
        selectFile.setBackground(Color.white);
        selectFile.addActionListener(this);
        selectFile.setFont(new Font("Arial", Font.PLAIN, textSize));
        selectFile.setBorderPainted(false);
        selectFile.setFocusable(false);
        panel.add(selectFile);

        saveFile = new JButton("Save State");
        saveFile.setBackground(Color.white);
        saveFile.addActionListener(this);
        saveFile.setFont(new Font("Arial", Font.PLAIN, textSize));
        saveFile.setBorderPainted(false);
        saveFile.setFocusable(false);
        panel.add(saveFile);

        selectPiece = new JButton("Pieces");
        selectPiece.setBackground(Color.white);
        selectPiece.setFont(new Font("Arial", Font.PLAIN, textSize));
        selectPiece.addActionListener(this);
        selectPiece.setBorderPainted(false);
        selectPiece.setFocusable(false);
        panel.add(selectPiece);

        switchSides = new JButton("Switch Sides");
        switchSides.setBackground(Color.white);
        switchSides.addActionListener(this);
        switchSides.setFont(new Font("Arial", Font.PLAIN, textSize));
        switchSides.setBorderPainted(false);
        switchSides.setFocusable(false);
        panel.add(switchSides);

        arrowMode = new JButton("Arrow Mode");
        arrowMode.setBackground(Color.white);
        arrowMode.addActionListener(this);
        arrowMode.setFont(new Font("Arial", Font.PLAIN, textSize));
        arrowMode.setBorderPainted(false);
        arrowMode.setFocusable(false);
        panel.add(arrowMode);

        return panel;
    }

    /**
     * Turns the boolean value saveFileChecker OFF
     */
    public void turnOffSaveFileChecker(){
        saveFileChecker = false;
    }

    /**
     * Turns the boolean value getSelectPiece OFF
     */
    public void turnOffSelectPiece(){
        getSelectPiece = false;
    }

    /**
     * Turns the boolean value selectFileCheck OFF
     */
    public void selectFileOff(){
        selectFileCheck = false;
    }

    /**
     * Turns the boolean value runSwitchSides OFF
     */
    public void switchSidesOff(){
        runSwitchSides = false;
    }
    
    /**
     * @return the value of runSwitchSides
     */
    public boolean checkSwitchSides(){
        return runSwitchSides;
    }
    
    /**
     * @return the value of getSelectPiece
     */
    public boolean getSelectPiece(){
        return getSelectPiece;
    }

    /**
     * @return the value of saveFileChecker
     */
    public boolean checkSaveFileChecker(){
        return saveFileChecker;
    }

    /**
     * @return the value of selectFileCheck
     */
    public boolean checkSelectFile(){
        return selectFileCheck;
    }

    /**
     * @return the value of arrowModeOn
     */
    public boolean checkArrowMode(){
        return arrowModeOn;
    }
    
    /**
     * Takes the action event. Finds which button was pressed and sets the value to true or for switch sides & arrow mode its opposite value.
     * For arrow mode it also changes it background based of its boolean value 
     * 
     * @param e the action event
     */
    private void actionLogic(ActionEvent e){

        if(e.getSource() == selectFile){
            selectFileCheck = true;
        }

        else if(e.getSource() == saveFile){
            saveFileChecker = true;
        }

        else if(e.getSource() == selectPiece){
            getSelectPiece = true;
        }

        else if(e.getSource() == switchSides){
            runSwitchSides = !runSwitchSides;
        }

        else if(e.getSource() == arrowMode){
            arrowModeOn = !arrowModeOn;

            if(arrowModeOn){
                arrowMode.setBackground(Color.red);
                arrowMode.setForeground(Color.white);
            }

            else{
                arrowMode.setBackground(Color.white);
                arrowMode.setForeground(Color.black);
            }
        }

    }

    /**
     * Runs action logic
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	actionLogic(e);
    }

}
