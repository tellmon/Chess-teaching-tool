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

/**
 * This is for the selectPiece panel that spawn over the board. This contains all the pieces needed to make all the chess boards needed.
 */
public class SelectPiece implements ActionListener{

	/**
	 *  JPanel for the buttons with the piece on it
	 */
	private JPanel panel = new JPanel(new GridLayout(0, 6));
    
    /**
     * The panel that holds the done button
     */
	private JPanel donePanel = new JPanel();

    /**
     * the array to hold the buttons
     */
	private  JButton[][] buttonArray = new JButton[2][6];
    
    /**
     * the JButton that has done on it
     */
	private JButton doneButton = new JButton();

    /**
     * the frame that is displayed to put everything onto
     */
	private JFrame frame = new JFrame();

    /**
     * boolean value to see if everything has been done
     */
	private boolean setUp = false;
    
    /**
     * boolean value to see if done has been selected
     */
	private boolean done = false;

    /**
     * the name of the button lasted pressed
     */
	private String name = "";

    /**
     * timer so i can trigger the action listener on a set timer. 
     */
	private Timer tick = new Timer(40, this);

    /**
     * this sets up the panel by starting the timer and creating the buttons and setting there texts as well as sets up the done button
     * and adds it to the frame after it runs setIcons to set there icons then sets setUp to true
     */
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
  
    /**
     * @return the name of the piece last clicked by this class
     */
    public String getName(){
        return name;
    }

    /**
     * sets the viability of the frame to false and done to false 
     */
    public void hidePanel(){
        frame.setVisible(false);
        done = false;
    }

    /**
     * sets the frame visibility to true
     */
    public void showPanel(){
        frame.setVisible(true);
    }

    /**
     * 
     * @return the value of done
     */
    public boolean getDone(){
        return done;
    }

    /**
     * 
     * @return the value of setUp
     */
    public boolean getSetUp(){
        return setUp;
    }   

    /**
     * uses a nested for loop to get every button with a switch case to set the icon of every button with the image it needs to display of the piece
     */
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
    
    /**
     * checks which button was pressed and then if it was a piece stores it in name else it sets done as true if it was that button
     * 
     * @param e the action event
     */
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
    
    /**
     * runs actionLogic
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        actionLogic(e);
    }
}