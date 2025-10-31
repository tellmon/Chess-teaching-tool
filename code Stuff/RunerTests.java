public class RunerTests {
    public static void main(String[] args) {
        
        System.out.println();

        FileMaker fileMaker = new FileMaker();
        FileReader fileReader = new FileReader();
        DataToArray dataToArray = new DataToArray();

        //String name = "Starting with No moves";
        String name = "Basic Start";
        String date = "03/9/2025";
        String data = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";


        fileReader.readFile(name);

        dataToArray.inputing(fileReader.getData());

        FramePanel framePanel = new FramePanel(dataToArray.returnChessArray());
        framePanel.SetPanelUp();
    }
}
