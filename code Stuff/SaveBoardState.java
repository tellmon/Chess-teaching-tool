import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class SaveBoardState implements ActionListener {

    private final JFileChooser fileChooser = new JFileChooser();

    private boolean submitted = false;
    private boolean setupDone = false;
    private boolean closed = false;

    private String positionData = "";
    private String filePath = "";
    
        
    public void setUpForInput() {
        submitted = false;
        closed = false;
        setupDone = true;

        fileChooser.removeActionListener(this); // avoid duplicates
        fileChooser.addActionListener(this);

        int result = fileChooser.showSaveDialog(null);

        // This catches X button
        if (result != JFileChooser.APPROVE_OPTION && !submitted) {
            closed = true;
        }
    }

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

    public String getName() {
        return filePath;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public boolean isSetupDone() {
        return setupDone;
    }

    public boolean isClosed() {
        return closed;
    }

    public void reset() {
        setupDone = false;
        submitted = false;
        closed = false;
        filePath = "";
    }

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
