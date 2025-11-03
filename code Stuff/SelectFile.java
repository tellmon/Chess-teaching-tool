import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectFile implements ActionListener{
    // create a small text panel to enter the name of the file

    JFrame textInput = new JFrame("Input File");
    JPanel textPanel = new JPanel();
    JTextField textField = new JTextField(100);
    JButton submitButton = new JButton("Submit");

    String text = "";

    Boolean sumbinited = false;
            
    public void setUpForInput(){
            
            textInput.setAlwaysOnTop(true);
            textInput.setVisible(true);
            textInput.setResizable(true);
            textInput.setLocationRelativeTo(null);
            textInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            textInput.setSize(500, 200);
            
		    submitButton.setBackground(Color.WHITE);
            submitButton.addActionListener(this);
            
            textPanel.add(submitButton);
            textPanel.add(textField);
            textInput.add(textPanel);
    }

    public String getText(){
        textField.setText(text);
        return text;
    }

    public Boolean ifSubmited(){
        return sumbinited;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        text = textField.getText();
        sumbinited = true;
    }
}
