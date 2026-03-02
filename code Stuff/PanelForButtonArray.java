import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelForButtonArray implements ActionListener{

    String piece = "";

    boolean movePiece = true;
    boolean letMove = true;

    char[][] chessBoardState = new char[8][8];
    JButton[][] buttonArray = new JButton[8][8];

    ValidMoveGetter validMoveGetter = new ValidMoveGetter();

    JButton lastButtonPressed = new JButton();

    ActionEvent lastEvent;

    Timer tick = new Timer(40, this);

    int oldx = 0;
    int oldy = 0;

    Color oldButtonColour = new Color(100, 100, 250);
    int colourX, colourY;

    private FramePanel frame;

    public PanelForButtonArray(FramePanel frame) {
        tick.start();

        this.frame = frame;
    }

    public JPanel buttonArray() {
        
        JPanel panel = new JPanel(new GridLayout(0, 8));

        boolean switchColours = false;
        
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
             
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y] = new JButton();
                
                buttonArray[x][y].setBorderPainted(false);
                buttonArray[x][y].setFocusable(false);
                
                buttonArray[x][y].setFont(new Font("Arial", Font.PLAIN, 80));
                                               
                buttonArray[x][y].addActionListener(this);

                buttonArray[x][y].setForeground(new Color(0, 0, 0, 0));

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

        loadBoard();
        
        return panel;
    }

    private void lastButtonPresed(ActionEvent e){
        for (int x = 0; x < 8; x ++){
           for (int y = 0; y < 8; y ++){
                if (e.getSource() == buttonArray[x][y]){
                    lastButtonPressed = buttonArray[x][y];
                }
            }
        }
    }

    private void setIcons(){

        String text = "";

        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){

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

    private void movePieces(ActionEvent e){
        if(letMove){
            for (int x = 0; x < 8; x ++){
                for (int y = 0; y < 8; y ++){
                    if (e.getSource() == buttonArray[x][y] && buttonArray[x][y].getText().charAt(0) != 0){
                    	                  	
                    	                    	
                        //check if its a piece then pick it up if its not then don't but ever way let it place. 
                        if (movePiece && !buttonArray[x][y].getText().matches("") && !buttonArray[x][y].getText().matches(" ")){
                            piece = buttonArray[x][y].getText();
                            buttonArray[x][y].setIcon(null);

                            oldx = x;
                            oldy = y;

                            movePiece = false;
                            buttonArray[x][y].setText(" ");

                            buttonArray[colourX][colourY].setBackground(oldButtonColour);
                        }
                        
                        else if(!movePiece){

                            oldButtonColour = buttonArray[x][y].getBackground();
                            colourX = x;
                            colourY = y;

                            if(validMoveGetter.checkIfValidMove(piece, oldx, oldy, x, y)){
                                buttonArray[x][y].setBackground(Color.green);
                            }

                            else{
                                buttonArray[x][y].setBackground(Color.red);
                            }

                            buttonArray[x][y].setText(piece);;
                            movePiece = true;
                        }
                        
                    }
                }
            }
        }
    }

    public void flipBoard(){

        JButton[][] flipedButtonArray = new JButton[8][8];

        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y].setIcon(null);
            }
        }


        // fliping in x and y axis
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                flipedButtonArray[x][y] = new JButton();  
            }
        }

        //flipping axis
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                flipedButtonArray[-y + 7][-(x-7)].setText(buttonArray[y][x].getText());
            }
        }

        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y].setText(flipedButtonArray[x][y].getText());
            }
        }

        resetColourOfButton();
        
        /**    
        * logic:
        * 
        * so we need to flip the board in the black and white center lines.
        * so y = x
        * and
        * y = -(x-7) # this is using the first square a 0,0 not 1,1 to as we are doing arrays
        * 
        * to get the x its -y + 7 = x
        * and for the y its y = -(x-7) 
        * we would need this in a sepreat list so we can switch between them, 
        * 
        * Switch between the two list like this:
        * temp = x
        * x = y
        * y = temp
        * 
        */
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lastEvent = e;
        frame.setPosisitonsForArrows();
        movePieces(e);
        lastButtonPresed(e);
        setIcons();
    }

    public void loadBoard(){
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                buttonArray[x][y].setIcon(null);
                buttonArray[x][y].setText(""+chessBoardState[y][x]);
            }
        }
        resetColourOfButton();
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

    public JButton getLastButton(){
        return lastButtonPressed;
    }

    public char[][] getBoardState(){
        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                chessBoardState[y][x] = buttonArray[x][y].getText().charAt(0);
            }
        }

        return chessBoardState;
    }

    private void resetColourOfButton(){
        lastButtonPressed.setBackground(oldButtonColour);
    }
}