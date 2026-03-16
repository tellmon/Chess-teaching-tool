
/**
 * This class if so any data that was taken from a file using my custom FEN notation can be converted to the 2D array
 */
public class DataToArray {
    
    // upper case is white
    // lower case is black
    //  rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR 
    // 
    //  Explaining it
    // 
    //  pieces              rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR                          
    //  number is just a empty space and how many times it is
    //  black to move       b
    //  black and white can both casele to both sides   KQkq
    //  the pawn has just moved 2 spaces at once        e3
    //  The number of halfmoves since the last capture or pawn advance 0
    //  The number of fullmoves since the last capture or pawn advance 1

	/**
	 * this is the array used to the store chess array as the 2D char. 
	 */
    char[][] chessBoardArray = new char[8][8];

    /**
     * This is the string in order to store the data before it is processed as to the array
     */
    String positonData = "";
    
    /**
     * This processes the entire chess board from the custom FEN notation to the 2D chess board array. 
     * The chess array is stored in chessBoardArray and can be returned from the method returnChessArray in this class.
     * 
     * @param data this is the data of the chess board before it is processed in a list 
     */
    public void inputing(String data){

        int nextLineCount = 0;
        int xAxis = 0;
        boolean skipRest = false;

        positonData = "";

        for(int i = 0; i < data.length(); i ++){

            if(data.charAt(i) == ' '){
                skipRest = true;
            }

            else if(Character.isDigit(data.charAt(i)) && !skipRest){

                int stopAt = Character.getNumericValue(data.charAt(i));

                for(int h = 0; h < stopAt; h ++){
                    
                    chessBoardArray[xAxis][nextLineCount] = ' ';
                    xAxis += 1;
                }
            }

            else if (skipRest) {
                positonData += data.charAt(i);
            }

            else if(!Character.isDigit(data.charAt(i)) && !skipRest){
                if(data.charAt(i) == '/'){
                    nextLineCount += 1;
                    xAxis = 0;
                }

                else{
                    chessBoardArray[xAxis][nextLineCount] = data.charAt(i);
                    xAxis += 1;
                }
            }
        }
    }

    /**
     * 
     * @return the chessBoardArray variable.
     */
    public char[][] returnChessArray(){
        return chessBoardArray;
    }

    /**
     * This gets the array and returns it as a string to be printed out of testing purposes. 
     * @return a string of the chess board printed out for testing purposes. 
     */
    public String boardStateOutput(){

        String output = "";

        for(int y = 0; y < 8; y ++){
            for(int x = 0; x < 8; x ++){
                output += chessBoardArray[x][y];
            }
            output += "\n";
        }

        return output;
    }
}
