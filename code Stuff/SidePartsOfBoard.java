import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This creates the sizes of the board. For the sides of the board to be displayed as the grid array.
 */
public class SidePartsOfBoard {
	
	/**
     * This is the JLable array for the numbers
     */
	private JLabel[] numberArray = new JLabel[9];
    
    /**
     * This is the JLabel array for the letters
     */
	private JLabel[] letterArray = new JLabel[10];

    /**
     * This is the JPanel for the numbers
     */
	private JPanel numberPanel = new JPanel(new GridLayout(8, 0));
    
    /**
     * This is the JPanel for the letters
     */
	private JPanel letterPanel = new JPanel(new GridLayout(0, 8));

    /**
     * The string to be displayed at the bottom of the board to be broken up. 
     */
	private String alphabet = "ABCDEFGH";
    
    /**
     * The test size of the letters and number to be displayed 
     */
	private int textSize = 50;
    
    /**
     * This is used to set the size of the letters when creating the class
     * 
     * @param x when creating this method it needs a text size to set the size of the letters and numbers. 
     */
    public SidePartsOfBoard(int x) {
    	textSize = x;
    }
    
    /**
     * This creates a JPanel that has 8 JLabels for each number from 0-8 and adds then too the JPanel to be returned. 
     * 
     * @return a JPanel of all the numbers for the grid reference
     */
    public JPanel numbers(){
        
        for(int i = 0; i < 8; i++){
            numberArray[i] = new JLabel();
            numberArray[i].setText(""+(8-i));
            numberPanel.add(numberArray[i]);
            numberArray[i].setFont(new Font("Arial", Font.PLAIN, textSize));
        }

        return numberPanel;
    }

    /**
     * This creates a JPanel that has 8 JLabels for each number from A-H and adds then too the JPanel to be returned. 
     * 
     * @return the JPanel of all the letters for the grid reference
     */
    public JPanel letter() {

        for (int i = 0; i < 8; i++) {
            letterArray[i] = new JLabel(String.valueOf(alphabet.charAt(i)));
            letterArray[i].setFont(new Font("Arial", Font.PLAIN, textSize));
            letterArray[i].setHorizontalAlignment(JLabel.CENTER);
            letterArray[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            letterPanel.add(letterArray[i]);
        }

        return letterPanel;
    }

    /**
     * Flips the both numbers and letters to be in reverse. So 1-8 goes 8-1 and Vice versa  same for the letter as well. 
     */
    public void flipNumbersAndLetters(){

        if(numberArray[7].getText().equals("8")){
            
            for(int i = 0; i < 8; i++){
                numberArray[i].setText(""+(8-i));
            }
            
            for(int i = 0; i < 8; i++){
                letterArray[i].setText(""+alphabet.charAt(i));
            }
        }

        else{

            for(int i = 0; i < 8; i++){
                numberArray[i].setText(""+(i+1));
            }

            for(int i = 0; i < 8; i++){
                letterArray[i].setText(""+alphabet.charAt(7-i));
            }
        }
    }
}
