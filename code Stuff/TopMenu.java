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

    public JPanel topMenuMaker(){

        JPanel panel = new JPanel();

        selectFile = new JButton("Select File");
        selectFile.setBackground(Color.white);
        selectFile.setFont(new Font("Arial", Font.PLAIN, 80));
        panel.add(selectFile);

        saveFile = new JButton("Save State");
        saveFile.setBackground(Color.white);
        saveFile.setFont(new Font("Arial", Font.PLAIN, 80));
        panel.add(saveFile);

        selectPiece = new JButton("Pieces");
        selectPiece.setBackground(Color.white);
        selectPiece.setFont(new Font("Arial", Font.PLAIN, 80));
        panel.add(selectPiece);

        switchSides = new JButton("Switch Sides");
        switchSides.setBackground(Color.white);
        switchSides.setFont(new Font("Arial", Font.PLAIN, 80));
        panel.add(switchSides);

        arrowMode = new JButton("Arrow Mode");
        arrowMode.setBackground(Color.white);
        arrowMode.addActionListener(this);
        arrowMode.setFont(new Font("Arial", Font.PLAIN, 80));
        panel.add(arrowMode);

        return panel;
    }

    private void actionDoer(ActionEvent e){
        if(e.getSource() == selectFile){
            System.out.println("select file");

        }

        else if(e.getSource() == saveFile){
            System.out.println("save file");

        }

        else if(e.getSource() == selectPiece){
            System.out.println("select piece");

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
        // TODO Auto-generated method stub
        actionDoer(e);
    } 

    public boolean checkArrowMode(){
        return arrowModeOn;
    }
}
