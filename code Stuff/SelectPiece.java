import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SelectPiece implements ActionListener{

    JPanel panel = new JPanel(new GridLayout(0, 6));
    JPanel donePanel = new JPanel();

    JButton[][] buttonArray = new JButton[2][6];
    JButton doneButton = new JButton();

    JFrame frame = new JFrame();

    boolean setUp = false;
    boolean done = false;

    String name = "";

    Timer tick = new Timer(40, this);

    public void setUpPanel(){

        tick.start();

        for (int x = 0; x < 2; x++){
            for (int y = 0; y < 6; y++){
                buttonArray[x][y] = new JButton();
                
                buttonArray[x][y].setBorderPainted(false);
                buttonArray[x][y].setFocusable(false);
                
                buttonArray[x][y].setFont(new Font("Arial", Font.PLAIN, 80));
                buttonArray[x][y].addActionListener(this);
                buttonArray[x][y].setBackground(Color.WHITE);
                buttonArray[x][y].setForeground(new Color(0, 0, 0, 0));

                panel.add(buttonArray[x][y]);
            }
        }

        int x = 0;
        buttonArray[x][0].setText("p");
        buttonArray[x][1].setText("r");
        buttonArray[x][2].setText("n");
        buttonArray[x][3].setText("b");
        buttonArray[x][4].setText("q");
        buttonArray[x][5].setText("k");

        x = 1;
        buttonArray[x][0].setText("P");
        buttonArray[x][1].setText("R");
        buttonArray[x][2].setText("N");
        buttonArray[x][3].setText("B");
        buttonArray[x][4].setText("Q");
        buttonArray[x][5].setText("K"); 
        
        doneButton.setFont(new Font("Arial", Font.PLAIN, 80));
        doneButton.setText("Done");
        doneButton.addActionListener(this);
        doneButton.setBackground(Color.WHITE);
        doneButton.setBorderPainted(false);
        doneButton.setFocusable(false);

        donePanel.add(doneButton);
        frame.add(donePanel, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setSize(1000, 500);

        setIcons();

        setUp = true;
    }
  
    public String getName(){
        return name;
    }

    public void hidePanel(){
        frame.setVisible(false);
        done = false;
    }

    public void showPanel(){
        frame.setVisible(true);
    }

    public boolean getDone(){
        return done;
    }

    public boolean getSetUp(){
        return setUp;
    }   

    private void setIcons(){

        String text = "";

        for (int x = 0; x < 2; x ++){
            for (int y = 0; y < 6; y ++){

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
    
    private void actionLogic(ActionEvent e){

        if(e.getSource() == doneButton){
            done = true;
        }

        else{

            for (int x = 0; x < 2; x++){
                for (int y = 0; y < 6; y++){
                    if(e.getSource() == buttonArray[x][y]){
                        name = buttonArray[x][y].getText();
                        
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionLogic(e);
    }
}