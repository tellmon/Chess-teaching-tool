import java.lang.Math;

/**
 * This class is used to check if the move is that was last made is valid or not. 
 */
public class ValidMoveGetter {
    
    /**
     * Gets all valid moves uses a switch case to see if a move is valid or not.
     * If the move was valid it will return true else it will return false. 
     * 
     * @param piece the piece that was moved that we are checking
     * @param lastx its last x position
     * @param lasty its last y position
     * @param newx its new x position
     * @param newy its new y position
     * @return true if the move is valid or false if not. 
     */
    public boolean checkIfValidMove(String piece, int lastx, int lasty, int newx, int newy){
        
        // if displacment in x == displacement in y
    	    	   	
        int xdiff = Math.abs(lastx) - Math.abs(newx);
        int ydiff = Math.abs(lasty) - Math.abs(newy);

        xdiff = Math.abs(xdiff);
        ydiff = Math.abs(ydiff);

        switch(piece.toUpperCase()){
        
                
            case "P":
                
                if(lastx == 6 || lastx == 1){
                    if(xdiff == 2 || xdiff == 1){
                        return true;
                    }
                }
                else{
                    if(xdiff == 1){
                        return true;
                    }
                }

                                       
            break;
            
            case "R":
                if((lasty == newy && lastx != newx) || (lastx == newx && lasty != newy)){
                    return true;
                }
            
            break;


            case "N":
                if((xdiff == 2 && ydiff == 1) || (xdiff == 1 && ydiff == 2)){
                   return true; 
                }
                
            break;

            case "B":
                
                if(xdiff == ydiff){
                    return true;
                }
                
            break;

            case "Q":
                if((xdiff == ydiff) || (lasty == newy && lastx != newx) || (lastx == newx && lasty != newy)){
                   return true;
                }

            break;

            case "K":
                if((xdiff == 1 && ydiff == 1) || (xdiff == 0 && ydiff == 1) || (xdiff == 1 && ydiff == 0)){
                    return true;
                }
                
            break;
        }
        return false;
    }
}
