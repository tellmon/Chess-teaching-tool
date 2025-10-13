import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelForButtonArray implements ActionListener{

    String Piece = "";
    boolean movePiece = false;

    char[][] chessBoardState = new char[8][8];
    JButton[][] buttonArray = new JButton[8][8];

    public JPanel buttonArray() {
        
        JPanel panel = new JPanel(new GridLayout(0, 8));

        boolean switchColours = false;
        
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y] = new JButton();
                buttonArray[x][y].setText(""+chessBoardState[y][x]);
                buttonArray[x][y].addActionListener(this);

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

    public void setButtonArray(char[][] piecesCharArray){
        chessBoardState = piecesCharArray;
    }

    private void movePieces(ActionEvent e){

        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                if (e.getSource() == buttonArray[x][y]){
                    if (movePiece){
                        Piece = buttonArray[x][y].getText();
                        movePiece = false;
                        buttonArray[x][y].setText("");
                        System.out.println("Piece = " + buttonArray[x][y].getText());
                    }
                    
                    else{
                        buttonArray[x][y].setText(Piece);;
                        movePiece = true;
                        System.out.println("Pieces = " + Piece);
                    }
                    
                }
           }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        movePieces(e);
    }
}