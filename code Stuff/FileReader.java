import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    static String data = "";

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

    
    public String getData(){
        if(data.equals("")){
            return "No data";
        }

        else
            return data;
    }
}