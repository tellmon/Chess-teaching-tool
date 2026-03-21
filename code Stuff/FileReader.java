import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is all about reading the file it takes a name of the file and reads each line from the computer to be used in other parts of the program and to get the board.  
 * 
 */
public class FileReader {

	/**
	 * this is the variable that the data is stored when the file is read out. 
	 */
	private static String data = "";

    /**
     * This will take a name of a file and read the full file into a string that can be returned with the getData method. 
     * 
     * @param name is the name of the file to read
     */
    public void readFile(String name){
        File file = new File(name);

        String fileInString = "";

        data = "";

        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(file)) {

            while (myReader.hasNextLine()) {
                fileInString += myReader.nextLine();
            }
        } 
        
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        data = fileInString;
    }

    /**
     * first checks if the string has any data and will return the data if it is not empty.
     * 
     * @return it will either return the string "No data" if the data is blank or the data that is in the variable. 
     */
    public String getData(){
        if(data.equals("")){
            return "No data";
        }

        else
            return data;
    }
}