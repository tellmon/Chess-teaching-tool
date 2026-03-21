import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is used to create the files and then write the data to the files to be stored on the computer.
 */
public class FileMaker {

    /**
     * 
     * Takes a name and a string for that data to be written into the file. 
     * 
     * @param name the name of the file that is being written too
     * @param data this is that data that will get written into the file
     */
    public void dataToSave(String name, String data){ // name is the name of file and data is the data stored in FEN notation
        
        if (createFile(name) && writeDataToFile(name, data)){
            System.out.println("done fine");
        }

        else{
            System.out.println("did not create as a file already has that name");
        }
    }

    /**
     * This will create the file and then return true it it has gone will else it will return false. 
     * 
     * @param name the name of the file
     * @return true if the file was create successfully else something has gone wrong
     */
    private boolean createFile(String name){ // if true its made else its already exists
        try {
            File myObj = new File(name+".txt"); // Create File object
            
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

    /**
     * Takes the data and write it to the file. By turning it into bytes then reading them into the file. 
     * 
     * @param name the name of the file
     * @param data the data to be written into the file
     * @return true if the data was writen successfully else something has gone wrong
     */
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
