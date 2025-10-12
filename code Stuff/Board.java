import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board implements ActionListener {

    JButton[][] buttonArray = new JButton[8][8];

    JButton selectFile = new JButton();
    JButton selectPiece = new JButton();
    JButton switchSides = new JButton();
    JButton arrowMode = new JButton();
    JButton saveFile = new JButton();

    int xPosFirst = 0;
    int yPosFirst = 0;

    int xPosSecond = 0;
    int yPosSecond = 0;

    int hightSecond = 0;
    int widthSecond = 0;

    int width = 0;
    int height = 0; 

    char[][] chessBoardState = new char[8][8];

    boolean movePiece = false;

    String Piece = "";
    

    public void makeBoardDisplay(){
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

        selectFile = new JButton("Select File");
        selectFile.setBackground(Color.white);
        panel.add(selectFile);

        saveFile = new JButton("Save State");
        saveFile.setBackground(Color.white);
        panel.add(saveFile);

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
                buttonArray[x][y] = new JButton(""+chessBoardState[y][x]);
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

    public void giveChessBoardState(char[][] board){
        chessBoardState = board;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(showLines)
            drawConnectors(g2);
    }

    private void drawArrow(Graphics2D g2) {

        g2.setColor(Color.RED);
        //line
        g2.draw(new Line2D.Double(xPosFirst, yPosFirst, xPosSecond, yPosSecond));

        double angle = 0;

        double xDiff = xPosSecond - xPosFirst;
        double yDiff = yPosSecond - yPosFirst;

        angle = Math.atan2(yDiff, xDiff);

        //TRIANGLE  need to rotate somehow though
        g2.rotate(angle, xPosSecond, yPosSecond);
        g2.fillPolygon(new int[] {xPosSecond, xPosSecond - widthSecond, xPosSecond + widthSecond}, new int[] {yPosSecond, yPosSecond - hightSecond, yPosSecond + hightSecond}, 3);
    }

    private void movePieces(ActionEvent e){

        for (int x = 0; x < 8; x ++){
            for (int y = 0; y < 8; y ++){
                if (e.getSource() == buttonArray[x][y]){
                    if (movePiece){
                        Piece = buttonArray[x][y].getName();
                        movePiece = false;
                    }
                    
                    else{
                        buttonArray[x][y].setName(Piece);;
                        movePiece = true;
                        System.out.println("piece = " + Piece);
                    }
                    
                }
           }
        }
    }

    private void getXAndYOfButtonInPixels(ActionEvent e){

        if(xPosFirst == 0 && yPosFirst == 0){
            for (int x = 0; x < 8; x ++){
                for (int y = 0; y < 8; y ++){
                    if (e.getSource() == buttonArray[x][y]){
                        xPosFirst = buttonArray[x][y].getX() + buttonArray[x][y].getWidth() / 2;
                        yPosFirst = buttonArray[x][y].getY() + buttonArray[x][y].getHeight() / 2;
                    }
                }
            }
        }

        else{
            for (int x = 0; x < 8; x ++){
                for (int y = 0; y < 8; y ++){
                    if (e.getSource() == buttonArray[x][y]){
                        xPosSecond = buttonArray[x][y].getX() + buttonArray[x][y].getWidth() / 2;
                        yPosSecond = buttonArray[x][y].getY() + buttonArray[x][y].getHeight() / 2;
                        hightSecond = buttonArray[x][y].getHeight();
                        widthSecond = buttonArray[x][y].getWidth();
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //getXAndYOfButtonInPixels(e);
        movePieces(e);
    }
}