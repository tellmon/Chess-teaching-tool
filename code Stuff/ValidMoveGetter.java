import javax.swing.ImageIcon;
import java.lang.Math;

public class ValidMoveGetter {
    
    
    /**
     * gets all valid moves.uses a switch case and method to hightlite all valid moves with green
     * maybe get to show check with red
     */
    

    public boolean checkIfValidMove(String piece, int lastx, int lasty, int newx, int newy){
        
        // https://stackoverflow.com/questions/70178247/how-do-i-check-if-the-move-is-valid-or-not-made-by-knight-and-bishop

        // if displacment in x == displacement in y

        int xdiff = Math.abs(lastx) - Math.abs(newx);
        int ydiff = Math.abs(lasty) - Math.abs(newy);

        System.out.println("xDiff = "+xdiff);
        System.out.println("yDiff = "+ydiff);
        
        // get the absualute value. the |x| thing in maths

        switch(piece.toUpperCase()){
      
            case "P":
                if((lastx == newx - 1 || lastx == newx + 1) && (lasty == newy - 1 || lasty == newy + 1)){
                    System.out.println("valid move");
                    return true;
                }
                       
            break;
            
            case "R":
                if((lasty == newy && lastx != newx) || (lastx == newx && lasty != newy)){
                    System.out.println("valid move");
                    return true;
                }
            
            break;


            case "N":
                if((xdiff == 2 && ydiff == 1) || (xdiff == 1 && ydiff == 2)){
                   System.out.println("valid move");
                   return true; 
                }
                
            break;

            case "B":
                
                if(xdiff == ydiff){
                    System.out.println("valid move");
                    return true;
                }
                
            break;

            case "Q":
                if((xdiff == ydiff) || (lasty == newy && lastx != newx) || (lastx == newx && lasty != newy)){
                    System.out.println("valid move");
                    return true;
                }

            break;

            case "K":
                if(xdiff == 1 || ydiff == 1){
                    System.out.println("valid move");
                    return true;
                }
                
            break;
        }
        return false;
    }
}
