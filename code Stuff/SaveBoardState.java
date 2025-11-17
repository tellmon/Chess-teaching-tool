import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        
        textInput.setAlwaysOnTop(true);
        textInput.setVisible(true);
        textInput.setResizable(true);
        textInput.setLocationRelativeTo(null);
        textInput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textInput.setSize(700, 400);

        fileChooser.addActionListener(this);

        textPanel.add(fileChooser);
        textInput.add(textPanel);
        setUpDone = true;
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
         System.out.println("daved4.0");
        return text;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showSaveDialog(fileChooser);
        System.out.println("daved3.0");

        if (returnVal != JFileChooser.APPROVE_OPTION){
            submited = false;
        }

        else{
            submited = true;

            text = fileChooser.getSelectedFile().getName();
            System.out.println("daved2.0");
        }    
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
