import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMaker {

    
    public void dataToSave(String name, String date, String data){ // name is the name of file, date is the date saved and data is the data stored in FEN notation
        
        if (createFIle(name) && writeDataToFile(name, date, data)){
            System.out.println("done fine");
        }

        else{
            System.out.println("did not create as ia file already has that name");
        }
    }


    private boolean createFIle(String name){ // if true its made else its already exists
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

    private boolean writeDataToFile(String name, String date, String data){
        String str = date + "\n~\n" + data;

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
}
