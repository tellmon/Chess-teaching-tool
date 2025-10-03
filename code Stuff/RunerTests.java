public class RunerTests {
    public static void main(String[] args) {
        
        FileMaker fileMaker = new FileMaker();
        FileReader fileReader = new FileReader();
        DataToArray dataToArray = new DataToArray();

        //String name = "Starting with No moves";
        String name = "Basic Start";
        String date = "03/9/2025";
        String data = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

        //fileMaker.dataToSave(name, date, data);

        fileReader.readFile(name);

        //System.out.println("date = "+fileReader.getDate() + " \nData = "+fileReader.getData());

        dataToArray.inputing(fileReader.getData());

        System.out.println(dataToArray.boardStateOutput());
    }
}
