import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class controls the drawing of the arrows this is used to draw the arrows from a given position using graphics 2D
 */
public class ArrowLogic extends JPanel{

	/**
	 * The x position of the first square selected
	 */
	private int xPosFirst = 0;
    
    /**
     * The y position of the first square selected
     */
	private int yPosFirst = 0;

    /**
     * The x position of the second square selected
     */
	private int xPosSecond = 0;
    
    /**
     * The y position of the second square selected
     */
	private int yPosSecond = 0;

    /**
     * This high of the JButton
     */
	private int hight = 0;
    
    /**
     * The width of the JButton
     */
	private int width = 0;

    /**
     * Used to check if both positions have been selected
     */
	private boolean gotBothPos = false;

    /**
     * This is used to make the paint component for the drawing and for the g2 component. 
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

        drawArrow(g2);
    }

    /**
     * This method will check if both postions have been gotten and if thats true it will draw an arrow between them. 
     * @param g2 this is the graphics object. 
     */
    private void drawArrow(Graphics2D g2) {
        if (gotBothPos) {
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(10));
            g2.draw(new Line2D.Double(xPosFirst, yPosFirst, xPosSecond, yPosSecond));

            double xDiff = xPosSecond - xPosFirst;
            double yDiff = yPosSecond - yPosFirst;
            double angle = Math.atan2(yDiff, xDiff);

            int arrowLength = width / 4;
            int arrowWidth = hight / 4;

            int[] xForTriangle = {xPosSecond, xPosSecond - arrowLength, xPosSecond - arrowLength };
            int[] yForTriangle = {yPosSecond, yPosSecond - arrowWidth, yPosSecond + arrowWidth};

            AffineTransform old = g2.getTransform();
            g2.rotate(angle, xPosSecond, yPosSecond);
            g2.setColor(Color.RED);
            g2.fillPolygon(xForTriangle, yForTriangle, 3);
            g2.setTransform(old);
        }
    }

    /**
     * This is used to get the x and y position of the JButton that was clicked and then check to set it as the first position or the second position
     * as well as set the width and height of the arrow when both arrows have been gotten
     * 
     * @param e is the action event clicked
     * @param buttonArray the arrow of JButtons that was clicked
     */
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
                        hight = buttonArray[x][y].getHeight();
                        width = buttonArray[x][y].getWidth();
                        gotBothPos = true;
                    }
                }
            }
        }
    }

    /**
     * Returns if both positions of the arrow has been gotten
     * @return the value of getBothPos
     */
    public boolean checkIfBothPosHave(){
        return gotBothPos;
    }

    /**
     * Sets all vales to there default positions
     */
    public void eraseArrows(){
        xPosFirst = 0;
        yPosFirst = 0;
        xPosSecond = 0;
        yPosSecond = 0;
    }
}
