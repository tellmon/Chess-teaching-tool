import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class FramePanel extends JFrame{

    int width = 0;
    int height = 0;

    PanelForButtonArray panelForButtonArray = new PanelForButtonArray();
    TopMenu topMenu = new TopMenu();
    SidePartsOfBoard sidePartsOfBoard = new SidePartsOfBoard();
    ArrowLogic arrowLogic = new ArrowLogic();
    JFrame board = new JFrame("Chess Board");

    public FramePanel(char[][] buttonArray){

        panelForButtonArray.setButtonArray(buttonArray);
        JPanel boardPanel = panelForButtonArray.buttonArray();

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
        // run this every time a button is pressed inside buttonArray
        boolean arrowMode = topMenu.checkArrowMode();
        System.out.println("running this");

        if(arrowMode){
            System.out.println("arrow mode on");
            arrowLogic.getXAndYOfButtonInPixels(panelForButtonArray.getActionEvent(), panelForButtonArray.getButtonArray());
        }
    }
}