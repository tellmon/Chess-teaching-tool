import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidePartsOfBoard {

    JLabel[] numberArray = new JLabel[9];
    JLabel[] letterArray = new JLabel[10];
    
    public JPanel numbers(){
        JPanel panel = new JPanel(new GridLayout(8, 0));

        for(int i = 8; i > 0; i--){
            numberArray[i] = new JLabel(""+i);
            panel.add(numberArray[i]);
            numberArray[i].setFont(new Font("Arial", Font.PLAIN, 80));
            // make this vertical
        }

        return panel;
    }

    public JPanel letter(){
        JPanel panel = new JPanel(new GridLayout(0, 8));

        String alphabet = "ABCDEFGH";

        for(int i = 0; i < 8; i++){
            letterArray[i] = new JLabel("       "+alphabet.charAt(i)); // 7 spaces
            panel.add(letterArray[i]);
            letterArray[i].setFont(new Font("Arial", Font.PLAIN, 80));
        }

        return panel;
    }
}
