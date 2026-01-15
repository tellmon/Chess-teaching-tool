import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TopMenu implements ActionListener{

    JButton selectFile = new JButton();
    JButton selectPiece = new JButton();
    JButton switchSides = new JButton();
    JButton arrowMode = new JButton();
    JButton saveFile = new JButton();

    boolean arrowModeOn = false;
    boolean runSwitchSides = false;
    boolean selectFileCheck = false;
    boolean saveFileChecker = false;
    boolean getSelectPiece = false;
    
    int textSize = 50;
    
    public TopMenu(int x) {
    	textSize = x;
    	
    }
    
    public JPanel topMenuMaker(){

        JPanel panel = new JPanel();

        selectFile = new JButton("Select File");
        selectFile.setBackground(Color.white);
        selectFile.addActionListener(this);
        selectFile.setFont(new Font("Arial", Font.PLAIN, textSize));
        panel.add(selectFile);

        saveFile = new JButton("Save State");
        saveFile.setBackground(Color.white);
        saveFile.addActionListener(this);
        saveFile.setFont(new Font("Arial", Font.PLAIN, textSize));
        panel.add(saveFile);

        selectPiece = new JButton("Pieces");
        selectPiece.setBackground(Color.white);
        selectPiece.setFont(new Font("Arial", Font.PLAIN, textSize));
        selectPiece.addActionListener(this);
        panel.add(selectPiece);

        switchSides = new JButton("Switch Sides");
        switchSides.setBackground(Color.white);
        switchSides.addActionListener(this);
        switchSides.setFont(new Font("Arial", Font.PLAIN, textSize));
        panel.add(switchSides);

        arrowMode = new JButton("Arrow Mode");
        arrowMode.setBackground(Color.white);
        arrowMode.addActionListener(this);
        arrowMode.setFont(new Font("Arial", Font.PLAIN, textSize));
        panel.add(arrowMode);

        return panel;
    }

    public void turnOffSaveFileChecker(){
        saveFileChecker = false;
    }

    public void turnOffSelectPiece(){
        getSelectPiece = false;
    }

    public boolean getSelectPiece(){
        return getSelectPiece;
    }

    public boolean checkSaveFileChecker(){
        return saveFileChecker;
    }

    public boolean checkSelectFile(){
        return selectFileCheck;
    }

    public void selectFileOff(){
        selectFileCheck = false;
    }

    public boolean checkSwitchSides(){
        return runSwitchSides;
    }

    public void switchSidesOff(){
        runSwitchSides = false;
    }

    public boolean checkArrowMode(){
        return arrowModeOn;
    }
    
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

    @Override
    public void actionPerformed(ActionEvent e) {
    	actionLogic(e);
    }

}
