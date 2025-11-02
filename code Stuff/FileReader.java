import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    static String data = "";

    public void readFile(String name){
        File file = new File(name + ".txt");

        String fileInString = "";

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

        dataSpliter(fileInString);
    }

    private void dataSpliter(String fileInString){
        Boolean dataNow = false;

        for (int i = 0; i < fileInString.length(); i++){

            data += fileInString.charAt(i);
        }
    }

    public String getData(){
        if(data.equals("")){
            return "No data";
        }

        else
            return data;
    }
}