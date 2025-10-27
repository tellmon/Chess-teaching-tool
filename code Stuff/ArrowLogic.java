import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ArrowLogic extends JPanel{

    int xPosFirst = 0;
    int yPosFirst = 0;

    int xPosSecond = 0;
    int yPosSecond = 0;

    int hightSecond = 0;
    int widthSecond = 0;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

        g.setColor(Color.BLACK);
        g2.fillRect(0, 0, 1000, 1000);

    }

    public void drawArrow(Graphics2D g2) {

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

    public void getXAndYOfButtonInPixels(ActionEvent e, JButton[][] buttonArray ){

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
}
