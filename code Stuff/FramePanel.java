import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FramePanel {

    int width = 0;
    int height = 0;

    public FramePanel(char[][] buttonArray){

        PanelForButtonArray panelForButtonArray = new PanelForButtonArray();
        TopMenu topMenu = new TopMenu();

        panelForButtonArray.setButtonArray(buttonArray);

        // get it to be the same size as the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();

        JFrame board = new JFrame("Chess Board");

        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(width, height);
        board.setVisible(true);
        board.isAlwaysOnTop();

        board.add(panelForButtonArray.buttonArray(), BorderLayout.CENTER);
        board.add(topMenu.topMenuMaker(), BorderLayout.NORTH);
        }
    }

