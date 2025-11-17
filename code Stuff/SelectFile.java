import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectFile implements ActionListener{
    // create a small text panel to enter the name of the file
    // use jfilepicker

    JFileChooser fileChooser = new JFileChooser();

    JFrame textInput = new JFrame("Input File");
    JPanel textPanel = new JPanel();

    String text = "";

    boolean submited = false;
    boolean setUpDone = false;
            
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

    public String getText(){
        return text;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // just get the file name to input

        int returnVal = fileChooser.showSaveDialog(null);

        if (returnVal != JFileChooser.APPROVE_OPTION){
            submited = false;
        }

        else{
            submited = true;

            text = fileChooser.getSelectedFile().getPath();
        }
        
        
    }
}
