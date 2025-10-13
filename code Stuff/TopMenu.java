import java.awt.Color;
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

    public JPanel topMenuMaker(){

        JPanel panel = new JPanel();

        selectFile = new JButton("Select File");
        selectFile.setBackground(Color.white);
        panel.add(selectFile);

        saveFile = new JButton("Save State");
        saveFile.setBackground(Color.white);
        panel.add(saveFile);

        selectPiece = new JButton("Pieces");
        selectPiece.setBackground(Color.white);
        panel.add(selectPiece);

        switchSides = new JButton("Switch Sides");
        switchSides.setBackground(Color.white);
        panel.add(switchSides);

        arrowMode = new JButton("Arrow Mode");
        arrowMode.setBackground(Color.white);
        arrowMode.addActionListener(this);
        panel.add(arrowMode);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("working on this");
    } 
}
