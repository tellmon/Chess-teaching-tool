/**
 * This handles all the file part of the code as of it. this handles all of its operations
 */
public class FileHandler {
	
	/**
	 * This makes the file Reader class to be used to read the files.
	 */
    FileReader fileReader = new FileReader();
    
    /**
     * This creates the dataToArray class so data can be converted to the 2D chess board array
     */
    DataToArray dataToArray = new DataToArray();
    
    /**
     * The creates the fileMaker class that is used to make files. 
     */
    FileMaker fileMaker = new FileMaker();
    
    /**
     * Calls fileReader to read the file
     * @param name name of the file to read
     */
    public void readFile(String name) {
    		fileReader.readFile(name);
    }
    
    /**
     * This returns the string of the data read from fileReader
     * @return the string of the data that was read from fileReader
     */
    public String getData() {
    		return fileReader.getData();
    }
    
    /**
     * this gets the data from the fileReader and converts it to the 2D array. 
     */
    public void turnDataToArray() {
    		dataToArray.inputing(getData());
    }
    
    /**
     * this calls dataToArray to grab the array to return it. 
     * @return the 2D chess array
     */
    public char[][] returnChessArray(){
    		return dataToArray.returnChessArray();
    }
    
    /**
     * Calls fileMaker dataToSave function to create hte file and then write the data into it. 
     * 
     * @param fileNameToSaveAs the file name
     * @param dataOfBoardInNFS the data to be written into the file
     */
    public void dataToSave(String fileNameToSaveAs, String dataOfBoardInNFS) {
    		fileMaker.dataToSave(fileNameToSaveAs, dataOfBoardInNFS);
    }   
}
