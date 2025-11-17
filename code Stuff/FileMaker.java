import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMaker {

    
    public void dataToSave(String name, String data){ // name is the name of file and data is the data stored in FEN notation
        
        if (createFile(name) && writeDataToFile(name, data)){
            System.out.println("done fine");
        }

        else{
            System.out.println("did not create as a file already has that name");
        }
    }


    private boolean createFile(String name){ // if true its made else its already exists
        try {
            File myObj = new File(name); // Create File object
            
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

    private boolean writeDataToFile(String name, String data){
        String str = data;

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
