public class FileHandler {
	
    FileReader fileReader = new FileReader();
    DataToArray dataToArray = new DataToArray();
    FileMaker fileMaker = new FileMaker();
    
    public void readFile(String name) {
    	fileReader.readFile(name);
    }
    
    public String getData() {
    	return fileReader.getData();
    }
    
    public void turnDataToArray() {
    	dataToArray.inputing(getData());
    }
    
    public char[][] returnChessArray(){
    	return dataToArray.returnChessArray();
    }
    
    public void dataToSave(String fileNameToSaveAs, String dataOfBoardInNFS) {
    	fileMaker.dataToSave(fileNameToSaveAs, dataOfBoardInNFS);
    }   
}
