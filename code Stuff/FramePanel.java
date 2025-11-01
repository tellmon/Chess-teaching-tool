import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FramePanel extends JFrame implements ActionListener{

    int width = 0;
    int height = 0;

    PanelForButtonArray panelForButtonArray;
    TopMenu topMenu = new TopMenu();
    SidePartsOfBoard sidePartsOfBoard = new SidePartsOfBoard();
    ArrowLogic arrowLogic = new ArrowLogic();
    JFrame board = new JFrame("Chess Board");

    Timer tick = new Timer(40, this);

    char[][] buttonArray;

    JPanel boardPanel;

    public FramePanel(char[][] buttonArray){
        panelForButtonArray = new PanelForButtonArray(this);
        this.buttonArray = buttonArray;
        tick.start();
    }

    public void SetPanelUp(){
        panelForButtonArray.setButtonArray(buttonArray);
        boardPanel = panelForButtonArray.buttonArray();

        // get it to be the same size as the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();

        // sets up the board
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(width, height);
        board.setVisible(true);
        board.isAlwaysOnTop();
        

        // creates the layaed panel
        JLayeredPane layeredPane = new JLayeredPane();

        layeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(arrowLogic, JLayeredPane.DRAG_LAYER);
        arrowLogic.setOpaque(false);

        layeredPane.setBounds(0, 0, width, height);
        layeredPane.setPreferredSize(new Dimension(width, height));
        layeredPane.setSize(new Dimension(width, height - 250));
        boardPanel.setSize(new Dimension(width, height - 250)); 
        arrowLogic.setSize(new Dimension(width, height - 250));

        // adds it all tp the board
        board.add(layeredPane, BorderLayout.CENTER);
        board.add(topMenu.topMenuMaker(), BorderLayout.NORTH);
        board.add(sidePartsOfBoard.numbers(), BorderLayout.WEST);
        board.add(sidePartsOfBoard.letter(), BorderLayout.SOUTH);
    }

    public void setPosisitonsForArrows(){ 
        boolean arrowMode = topMenu.checkArrowMode();
        
        if(arrowMode){
            panelForButtonArray.setLetMove(false);
            arrowLogic.getXAndYOfButtonInPixels(panelForButtonArray.getActionEvent(), panelForButtonArray.getButtonArray());
        }

        else{
            arrowLogic.eraseArrows();
            panelForButtonArray.setLetMove(true);
        }

        boardPanel.repaint();
    }

    private void actionLogic(ActionEvent e){
        if(topMenu.checkSwitchSides()){
            panelForButtonArray.flipBoard();
            topMenu.switchSidesOff();
       }
       
       boardPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       actionLogic(e);
    }
}