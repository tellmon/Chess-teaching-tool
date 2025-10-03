public class DataToArray {
    
    // upper case is white
    // lower case is black
    //  rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1
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

    char[][] chessBoardArray = new char[8][8];

    String positonData = "";

    public void inputing(String data){

        int nextLineCount = 0;
        int xAxis = 0;
        boolean skipRest = false;

        for(int i = 0; i < data.length(); i ++){
            if(Character.isDigit(data.charAt(i)) && !skipRest){

                System.out.println("Number is " + data.charAt(i));
                System.out.println("We are at "+i);
                System.out.println("skipRest is " + skipRest);

                for(int x = 0; i < data.charAt(i); i ++){
                    xAxis += x;
                    chessBoardArray[xAxis][nextLineCount] = ' ';
                }
            }

            else if(!Character.isDigit(data.charAt(i)) && !skipRest){
                if(data.charAt(i) == '/'){
                    nextLineCount += 1;
                    xAxis = 0;
                }

                else if(data.charAt(i) == ' '){
                    skipRest = true;
                }

                else{
                    xAxis += 1;
                    chessBoardArray[xAxis][nextLineCount] = data.charAt(i);
                }
            }

            else if (skipRest) {
                positonData += data.charAt(i);
            }
        }
    }

    public char[][] returnChessArray(){
        return chessBoardArray;
    }

    public void reset(){
        positonData = "";
    }
}
