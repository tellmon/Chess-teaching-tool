import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectPiece implements ActionListener{

    JPanel panel = new JPanel(new GridLayout(0, 6));
    JPanel donePanel = new JPanel();

    JButton[][] buttonArray = new JButton[2][6];
    JButton doneButton = new JButton();

    JFrame frame = new JFrame();

    boolean setUp = false;
    boolean done = false;

    String name = "";

    public void setUpPanel(){
        for (int x = 0; x < 2; x++){
            for (int y = 0; y < 6; y++){
                buttonArray[x][y] = new JButton();

                buttonArray[x][y].setFont(new Font("Arial", Font.PLAIN, 80));
                buttonArray[x][y].addActionListener(this);
                 buttonArray[x][y].setBackground(Color.WHITE);

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

        donePanel.add(doneButton);
        frame.add(donePanel, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setSize(1000, 500);

        setUp = true;
    }

    public void actionLogic(ActionEvent e){
        if(e.getSource() == doneButton){
            done = true;
        }

        else{

            for (int x = 0; x < 2; x++){
                for (int y = 0; y < 6; y++){
                    if(e.getSource() == buttonArray[x][y]){
                        name = buttonArray[x][y].getText();
                        /**
                         * i want the user to click on a square in the pieces then in the box. gets then return this. 
                         */
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionLogic(e);
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
}