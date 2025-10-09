import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board {

    JButton[][] buttonArray = new JButton[8][8];

    JButton selectFile = new JButton();
    JButton selectPiece = new JButton();
    JButton switchSides = new JButton();
    JButton arrowMode = new JButton();

    
    int width = 0;
    int height = 0; 

    public Board(){
        // get it to be the same size as the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();

        JFrame board = new JFrame("Chess Board");

        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(width, height);
        board.setVisible(true);
        board.isAlwaysOnTop();

        board.add(buttonArray(), BorderLayout.CENTER);
        board.add(topMenu(), BorderLayout.NORTH);
    }

    private JPanel numbers(){
        JPanel panel = new JPanel();

        return panel;
    }

    private JPanel letter(){
        JPanel panel = new JPanel();
        
        return panel;
    }

    private JPanel topMenu(){

        JPanel panel = new JPanel();

        selectFile = new JButton("File");
        selectFile.setBackground(Color.white);
        panel.add(selectFile);

        selectPiece = new JButton("Pieces");
        selectPiece.setBackground(Color.white);
        panel.add(selectPiece);

        switchSides = new JButton("Switch Sides");
        switchSides.setBackground(Color.white);
        panel.add(switchSides);

        arrowMode = new JButton("Arrow Mode");
        arrowMode.setBackground(Color.white);
        panel.add(arrowMode);

        return panel;
    } 

    private JPanel buttonArray(){
        
        JPanel panel = new JPanel(new GridLayout(0, 8));

        boolean switchColours = false;
        
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y] = new JButton("chess board");

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
        
        return panel;
    }
}