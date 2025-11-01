import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
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

    boolean gotBothPos = false;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

        drawArrow(g2);
    }

    private void drawArrow(Graphics2D g2) {
        if (gotBothPos) {
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(10));
            g2.draw(new Line2D.Double(xPosFirst, yPosFirst, xPosSecond, yPosSecond));

            double xDiff = xPosSecond - xPosFirst;
            double yDiff = yPosSecond - yPosFirst;
            double angle = Math.atan2(yDiff, xDiff);

            int arrowLength = widthSecond / 4;
            int arrowWidth = hightSecond / 4;

            int[] xForTriangle = {xPosSecond, xPosSecond - arrowLength, xPosSecond - arrowLength };
            int[] yForTriangle = {yPosSecond, yPosSecond - arrowWidth, yPosSecond + arrowWidth};

            AffineTransform old = g2.getTransform();
            g2.rotate(angle, xPosSecond, yPosSecond);
            g2.setColor(Color.RED);
            g2.fillPolygon(xForTriangle, yForTriangle, 3);
            g2.setTransform(old);
        }
    }


    public void getXAndYOfButtonInPixels(ActionEvent e, JButton[][] buttonArray ){

        if(xPosFirst == 0 && yPosFirst == 0){
            for (int x = 0; x < 8; x ++){
                for (int y = 0; y < 8; y ++){
                    if (e.getSource() == buttonArray[x][y]){
                        xPosFirst = buttonArray[x][y].getX() + buttonArray[x][y].getWidth() / 2;
                        yPosFirst = buttonArray[x][y].getY() + buttonArray[x][y].getHeight() / 2;
                        gotBothPos = false;
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
                        hightSecond = buttonArray[x][y].getHeight() ;
                        widthSecond = buttonArray[x][y].getWidth() ;
                        gotBothPos = true;
                    }
                }
            }
        }
    }

    public boolean checkIfBothPosHave(){
        return gotBothPos;
    }

    public void eraseArrows(){
        xPosFirst = 0;
        yPosFirst = 0;
        xPosSecond = 0;
        yPosSecond = 0;
    }
}
