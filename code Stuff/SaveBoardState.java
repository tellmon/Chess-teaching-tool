import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * This class is used to create the interface used to save the file for exporting the board
 */
public class SaveBoardState implements ActionListener {

	/**
     * This is the interface that i use to save and select files for the program with a nice gui
     */
    private final JFileChooser fileChooser = new JFileChooser();

    /**
     * This will check if the user has submitted a file to import
     */
    private boolean submitted = false;
    
    /**
     * This check if the setup for the panel has already happened and will skip it if it has. 
     */
    private boolean setupDone = false;
    
    /**
     * This checks if the panel has been closed by the user. 
     */
    private boolean closed = false;

    private String positionData = "";
    
    /**
     * This is the string where the file path will be stored. 
     */
    private String filePath = "";
    
    /**
     * This is creating the JFileChoser and adding its actionListener and then setting it to its save Dialog version
     */
    public void setUpForInput() {
        submitted = false;
        closed = false;
        setupDone = true;

        fileChooser.removeActionListener(this);
        fileChooser.addActionListener(this);

        int result = fileChooser.showSaveDialog(null);

        if (result != JFileChooser.APPROVE_OPTION && !submitted) {
            closed = true;
        }
    }

    /**
     * This takes the board state and then converted its 2D array to the string used in the saved file with the custom BNF format. 
     * @param boardState the board as a 2D array of chars
     * @return the string of the position data 
     */
    public String convertToString(char[][] boardState) {
        positionData = "";
        int count = 0;

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {

                if (boardState[x][y] == ' ') {
                    count++;
                } else {
                    if (count != 0) positionData += count;
                    positionData += boardState[x][y];
                    count = 0;
                }
            }

            if (count != 0) positionData += count;
            positionData += "/";
            count = 0;
        }

        return positionData.substring(0, positionData.length() - 1);
    }

    /**
     * @return the file path that was submitted
     */
    public String getName() {
        return filePath;
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
    public boolean isSetupDone() {
        return setupDone;
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
        setupDone = false;
        submitted = false;
        closed = false;
        filePath = "";
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
            closed = false;
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        } 
        else if (cmd.equals(JFileChooser.CANCEL_SELECTION)) {
        	closed = true;
            submitted = false;
        }
    }

}
