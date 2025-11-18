import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SaveBoardState implements ActionListener{

    JFileChooser fileChooser = new JFileChooser();

    JFrame textInput = new JFrame("save File");
    JPanel textPanel = new JPanel();

    boolean submited = false;
    boolean setUpDone = false;
    
    String positonData = "";
   
    int count = 0;

    String text = "";


    public void setUpForInput(){

        setUpDone = true;
        fileChooser.addActionListener(this);

        fileChooser.showSaveDialog(fileChooser);
    }

    public String converToString(char[][] boardState) {
        
        for (int y = 0; y < 8; y++ ){
            for(int x = 0; x < 8; x++){
                
                if (boardState[x][y] == ' '){
                    count += 1;
                }

                else{

                    if(count != 0){
                        positonData += count;
                    }

                    positonData += boardState[x][y];

                    count = 0;
                }   
            }

            if(count != 0){
                positonData += count;
            }

            count = 0;
            positonData += '/';
        }  

        return positonData = positonData.substring(0, positonData.length() - 1);
    }

    public String getName() {
        return text;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        submited = true;
        
        text = fileChooser.getSelectedFile().getPath();
         
    }

    public Boolean ifSubmited(){
        return submited;
    }

    public boolean getSetUpDone(){
        return setUpDone;
    }

    public void reset(){
        setUpDone = false;
        submited = false;
    }
}
