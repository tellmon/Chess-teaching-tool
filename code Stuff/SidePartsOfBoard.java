import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidePartsOfBoard {

    JLabel[] numberArray = new JLabel[9];
    JLabel[] letterArray = new JLabel[10];

    JPanel numberPanel = new JPanel(new GridLayout(8, 0));
    JPanel letterPanel = new JPanel(new GridLayout(0, 8));

    String alphabet = "ABCDEFGH";
    
    public JPanel numbers(){
        
        for(int i = 0; i < 8; i++){
            numberArray[i] = new JLabel();
            numberArray[i].setText(""+(8-i));
            numberPanel.add(numberArray[i]);
            numberArray[i].setFont(new Font("Arial", Font.PLAIN, 80));
        }

        return numberPanel;
    }

    public JPanel letter(){
        
        for(int i = 0; i < 8; i++){
            letterArray[i] = new JLabel(); // 7 spaces
            letterArray[i].setText("       "+alphabet.charAt(i));
            letterPanel.add(letterArray[i]);
            letterArray[i].setFont(new Font("Arial", Font.PLAIN, 80));
        }

        return letterPanel;
    }

    public void flipNumbersAndLetters(){

        if(numberArray[7].getText().equals("8")){
            
            for(int i = 0; i < 8; i++){
                numberArray[i].setText(""+(8-i));
            }
            
            for(int i = 0; i < 8; i++){
                letterArray[i].setText("       "+alphabet.charAt(i));
            }
        }

        else{

            for(int i = 0; i < 8; i++){
                numberArray[i].setText(""+(i+1));
            }

            for(int i = 0; i < 8; i++){
                letterArray[i].setText("       "+alphabet.charAt(7-i));
            }
        }
    }
}
