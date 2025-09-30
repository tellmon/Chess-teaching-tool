import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {


    static String date = "";
    static String data = "";

    public void readFile(String name){
        File file = new File(name + ".txt");

        String fileInString = "";

        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(file)) {

            while (myReader.hasNextLine()) {
                fileInString += myReader.nextLine(); // itz not reading the data for some reason
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

            if (fileInString.charAt(i) == '~'){
                dataNow = true;
            }

            else if(dataNow){
                data += fileInString.charAt(i);
            }
            
            else if(!dataNow){
                date += fileInString.charAt(i);
            }
            
        }
    }


    public String getData(){
        if(data.equals("")){
            return "No data";
        }

        else
            return data;
    }

    public String getDate(){
        if(date.equals("")){
            return "No date";
        }

        else
            return date;
    }
}