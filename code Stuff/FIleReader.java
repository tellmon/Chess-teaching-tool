import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FIleReader {


    static String date = "";
    static String data = "";

    public static String readFile(String name){
        File myObj = new File(name + ".txt");

        String fileInString = "";

        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(myObj)) {

            

            while (myReader.hasNextLine()) {

                fileInString = myReader.nextLine();
            }
        } 
        
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return fileInString;
    }

    public static void dataSpliter(String fileInString){
        Boolean dataNow = false;

        for (int i = 0; i < fileInString.length(); i++){
            if (fileInString.charAt(i) == '~'){
                dataNow = true;
            }

            if(dataNow){
                data += fileInString.charAt(i);
            }
            
            else{
                date += fileInString.charAt(i);
            }
            
        }
    }


    public String getData(){
        if(getData().equals("")){
            return "No data";
        }

        else
            return data;
    }

    public String getDate(){
        if(getData().equals("")){
            return "No date";
        }

        else
            return date;
    }

    
    public static void main(String[] args) {
        
        String name = "Basic Start";
        
        dataSpliter(readFile(name));

        System.out.println(date);
        System.out.println(data);
    }
}