import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class is used to create the frame and the interface used to select the file for importing into the board
 */
public class SelectFile implements ActionListener {

    /**
     * this is the interface that i use to save and select files for the program with a nice gui
     */
	private final JFileChooser fileChooser = new JFileChooser();
	
	/**
	 * This is the frame used to make the pop up for the selector to go on
	 */
    private final JFrame textInput = new JFrame("Input File");
    
    /**
     * this is the JPanel to put the JFileChooser onto. 
     */
    private final JPanel textPanel = new JPanel();

    /**
     * this will check if the user has submitted a file to import
     */
    private boolean submitted = false;
    
    /**
     * This check if the setup for the panel has already happened and will skip it if it has. 
     */
    private boolean setUpDone = false;
    
    /**
     * This checks if the panel has been closed by the user. 
     */
    private boolean closed = false;
    
    /**
     * this is the string where the file path will be stored. 
     */
    private String text = "";

    /**
     * This is creating the panel, applying the filter for just text files and adding the the windows listener and actionlisterner. then adding JFileChoser to the panel and setting it visible.
     */
    public void setUpForInput() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);

        textInput.setAlwaysOnTop(true);
        textInput.setResizable(true);
        textInput.setSize(700, 400);
        textInput.setLocationRelativeTo(null);
        textInput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        textInput.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                closed = true;
            }
        });

        fileChooser.addActionListener(this);

        textPanel.add(fileChooser);
        textInput.add(textPanel);

        textInput.setVisible(true);

        setUpDone = true;
        closed = false;
    }

    /**
     * @return the file path that was submitted
     */
    public String getText() {
        return text;
    }

    /** 
     * @return the boolean value of submitted
     */
    public boolean isSubmitted() {
        return submitted;
    }

    /**
     * @return the boolean value of setUpDone
     */
    public boolean isSetUpDone() {
        return setUpDone;
    }

    /**
     * @return the boolean value of closed
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * resets all the values to there default states
     */
    public void reset() {
        setUpDone = false;
        submitted = false;
        closed = false;
        text = "";
    }

    /**
     * This will get the action event and then check if the file is one of the approved section then if so it will set is as the text 
     * else it will keep the panel open till a approved section is submitted
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if (cmd.equals(JFileChooser.APPROVE_SELECTION)) {
            submitted = true;
            text = fileChooser.getSelectedFile().getAbsolutePath();
            closed = false;
            textInput.dispose();
        }

        else if (cmd.equals(JFileChooser.CANCEL_SELECTION)) {
            submitted = false;
            closed = true;
            textInput.dispose();
        }
    }
}
