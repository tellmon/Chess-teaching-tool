import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMaker {

    
    public static void dataToSave(String name, String date, String data){ // name is the name of file, date is the date saved and data is the data stored in FEN notation
        
        if (createFIle(name) && writeDataToFile(name, date, data)){
            System.out.println("done fine");
        }

        else{
            System.out.println("error as it alreadt exists or it cant write");
        }
    }


    public static boolean createFIle(String name){ // if true its made else its already exists
        try {
            File myObj = new File(name +".txt" ); // Create File object
            
            if (myObj.createNewFile()) {           // Try to create the file
                return true; // done
            } 

            else {
                return false; // already exisits
            }
        } 
    
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); // Print error details
        }

        return false; // error

    }

    public static boolean writeDataToFile(String name, String date, String data){
        String str = date + "~ \n" + data;

        Path path = Paths.get(name+ ".txt");
        byte[] strToBytes = str.getBytes();

        try {
            Files.write(path, strToBytes);
            return true;
        } 
        
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Failed to write");
        }
        return false;
    }


    public static void main(String[] args) {

        String file = "Basic Start";
        String date = "12/12/2012";
        String data = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";

        dataToSave(file, date, data);
    }
}
