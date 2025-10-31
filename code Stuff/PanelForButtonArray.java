import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelForButtonArray implements ActionListener{

    String Piece = "";

    boolean movePiece = true;
    boolean letMove = true;

    char[][] chessBoardState = new char[8][8];
    JButton[][] buttonArray = new JButton[8][8];

    ActionEvent lastEvent;

    private FramePanel frame;

    public PanelForButtonArray(FramePanel frame) {
        this.frame = frame;
    }

    public JPanel buttonArray() {
        
        JPanel panel = new JPanel(new GridLayout(0, 8));

        boolean switchColours = false;
        
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y] = new JButton();
                buttonArray[x][y].setText(""+chessBoardState[y][x]);
                
                buttonArray[x][y].setFont(new Font("Arial", Font.PLAIN, 80));

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

    private void movePieces(ActionEvent e){
        if(letMove){
            for (int x = 0; x < 8; x ++){
                for (int y = 0; y < 8; y ++){
                    if (e.getSource() == buttonArray[x][y]){

                        // needs to check if its a pice then pick it up if its not then dont but ever way let it place. 
                        if (movePiece && !buttonArray[x][y].getText().matches("") && !buttonArray[x][y].getText().matches(" ")){
                            Piece = buttonArray[x][y].getText();

                            movePiece = false;
                            buttonArray[x][y].setText("");
                        }
                        
                        else if(!movePiece){
                            buttonArray[x][y].setText(Piece);;
                            movePiece = true;
                        }
                        
                    }
                }
            }
        }   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lastEvent = e;
        frame.setPosisitonsForArrows();
        movePieces(e);
    }

    public void setLetMove(boolean letMove){
        this.letMove = letMove;
    }

    public ActionEvent getActionEvent(){
        return lastEvent;
    }    

    public void setButtonArray(char[][] piecesCharArray){
        chessBoardState = piecesCharArray;
    }

    public JButton[][] getButtonArray(){
        return buttonArray;
    }

    public char[][] getBoardState(){
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                chessBoardState[y][x] = buttonArray[x][y].getText().charAt(0);
            }
        }

        return chessBoardState;
    }
}