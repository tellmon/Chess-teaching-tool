public class RunerTests {
    public static void main(String[] args) {
        
        FileMaker fileMaker = new FileMaker();
        FileReader fileReader = new FileReader();

        String name = "testing";
        String date = "12/12/2012";
        String data = "The Test Worked";

        fileMaker.dataToSave(name, date, data);

        fileReader.readFile(name);

        System.out.println("date = "+fileReader.getDate() + " \nData = "+fileReader.getData());
    }
}
// maybe change sysout to a return statment so it can got to pop up for later 